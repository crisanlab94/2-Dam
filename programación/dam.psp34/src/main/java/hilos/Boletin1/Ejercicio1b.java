package hilos.Boletin1;

public class Ejercicio1b extends Thread {
	
	private String nombreHilo;
	
	
	public Ejercicio1b(String nombreHilo) {
		super();
		this.nombreHilo = nombreHilo;
	}


	@Override
    public void run() {
		while (true) {
			System.out.println("Procesos");
		try {
			sleep(500); //dejar el hilo un rato parado
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	}
}
