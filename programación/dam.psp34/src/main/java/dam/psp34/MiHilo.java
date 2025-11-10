package dam.psp34;

public class MiHilo extends Thread{
	
	private String nombreHilo;

	public MiHilo(String nombreHilo) {
		super();
		this.nombreHilo = nombreHilo;
	}
	
	
	
	@Override
    public void run() {
		//ver estado
		System.out.println(this.nombreHilo + "estado:" + this.getState());
		try {
			sleep(100000); //dejar el hilo un rato parado
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Termina hilo:" + this.nombreHilo);
	}
	
	
		
	}
	


