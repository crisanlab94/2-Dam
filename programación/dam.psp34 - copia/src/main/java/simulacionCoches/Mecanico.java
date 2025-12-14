package simulacionCoches;

import java.util.concurrent.Semaphore;

public class Mecanico implements Runnable {

	private String nombre;
	private Semaphore hayCoches;
	private Semaphore hayTurno;
	private int numCoches;
	

	public Mecanico(String nombre, Semaphore hayCoches, Semaphore hayTurno, int numCoches) {
		super();
		this.nombre = nombre;
		this.hayCoches = hayCoches;
		this.hayTurno = hayTurno;
		this.numCoches = numCoches;
	}


	@Override
	public void run() {
		arreglarCoche();
		
		}
	

	public void arreglarCoche() {
		// Bucle que se ejecuta exactamente numCoches veces (20 veces)
        for (int i = 1; i <= numCoches; i++) {
		try {
			// Bloquea hasta que un coche llega
			hayCoches.acquire(); //Llega un coche
			System.out.println("Estoy arreglando coche");
			 Thread.sleep(5000);
			 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(nombre + " ha terminado Coche" + i + ". Listo para el siguiente turno.");
			//Da turno al siguiente coche esperando
			 hayTurno.release();
			
			
		}
		
		
	}
        System.out.println("\n--- MECÁNICO: ¡Todos los " + numCoches + " coches han sido reparados! ---");
	}
}
