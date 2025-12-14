
	package simulacroBarberia;

	import java.util.concurrent.Semaphore;

	public class ClienteA implements Runnable {
	    private String nombre;
	    private Semaphore hayClientes;
	    private Semaphore hayTurno;

	    public ClienteA(String nombre, Semaphore hayClientes, Semaphore hayTurno) {
	        this.nombre = nombre;
	        this.hayClientes = hayClientes;
	        this.hayTurno = hayTurno;
	    }

	    @Override
	    public void run() {
	        try {
	            hayTurno.acquire(); // esperar turno
	            System.out.println(nombre + " espera para cortarse el pelo");
	            hayClientes.release(); // avisar al barbero
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}



