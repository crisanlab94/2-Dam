package repaso2;

import java.util.concurrent.Semaphore;


public class Sonda implements Runnable {
	private String id;
	private EstacionControl estacion;
	private Semaphore sem;
	
	
	public Sonda(String id, EstacionControl estacion, Semaphore sem) {
		super();
		this.id = id;
		this.estacion = estacion;
		this.sem = sem;
	}
	
	@Override
	public void run() {
		// La sonda intenta intenta entrar
		boolean pudoEntrar = estacion.intentarEntrar(this, sem);
		if (pudoEntrar) {
		try {
	        // El hilo pasa a estado dormido
	        Thread.sleep(2000); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        // Al terminar el tiempo, DEBE llamar al método salir del entorno
	       
	        estacion.salir(sem);
	        System.out.println("La sonda con  " + id + " ha terminado y ha salido.");
	    }
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EstacionControl getEstacion() {
		return estacion;
	}

	public void setEstacion(EstacionControl estacion) {
		this.estacion = estacion;
	}

	public Semaphore getSem() {
		return sem;
	}

	public void setSem(Semaphore sem) {
		this.sem = sem;
	}
	
	
	
}
