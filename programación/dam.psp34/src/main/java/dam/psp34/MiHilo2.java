package dam.psp34;

public class MiHilo2 implements Runnable {
	private String nombreHilo;
	
	
	public MiHilo2(String nombreHilo) {
		super();
		this.nombreHilo = nombreHilo;
	}


	@Override
	public void run() {
		Thread.currentThread().setName(nombreHilo);
		System.out.println("Ejecutando Hilo:"+Thread.currentThread().getName());	
		sleep(100000); //dejar el hilo un rato parado
		System.out.println("Termina hilo:" + this.nombreHilo);
	}


	private void sleep(int i) {
		// TODO Auto-generated method stub
		
	}
	
}



