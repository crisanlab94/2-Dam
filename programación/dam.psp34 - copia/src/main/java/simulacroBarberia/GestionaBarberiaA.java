
	package simulacroBarberia;

	import java.util.concurrent.Semaphore;

	public class GestionaBarberiaA {
	    public static void main(String[] args) {
	        final int numClientes = 10;

	        // Inicializamos en 1 y luego bajamos a 0
	        Semaphore hayClientes = new Semaphore(1);
	        Semaphore hayTurno   = new Semaphore(1);

	        try {
	            hayClientes.acquire(); // ponerlo en 0
	            //hayTurno.acquire();    // ponerlo en 0
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        System.out.println("--- INICIO DE LA JORNADA CON UN BARBERO ---");
	        long inicio = System.currentTimeMillis();

	        BarberoA barbero = new BarberoA("Barbero 1", hayClientes, hayTurno, numClientes);
	        Thread hiloBarbero = new Thread(barbero);
	        hiloBarbero.start();

	        // Clientes
	        for (int i = 1; i <= numClientes; i++) {
	            ClienteA cliente = new ClienteA("Cliente " + i, hayClientes, hayTurno);
	            new Thread(cliente).start();
	        }

	        try {
	            hiloBarbero.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        long fin = System.currentTimeMillis();
	        System.out.printf("Tiempo total de %d cortes: %.2f segundos\n",
	                          numClientes, (fin - inicio)/1000.0);
	    }
	}



