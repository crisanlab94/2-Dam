
	package simulacionSupermercado;

	import java.util.concurrent.Semaphore;

	public class GestionaSupermercadoB {
	    public static void main(String[] args) {
	        final int numClientes = 20;

	        // Inicializamos en 1 y luego bajamos a 0
	        Semaphore hayClientes = new Semaphore(1);
	        Semaphore hayTurno   = new Semaphore(1);

	        try {
	            hayClientes.acquire(); // ponerlo en 0
	            hayTurno.acquire();    // ponerlo en 0
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        System.out.println("--- INICIO DE LA JORNADA CON DOS CAJEROS ---");
	        long inicio = System.currentTimeMillis();

	        // Dos cajeros con distintos tiempos
	        CajeroB cajero1 = new CajeroB("Cajero 1 (3s)", hayClientes, hayTurno, numClientes, 3000);
	        CajeroB cajero2 = new CajeroB("Cajero 2 (5s)", hayClientes, hayTurno, numClientes, 5000);

	        Thread hiloCajero1 = new Thread(cajero1);
	        Thread hiloCajero2 = new Thread(cajero2);

	        hiloCajero1.start();
	        hiloCajero2.start();

	        // Clientes
	        for (int i = 1; i <= numClientes; i++) {
	            ClienteB cliente = new ClienteB("Cliente " + i, hayClientes, hayTurno);
	            new Thread(cliente).start();
	        }

	        // Esperamos a que terminen los cajeros
	        try {
	            hiloCajero1.join();
	            hiloCajero2.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        long fin = System.currentTimeMillis();
	        System.out.printf("Tiempo total de atención de %d clientes: %.2f segundos\n",
	                          numClientes, (fin - inicio)/1000.0);
	    }
	}



