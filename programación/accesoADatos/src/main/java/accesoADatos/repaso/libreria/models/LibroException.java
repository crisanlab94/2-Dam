package accesoADatos.repaso.libreria.models;

public class LibroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LibroException(String message) {
		super(message);
		System.out.println("Lanzo mi excepcion con" + message);
	}
	
	

}
