package boletinRestaurante;

import java.util.concurrent.Semaphore;

public class HiloComensal implements Runnable {

	private String nombre;
	private Semaphore semaforo;

	

	public HiloComensal(String nombre, Semaphore semaforo) {
		super();
		this.nombre = nombre;
		this.semaforo = semaforo;
	}
 
	@Override
	public void run() {
		comer();
		
		}
	

	public void comer() {
		while(true) {
		try {
			semaforo.acquire();
		    System.out.println(nombre + " Esta comiendo");
		  
		} catch (InterruptedException e) {
			e.printStackTrace();
		
	}
		}
		
  }
}


