package simulacionSupermercado;

	import java.util.concurrent.Semaphore;

	public class CajeroB implements Runnable {
	    private String nombre;
	    private Semaphore hayClientes;
	    private Semaphore hayTurno;
	    private int numClientes;
	    private int tiempoAtencion; // tiempo en ms

	    public CajeroB(String nombre, Semaphore hayClientes, Semaphore hayTurno, int numClientes, int tiempoAtencion) {
	        this.nombre = nombre;
	        this.hayClientes = hayClientes;
	        this.hayTurno = hayTurno;
	        this.numClientes = numClientes;
	        this.tiempoAtencion = tiempoAtencion;
	    }

	    @Override
	    public void run() {
	        for (int i = 0; i < numClientes; i++) {
	            try {
	                hayClientes.acquire(); // esperar cliente
	                System.out.println(nombre + " atiende cliente " + (i+1));
	                Thread.sleep(tiempoAtencion); // simula tiempo de atención
	                hayTurno.release(); // liberar turno
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}



