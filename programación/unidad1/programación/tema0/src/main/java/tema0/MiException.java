package tema0;

public class MiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MiException(String message) {
		super(message);
		System.out.println("Lanzo mi excepcion con" + message);
	}
	
	

}
