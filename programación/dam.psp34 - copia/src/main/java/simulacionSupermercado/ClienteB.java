package simulacionSupermercado;

	import java.util.concurrent.Semaphore;

	public class ClienteB implements Runnable {
	    private String nombre;
	    private Semaphore hayClientes;
	    private Semaphore hayTurno;

	    public ClienteB(String nombre, Semaphore hayClientes, Semaphore hayTurno) {
	        this.nombre = nombre;
	        this.hayClientes = hayClientes;
	        this.hayTurno = hayTurno;
	    }

	    @Override
	    public void run() {
	        try {
	        	// 1. Coger el turno (cerrar la puerta detrás de mí)
	            hayTurno.acquire(); 
	            
	            // 2. Imprimir
	            System.out.println(nombre + " está en la cola");
	            
	            // 3. Avisar al cajero de que estoy listo
	            hayClientes.release(); 
	            
	            // ¡OJO! NO hacemos hayTurno.release() aquí.
	            // Si soltamos el turno ahora, el siguiente cliente entraría
	            // antes de que el cajero me haya atendido.
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}


