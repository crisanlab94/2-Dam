package boletinRestauranteMenu;

import java.util.concurrent.Semaphore;

public class Cocinero implements Runnable {

	private String nombre;
	private Semaphore hayClientes;
	private Semaphore hayPlato;
	

	public Cocinero(String nombre, Semaphore hayClientes, Semaphore hayPlato) {
		super();
		this.nombre = nombre;
		this.hayClientes = hayClientes;
		this.hayPlato = hayPlato;
	}


	@Override
	public void run() {
		cocinar();
		
		}
	

	public void cocinar() {
		while(true) {
		try {
			hayClientes.acquire();
			System.out.println("Estoy cocinando");
			 Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(nombre + " Hay plato");
			hayPlato.release(3); 
			
		}
		}
		
	}

}
