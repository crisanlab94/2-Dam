package simulacionCoches;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GestionaTaller2 {
	final int numCoches = 20;
    
    // Objeto compartido para contar (Sin static)
    int[] contadorCoches = {1};

    Semaphore hayCoches = new Semaphore(1);
    Semaphore hayTurno  = new Semaphore(1);

    try {
        // CORRECCIÓN 3: SOLO bajamos hayCoches.
        // Si bajas hayTurno, los coches no pueden entrar nunca.
        hayCoches.acquire(); 
        // hayTurno.acquire();  <-- ¡BORRADO PARA EVITAR DEADLOCK!
        
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    System.out.println("--- INICIO DE LA JORNADA CON DOS MECÁNICOS ---");
    long inicio = System.currentTimeMillis();

    // Crear mecánicos
    // CORRECCIÓN 4: Pasamos 'numCoches' al final y corregimos el nombre de variable 'contadorCoches'
    Mecanico2 mecanico1 = new Mecanico2("Mecanico 1 (5s)", hayCoches, hayTurno, 5000, contadorCoches, numCoches);
    Mecanico2 mecanico2 = new Mecanico2("Mecanico 2 (8s)", hayCoches, hayTurno, 8000, contadorCoches, numCoches);

    Thread hiloMecanico1 = new Thread(mecanico1);
    Thread hiloMecanico2 = new Thread(mecanico2);

    hiloMecanico1.start();
    hiloMecanico2.start();

    // Coches
    // No necesitamos guardar los hilos de los coches en una lista, 
    // porque vamos a esperar a que terminen los mecánicos.
    for (int i = 1; i <= numCoches; i++) {
        Coche coche = new Coche("Coche " + i, hayCoches, hayTurno);
        new Thread(coche).start();
    }

    // CORRECCIÓN 5: Esperamos a los MECÁNICOS.
    // Si esperas a los coches, el tiempo saldrá mal porque el coche 
    // termina su hilo nada más dejar las llaves, no cuando lo arreglan.
    try {
        hiloMecanico1.join();
        hiloMecanico2.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    long fin = System.currentTimeMillis();
    double tiempoTotal = (fin - inicio) / 1000.0;
    System.out.printf("Tiempo total de reparación de %d coches: %.2f segundos\n", numCoches, tiempoTotal);
}