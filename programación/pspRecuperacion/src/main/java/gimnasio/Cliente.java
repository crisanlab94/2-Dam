package gimnasio;

public class Cliente implements Runnable {
	private String id;
	private Gimnasio gym;

	public Cliente(String id, Gimnasio gym) {
		super();
		this.id = id;
		this.gym = gym;
	}
	
	public void run() {
	    if (gym.entrar(this)) {
	        try {
	            Thread.sleep(2000); // Entrenando...
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            gym.salir(); // Solo sale si el if fue true
	        }
	    }
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Gimnasio getGym() {
		return gym;
	}

	public void setGym(Gimnasio gym) {
		this.gym = gym;
	}
	
	

}
