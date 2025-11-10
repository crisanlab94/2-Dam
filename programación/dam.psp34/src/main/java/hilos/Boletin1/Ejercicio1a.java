package hilos.Boletin1;

public class Ejercicio1a extends Thread {
	private String nombreHilo;
	
	
	public Ejercicio1a(String nombreHilo) {
		super();
		this.nombreHilo = nombreHilo;
	}


	@Override
    public void run() {
		while (true) {
			System.out.println("Servicios");
		try {
			sleep(500); //dejar el hilo un rato parado
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	}
}
