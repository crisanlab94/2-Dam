
	package simulacroBarberia;

	import java.util.concurrent.Semaphore;

	public class BarberoB implements Runnable {
	    private String nombre;
	    private Semaphore hayClientes;
	    private Semaphore hayTurno;
	    private int numClientes;
	    private int tiempoCorte;

	    public BarberoB(String nombre, Semaphore hayClientes, Semaphore hayTurno, int numClientes, int tiempoCorte) {
	        this.nombre = nombre;
	        this.hayClientes = hayClientes;
	        this.hayTurno = hayTurno;
	        this.numClientes = numClientes;
	        this.tiempoCorte = tiempoCorte;
	    }

	    @Override
	    public void run() {
	        for (int i = 0; i < numClientes; i++) {
	            try {
	                hayClientes.acquire(); // esperar cliente
	                System.out.println(nombre + " corta el pelo al cliente " + (i+1));
	                Thread.sleep(tiempoCorte); // simula tiempo de corte
	                hayTurno.release(); // liberar turno
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}



