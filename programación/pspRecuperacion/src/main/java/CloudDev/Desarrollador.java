package CloudDev;

import java.util.concurrent.Semaphore;

public class Desarrollador implements Runnable   {
	private String id;
	private EntornoCloud entorno;
	private Semaphore sem;
	
	public Desarrollador(String id, EntornoCloud entorno, Semaphore sem) {
		super();
		this.id = id;
		this.entorno = entorno;
		this.sem = sem;
	}

	@Override
	public void run() {
		// El desarrollador intenta iniciar sesión en su entorno 
		boolean pudoEntrar=entorno.intentarIniciarSesion(this,sem);
		try {
	        // El hilo pasa a estado dormido
	        Thread.sleep(2000); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        // Al terminar el tiempo, DEBE llamar al método salir del entorno
	       
	        entorno.salir(sem);
	        System.out.println("El desarrollador " + id + " ha terminado y ha salido.");
	    }
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}


	