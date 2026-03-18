package exception;

public class SocioNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public SocioNotFoundException() {
        super();
    }
      

    public SocioNotFoundException(String message) {
        super(message);
    }
 
    public SocioNotFoundException(long id) {
        super("Socio not found: " + id);
    }

}
