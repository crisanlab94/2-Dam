
	package simulacroBarberia;

	import java.util.concurrent.Semaphore;

	public class BarberoA implements Runnable {
	    private String nombre;
	    private Semaphore hayClientes;
	    private Semaphore hayTurno;
	    private int numClientes;

	    public BarberoA(String nombre, Semaphore hayClientes, Semaphore hayTurno, int numClientes) {
	        this.nombre = nombre;
	        this.hayClientes = hayClientes;
	        this.hayTurno = hayTurno;
	        this.numClientes = numClientes;
	    }

	    @Override
	    public void run() {
	        for (int i = 0; i < numClientes; i++) {
	            try {
	                hayClientes.acquire(); // esperar cliente
	                System.out.println(nombre + " corta el pelo al cliente " + (i+1));
	                Thread.sleep(7000); // tarda 7s en cortar
	                hayTurno.release(); // liberar turno
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}



