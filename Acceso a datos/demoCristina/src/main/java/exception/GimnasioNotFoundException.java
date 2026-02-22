package exception;

public class GimnasioNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public GimnasioNotFoundException() {
        super();
    }
      

    public GimnasioNotFoundException(String message) {
        super(message);
    }
 
    public GimnasioNotFoundException(long id) {
        super("GYM not found: " + id);
    }

}
