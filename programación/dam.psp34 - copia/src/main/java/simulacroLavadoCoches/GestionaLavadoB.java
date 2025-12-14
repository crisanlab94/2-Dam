
	package simulacroLavadoCoches;

	import java.util.concurrent.Semaphore;

	public class GestionaLavadoB {
	    public static void main(String[] args) {
	        final int numCoches = 12;

	        // Inicializamos en 1 y luego bajamos a 0
	        Semaphore hayCoches = new Semaphore(1);
	        Semaphore hayTurno  = new Semaphore(1);

	        try {
	            hayCoches.acquire(); // ponerlo en 0
	            hayTurno.acquire();  // ponerlo en 0
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        System.out.println("--- INICIO DE LA JORNADA CON DOS MÁQUINAS DE LAVADO ---");
	        long inicio = System.currentTimeMillis();

	        LavadoraB lavadora1 = new LavadoraB("Lavadora 1 (6s)", hayCoches, hayTurno, numCoches, 6000);
	        LavadoraB lavadora2 = new LavadoraB("Lavadora 2 (9s)", hayCoches, hayTurno, numCoches, 9000);

	        Thread hiloLavadora1 = new Thread(lavadora1);
	        Thread hiloLavadora2 = new Thread(lavadora2);

	        hiloLavadora1.start();
	        hiloLavadora2.start();

	        // Coches
	        for (int i = 1; i <= numCoches; i++) {
	            CocheB coche = new CocheB("Coche " + i, hayCoches, hayTurno);
	            new Thread(coche).start();
	        }

	        try {
	            hiloLavadora1.join();
	            hiloLavadora2.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        long fin = System.currentTimeMillis();
	        System.out.printf("Tiempo total de lavado de %d coches: %.2f segundos\n",
	                          numCoches, (fin - inicio)/1000.0);
	    }
	}



