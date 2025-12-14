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
	            hayTurno.acquire(); // esperar turno
	            System.out.println(nombre + " está en la cola");
	            hayClientes.release(); // avisar al cajero
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}


