package hilos;

public class HiloRunnable implements Runnable {
	private String nombreHilo;

	public HiloRunnable(String nombreHilo) {
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
		Thread.currentThread().setName(nombreHilo);
		System.out.println("Ejecutando Hilo:" + Thread.currentThread().getName());
	}

}
