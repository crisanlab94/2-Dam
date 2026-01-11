package repaso1;

import java.util.concurrent.Semaphore;



public class Atleta implements Runnable {
	private String id;
	private SalaFisio sala;
	private Semaphore sem;
	
	public Atleta(String id, SalaFisio sala, Semaphore sem) {
		super();
		this.id = id;
		this.sala = sala;
		this.sem = sem;
	}
	
	
	@Override
	public void run() {
		// El atelta intenta entrar en su sala
		boolean entro= sala.intentarEntrar(this, sem);
		if(entro) {
		try {
	        // Simulamos el tiempo que tarda el masaje o la rehabilitación
	        Thread.sleep(2000); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        // Al terminar, DEBE salir para liberar el sitio a otros
	        sala.salir(sem); 
	        System.out.println("El atleta " + id + " ha terminado y ha salido.");
	    }
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SalaFisio getSala() {
		return sala;
	}

	public void setSala(SalaFisio sala) {
		this.sala = sala;
	}

	public Semaphore getSem() {
		return sem;
	}

	public void setSem(Semaphore sem) {
		this.sem = sem;
	}
	
	

}
