package simulacionCoches;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GestionaTaller2 {
public static void main(String[] args) {
		
		final int numCoches=20;
		//Un coche un mecanico
		Semaphore hayCoches = new Semaphore(1);
        Semaphore hayTurno = new Semaphore(1);
        
        
        try {
        	//Aqui inicio el turno en 0
        	//Tanto de coches como de mecanico
        	//Porque un mecanico no arregla hasta que no llega un coche
			hayCoches.acquire();
			hayTurno.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //Lista vacia para almacenar todos los hilos
        //La necesito porque hay más de un mecanico
        List<Thread> hilosCoche = new ArrayList<Thread>();
        System.out.println("--- INICIO DE LA JORNADA CON DOS MECÁNICOS ---");
        long inicio = System.currentTimeMillis();
       
     // Crear y ejecutar los dos hilos Mecánico
               //Mecánico 1: Tarda 5 segundos (5000 ms)
        		Mecanico mecanico1 = new Mecanico("Mecanico 1 (5s)", hayCoches, hayTurno, 5000);
        		//Mecánico 2: Tarda 8 segundos (8000 ms)
        		Mecanico mecanico2 = new Mecanico("Mecanico 2 (8s)", hayCoches, hayTurno, 8000);
        
        		Thread hiloMecanico1 = new Thread(mecanico1);
        		Thread hiloMecanico2 = new Thread(mecanico2);
       
        		hiloMecanico1.start();
        		hiloMecanico2.start();
        
        //Coches
        for (int i = 1; i <= numCoches; i++) {
            Coche coche = new Coche("Coche " + i, hayCoches, hayTurno);
            //Me creo hilo coches
            Thread hiloCoches = new Thread(coche);
            //Lo inicio
           hiloCoches.start();
           hilosCoche.add(hiloCoches);
        }
        

        // Esperamos a que terminen los 20 coches
        try {
        	for (Thread hilo : hilosCoche) {
        		hilo.join();
        		 }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long fin = System.currentTimeMillis();
        double tiempoTotal = (fin - inicio) / 1000.0;
        System.out.printf("Tiempo total de reparación de %d coches: %.2f segundos\n", numCoches, tiempoTotal);
       
	}
 }




