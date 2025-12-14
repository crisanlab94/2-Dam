package simulacionCoches;


import java.util.concurrent.Semaphore;



public class GestionaTaller {
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
        
        //calcular cuanto tarda
        System.out.println("--- INICIO DE LA JORNADA ---");
        long inicio = System.currentTimeMillis();
        
        //Solo hay un mecanico
        Mecanico mecanico = new Mecanico("Mecanico 1", hayCoches, hayTurno, numCoches);
        //Me creo el hilo mecanico
        Thread hiloMecanico = new Thread(mecanico);
        //Lo inicio
        hiloMecanico.start();
        
        //Coches
        for (int i = 1; i <= numCoches; i++) {
            Coche coche = new Coche("Coche " + i, hayCoches, hayTurno);
            //Me creo hilo coches
            Thread hiloCoches = new Thread(coche);
            //Lo inicio
           hiloCoches.start();
        }
        

        // Esperamos a que termine el mecánico
        try {
            hiloMecanico.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long fin = System.currentTimeMillis();
        double tiempoTotal = (fin - inicio) / 1000.0;
        System.out.printf("Tiempo total de reparación de %d coches: %.2f segundos\n", numCoches, tiempoTotal);
       
	}
 }

