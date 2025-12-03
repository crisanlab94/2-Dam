package Boletin;

public class Ejercicio1A extends Thread {
	private String nombreHilito;

	public String getNombreHilito() {
		return nombreHilito;
	}

	public void setNombreHilito(String nombreHilito) {
		this.nombreHilito = nombreHilito;
	}

	public Ejercicio1A(String nombreHilito) {
		super();
		this.nombreHilito = nombreHilito;
	}

	@Override
	public void run() {
		
		while (true) {
			System.out.println("SERVICIOS");
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
