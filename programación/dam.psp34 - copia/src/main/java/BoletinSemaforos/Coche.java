package BoletinSemaforos;

public class Coche implements Runnable {
	private String nombre;
	private SemaforoCoche semaforo;
	public Coche(String nombre, SemaforoCoche semaforo) {
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
			semaforo.adquirir();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Intenta ocupar un surtidor
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
		semaforo.liberar(); 
	}

	public void mostrarMensajePago() {
		System.out.println(nombre + " está pagando con tarjeta...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Simula 1 segundo de pago
		System.out.println(nombre + " ha terminado de pagar.");
	}
}
