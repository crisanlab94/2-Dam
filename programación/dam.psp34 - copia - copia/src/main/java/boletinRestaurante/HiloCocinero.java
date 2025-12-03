package boletinRestaurante;

import java.util.concurrent.Semaphore;

public class HiloCocinero implements Runnable {


	private String nombre;
	private Semaphore semaforo;

	

	public HiloCocinero(String nombre, Semaphore semaforo) {
		super();
		this.nombre = nombre;
		this.semaforo = semaforo;
	}
 
	@Override
	public void run() {
		cocinar();
		
		}
	

	public void cocinar() {
		while(true) {
		System.out.println(nombre + " Esta cocinando");
		try {
			 Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release(); 
			System.out.println(nombre + " Termino de cocinar");
		}
		}
		
	}
}
