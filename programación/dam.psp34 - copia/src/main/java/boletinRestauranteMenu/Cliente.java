package boletinRestauranteMenu;

import java.util.concurrent.Semaphore;

public class Cliente implements Runnable {

	private String nombre;
	private Semaphore hayClientes;
	private Semaphore hayPlato;
	
	public Cliente(String nombre, Semaphore hayClientes, Semaphore hayPlato) {
		super();
		this.nombre = nombre;
		this.hayClientes = hayClientes;
		this.hayPlato = hayPlato;
	}


	@Override
	public void run() {
		comer();
		
		}
	

	public void comer() {
		while(true) {
		try {
			System.out.println("He llegado "+ nombre);
			hayClientes.release();
			hayPlato.acquire();
			System.out.println("Estoy comiendo..."+ nombre);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			
			System.out.println(nombre + " He comido");
		}
		}
		
	}
}
