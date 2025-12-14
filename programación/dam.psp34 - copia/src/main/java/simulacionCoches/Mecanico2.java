package simulacionCoches;

import java.util.concurrent.Semaphore;

public class Mecanico2 implements Runnable {
	private String nombre;
	private Semaphore hayCoches;
	private Semaphore hayTurno;
	private int tiempoReparacion;
	

	public Mecanico2(String nombre, Semaphore hayCoches, Semaphore hayTurno, int tiempoReparacion) {
		super();
		this.nombre = nombre;
		this.hayCoches = hayCoches;
		this.hayTurno = hayTurno;
		this.tiempoReparacion = tiempoReparacion;
	}


	@Override
	public void run() {
		arreglarCoche();
		
		}
	

	public void arreglarCoche() {
		// Bucle infinito: el mecanico siempre intenta trabajar. 
        // Se bloqueará en hayCoches.acquire() cuando ya no quede trabajo.
        while(true) {
		try {
			// Bloquea hasta que un coche llega
			hayCoches.acquire(); //Llega un coche
			System.out.println(nombre + "Estoy arreglando coche");
			 Thread.sleep(tiempoReparacion);
			 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(nombre + " ha terminado la repacion . Listo para el siguiente coche");
			//Da turno al siguiente coche esperando
			 hayTurno.release();
			
			
		}
		
		
	}

	}
}



