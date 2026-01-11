package repaso3;

import java.util.concurrent.Semaphore;

public class ClubPadel {
    private Semaphore pistas; // Semáforo para el aforo
    private int contadorGlobalJugadores; // Variable para el requisito 3

    public ClubPadel(int numPistas) {
        // Inicializamos con 4 pistas
        this.pistas = new Semaphore(numPistas);
        this.contadorGlobalJugadores = 0;
    }

    /**
     * APARTADO 1 y 2: Intentar entrar con tryAcquire
     */
    public boolean intentarEntrar(Jugador j) {
        // Intentamos coger una pista. Si no hay (false), el hilo no se bloquea.
        if (pistas.tryAcquire()) {
            System.out.println("✅ El jugador " + j.getId() + " ha ocupado una pista.");
            return true;
        } else {
            System.out.println("❌ Club lleno. El jugador " + j.getId() + " se marcha a otro club.");
            return false;
        }
    }

    /**
     * APARTADO 3: Contador protegido con Monitor (synchronized)
     */
    public synchronized void salirYContar(Jugador j) {
        pistas.release(); // Soltamos la pista
        contadorGlobalJugadores++; // Incrementamos el contador de forma segura
        System.out.println("⬅️ El jugador " + j.getId() + " ha salido. Total hoy: " + contadorGlobalJugadores);
    }
}