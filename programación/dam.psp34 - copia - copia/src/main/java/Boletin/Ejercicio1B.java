package Boletin;

public class Ejercicio1B extends Thread {
	private String nombreHilito;

	public String getNombreHilito() {
		return nombreHilito;
	}

	public void setNombreHilito(String nombreHilito) {
		this.nombreHilito = nombreHilito;
	}

	public Ejercicio1B(String nombreHilito) {
		super();
		this.nombreHilito = nombreHilito;
	}
	
	@Override
	public void run() {
		
		while (true) {
			System.out.println("PROCESOS");
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
	}

}
