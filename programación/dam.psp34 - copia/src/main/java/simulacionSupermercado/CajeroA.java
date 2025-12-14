package simulacionSupermercado;

import java.util.concurrent.Semaphore;

public class CajeroA  implements Runnable {
	    private String nombre;
	    private Semaphore hayClientes;
	    private Semaphore hayTurno;
	    private int numClientes;

	    public CajeroA(String nombre, Semaphore hayClientes, Semaphore hayTurno, int numClientes) {
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
	                System.out.println(nombre + " atiende cliente " + (i+1));
	                Thread.sleep(3000); // tarda 3s
	                hayTurno.release(); // liberar turno
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	