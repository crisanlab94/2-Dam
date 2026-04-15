package modelo;

public class AppException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }


}
