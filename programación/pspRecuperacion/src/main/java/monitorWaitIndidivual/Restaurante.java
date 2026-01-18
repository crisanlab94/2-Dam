package monitorWaitIndidivual;

public class Restaurante {
	
	    private boolean hayPlato = false; // Estado inicial: no hay comida

	    // El Cocinero llama aquí
	    public synchronized void ponerPlato(String nombreCocinero) {
	        // Mientras haya comida, el cocinero espera (para no desperdiciar)
	        while (hayPlato == true) {
	            try {
	                wait(); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        // Acción de cocinar
	        hayPlato = true;
	        System.out.println("[" + nombreCocinero + "] ha servido un plato caliente.");
	        
	        // AVISAR a los clientes que están en el wait()
	        notifyAll();
	    }

	    // El Cliente llama aquí
	    public synchronized void comerPlato(String nombreCliente) {
	        // Mientras NO haya comida, el cliente espera
	        while (hayPlato == false) {
	            try {
	                System.out.println(nombreCliente + " está esperando su comida...");
	                wait(); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        // Acción de comer
	        hayPlato = false;
	        System.out.println("--> " + nombreCliente + " se ha comido el plato y se va feliz.");
	        
	        // AVISAR al cocinero de que el plato está vacío
	        notifyAll();
	    }
	}


