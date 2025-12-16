package simulacionCoches;

import java.util.concurrent.Semaphore;

public class Coche implements Runnable {

	private String nombre;
	private Semaphore hayCoches;
	private Semaphore hayTurno;
	


	public Coche(String nombre, Semaphore hayCoches, Semaphore hayTurno) {
		super();
		this.nombre = nombre;
		this.hayCoches = hayCoches;
		this.hayTurno = hayTurno;
	}


	@Override
	public void run() {
		turnoArreglar();
		
		}
	

	public void turnoArreglar() {
	
		try {
			System.out.println("He llegado "+ nombre);
			hayCoches.release();
			hayTurno.acquire();
			System.out.println("Estoy siendo arreglado..."+ nombre);
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		} finally {
			
			System.out.println(nombre + " Me voy, ya he sido arreglado");
			hayTurno.release();
		
		}
		
	}
}
