package monitorWaitIndidivual;



	public class GestionaRestaurante {
	    public static void main(String[] args) {
	        Restaurante res = new Restaurante();
	        int numeroClientes = 5;

	        // 1. Creamos y lanzamos al Cocinero (le decimos que cocine para 5)
	        Thread tCocinero = new Thread(new Cocinero(res, numeroClientes));
	        tCocinero.start();

	        // 2. Creamos y lanzamos a los 5 Clientes
	        Thread[] hilosClientes = new Thread[numeroClientes];
	        for (int i = 0; i < numeroClientes; i++) {
	            hilosClientes[i] = new Thread(new Cliente("Cliente-" + (i+1), res));
	            hilosClientes[i].start();
	        }

	        // 3. Esperamos a que todos terminen para cerrar el restaurante
	        try {
	            tCocinero.join();
	            for (int i = 0; i < numeroClientes; i++) {
	                hilosClientes[i].join();
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        System.out.println("\n--- EL RESTAURANTE HA CERRADO ---");
	    }
	}


