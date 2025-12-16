package simulacionCoches;

import java.util.concurrent.Semaphore;

public class Mecanico2 implements Runnable {
	private String nombre;
    private Semaphore hayCoches;
    private Semaphore hayTurno;
    private int tiempoReparacion;
    private int[] contadorCoches;
    
    // CORRECCIÓN 1: Declaramos la variable aquí, como atributo de la clase
    private int maxCoches; 

    // CORRECCIÓN 2: Añadimos 'int maxCoches' a los argumentos del constructor
    public Mecanico2(String nombre, Semaphore hayCoches, Semaphore hayTurno, int tiempoReparacion, int[] contadorCoches, int maxCoches) {
        this.nombre = nombre;
        this.hayCoches = hayCoches;
        this.hayTurno = hayTurno;
        this.tiempoReparacion = tiempoReparacion;
        this.contadorCoches = contadorCoches;
        this.maxCoches = maxCoches; // Guardamos el valor
    }

    @Override
    public void run() {
        arreglarCoche();
    }

    public void arreglarCoche() {
        boolean tallerAbierto = true;

        while (tallerAbierto) {
            try {
                // 1. Esperar trabajo
                hayCoches.acquire();

                int cocheActual = 0;
                boolean tengoTrabajo = false;

                // 2. Coger ticket (Synchronized)
                synchronized (contadorCoches) {
                    cocheActual = contadorCoches[0];

                    if (cocheActual <= maxCoches) {
                        contadorCoches[0]++; 
                        tengoTrabajo = true;
                    } else {
                        tallerAbierto = false; 
                        hayCoches.release(); // Avisar al otro mecánico para que salga
                    }
                }

                // 3. Trabajar y LIBERAR TURNO
                if (tengoTrabajo) {
                    System.out.println("🔧 " + nombre + " está arreglando Coche " + cocheActual);
                    Thread.sleep(tiempoReparacion);
                    System.out.println("✅ " + nombre + " terminó con Coche " + cocheActual);
                    
                    // IMPORTANTE: El mecánico abre la puerta para el siguiente coche
                    hayTurno.release(); 
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
    