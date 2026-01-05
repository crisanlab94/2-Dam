
public class CraftersException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CraftersException(String message) {
		super(message);
		System.out.println("No es posible construir un evento con esos datos" + message);
	}
	
	

}
