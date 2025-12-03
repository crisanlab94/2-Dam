package botelin.ServidorWeb;

import java.util.concurrent.Semaphore;

public class PeticionWeb implements Runnable {

	private String nombre;
	private Semaphore semaforo;

	

	public PeticionWeb(String nombre, Semaphore semaforo) {
		super();
		this.nombre = nombre;
		this.semaforo = semaforo;
	}
 
	@Override
	public void run() {
		conexionPeticion();
		
		}

	public void conexionPeticion() {
		try {
			semaforo.acquire();
		    System.out.println(nombre + " Esta dentro de la web");
		    Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release(); 
			System.out.println(nombre + " Ya no esta dentro");
		}
	}



}
