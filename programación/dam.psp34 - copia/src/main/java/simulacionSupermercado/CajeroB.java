package simulacionSupermercado;

	import java.util.concurrent.Semaphore;

	public class CajeroB implements Runnable {
	    private String nombre;
	    private Semaphore hayClientes;
	    private Semaphore hayTurno;
	    private int [] contadorCompartido; //contador para turno
	    private int numClientes;
	    private int tiempoAtencion; // tiempo en ms

	 

	    public CajeroB(String nombre, Semaphore hayClientes, Semaphore hayTurno, int[] contadorCompartido,
				int numClientes, int tiempoAtencion) {
			super();
			this.nombre = nombre;
			this.hayClientes = hayClientes;
			this.hayTurno = hayTurno;
			this.contadorCompartido = contadorCompartido;
			this.numClientes = numClientes;
			this.tiempoAtencion = tiempoAtencion;
		}



	    @Override
	    public void run() {
	        
	    	boolean tiendaAbierta = true;

	        while (tiendaAbierta) {
	            try {
	                // 1. Esperar a que el cliente avise (handshake)
	                hayClientes.acquire();

	                int clienteAAtender = 0;
	                boolean tengoTrabajo = false;

	                // 2. Mirar la libreta compartida (Synchronized)
	                synchronized (contadorCompartido) {
	                    clienteAAtender = contadorCompartido[0];

	                    if (clienteAAtender <= numClientes) {
	                        contadorCompartido[0]++; 
	                        tengoTrabajo = true;
	                    } else {
	                        tiendaAbierta = false;
	                        hayClientes.release(); // Despertar al compañero para que se vaya
	                    }
	                }

	                // 3. Trabajar y LIBERAR EL TURNO
	                if (tengoTrabajo) {
	                    System.out.println("🟢 " + nombre + " atiende cliente " + clienteAAtender);
	                    Thread.sleep(tiempoAtencion);
	                    
	                    // ¡AQUÍ ESTÁ LA CLAVE!
	                    // El cajero dice: "Ya he terminado con este, que pase el siguiente".
	                    hayTurno.release(); 
	                }

	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	}
