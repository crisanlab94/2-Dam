
	package simulacroBarberia;

	import java.util.concurrent.Semaphore;

	public class GestionaBarberiaB {
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

	        System.out.println("--- INICIO DE LA JORNADA CON DOS BARBEROS ---");
	        long inicio = System.currentTimeMillis();

	        BarberoB barbero1 = new BarberoB("Barbero 1 (7s)", hayClientes, hayTurno, numClientes, 7000);
	        BarberoB barbero2 = new BarberoB("Barbero 2 (10s)", hayClientes, hayTurno, numClientes, 10000);

	        Thread hiloBarbero1 = new Thread(barbero1);
	        Thread hiloBarbero2 = new Thread(barbero2);

	        hiloBarbero1.start();
	        hiloBarbero2.start();

	        // Clientes
	        for (int i = 1; i <= numClientes; i++) {
	            ClienteB cliente = new ClienteB("Cliente " + i, hayClientes, hayTurno);
	            new Thread(cliente).start();
	        }

	        try {
	            hiloBarbero1.join();
	            hiloBarbero2.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        long fin = System.currentTimeMillis();
	        System.out.printf("Tiempo total de %d cortes: %.2f segundos\n",
	                          numClientes, (fin - inicio)/1000.0);
	    }
	}



