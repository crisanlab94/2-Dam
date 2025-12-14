
	package simulacroLavadoCoches;

	import java.util.concurrent.Semaphore;

	public class GestionaLavadoA {
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

	        System.out.println("--- INICIO DE LA JORNADA CON UNA MÁQUINA DE LAVADO ---");
	        long inicio = System.currentTimeMillis();

	        LavadoraA lavadora = new LavadoraA("Lavadora 1", hayCoches, hayTurno, numCoches);
	        Thread hiloLavadora = new Thread(lavadora);
	        hiloLavadora.start();

	        // Coches
	        for (int i = 1; i <= numCoches; i++) {
	            CocheA coche = new CocheA("Coche " + i, hayCoches, hayTurno);
	            new Thread(coche).start();
	        }

	        try {
	            hiloLavadora.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        long fin = System.currentTimeMillis();
	        System.out.printf("Tiempo total de lavado de %d coches: %.2f segundos\n",
	                          numCoches, (fin - inicio)/1000.0);
	    }
	}



