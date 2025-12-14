package simulacionSupermercado;

import java.util.concurrent.Semaphore;

	    public class GestionaSupermercadoA {
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

	            System.out.println("--- INICIO DE LA JORNADA CON UN CAJERO ---");
	            long inicio = System.currentTimeMillis();

	            CajeroA cajero = new CajeroA("Cajero 1", hayClientes, hayTurno, numClientes);
	            Thread hiloCajero = new Thread(cajero);
	            hiloCajero.start();

	            // Clientes
	            for (int i = 1; i <= numClientes; i++) {
	                ClienteA cliente = new ClienteA("Cliente " + i, hayClientes, hayTurno);
	                new Thread(cliente).start();
	            }

	            // Esperamos a que termine el cajero
	            try {
	                hiloCajero.join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            long fin = System.currentTimeMillis();
	            System.out.printf("Tiempo total de atención de %d clientes: %.2f segundos\n",
	                              numClientes, (fin - inicio)/1000.0);
	        }
	    }






