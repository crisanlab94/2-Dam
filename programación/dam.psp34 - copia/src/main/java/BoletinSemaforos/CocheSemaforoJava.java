package BoletinSemaforos;

import java.util.concurrent.Semaphore;

public class CocheSemaforoJava implements Runnable {
	private String nombre;
	private Semaphore semaforo;

	

	public CocheSemaforoJava(String nombre, Semaphore semaforo) {
		super();
		this.nombre = nombre;
		this.semaforo = semaforo;
	}
 
	@Override
	public void run() {
		//mostrarMensajePago();
		mostrarMensajeAdquirido();
		mostrarMensajeLiberado();
	}

	public void mostrarMensajeAdquirido() {
		System.out.println(nombre + " espera para repostar...");
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(nombre + " está repostando.");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void mostrarMensajeLiberado() {
		System.out.println(nombre + " ha terminado de repostar.");
		semaforo.release(); 
	}

	public void mostrarMensajePago() {
		System.out.println(nombre + " está pagando con tarjeta...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(nombre + " ha terminado de pagar.");
	}
}
