package multicine;

import java.util.concurrent.Semaphore;

public class GestionaCine {
    public static void main(String[] args) {
        // 1. Iniciamos los semáforos (Aforos)
        Semaphore sTaquilla = new Semaphore(2);
        Semaphore sBar = new Semaphore(1);
        int contadorHilos=0;

        Cine cine = new Cine(sTaquilla, sBar);
        
        // Total: 8 (Solo Cine) + 4 (Cine y Comida) = 12 hilos
        Thread[] hilos = new Thread[12];

        // 2. Lanzar 8 espectadores de SOLO_CINE (Índices 0 al 7)
        for (int i = 1; i <= 8; i++) {
            String dni = "Solo Entrada-" + i;
            String codigoQR;
            
            if (i == 3) {
                codigoQR = "ERROR";
            } else {
                codigoQR = dni;
            }
            
            hilos[i - 1] = new Thread(new Espectador(dni, codigoQR, TipoCine.SOLOCINE, cine));
            hilos[i - 1].start();
            contadorHilos++;
        }

        // 3. Lanzar 4 espectadores de CINE_Y_COMIDA (Índices 8 al 11)
        for (int i = 1; i <= 4; i++) {
            String dni = "COM-" + i;
            
            // CORRECCIÓN: Usamos el índice 8 (después de los 8 primeros) + (i-1)
           
            hilos[contadorHilos] = new Thread(new Espectador(dni, dni, TipoCine.CINEYCOMIDA, cine));
            hilos[contadorHilos].start();
            contadorHilos++;
        }

        // 4. Esperar a todos (Join)
        for (int i = 0; i < hilos.length; i++) {
            if (hilos[i] != null) {
                try {
                    hilos[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // 5. Resultado final
        cine.mostrarResumen();
    }
}