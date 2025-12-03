package hilos;

public class MiHilo extends Thread {
	private String nombreHilo;

	public MiHilo(String nombreHilo) {
		super();
		this.nombreHilo = nombreHilo;
	}

	public String getNombreHilo() {
		return nombreHilo;
	}

	public void setNombreHilo(String nombreHilo) {
		this.nombreHilo = nombreHilo;
	}

	@Override
	public void run() {
		System.out.println(this.nombreHilo + this.getState());
		try {
			sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Saliendo del " + this.getNombreHilo());

	}

}
