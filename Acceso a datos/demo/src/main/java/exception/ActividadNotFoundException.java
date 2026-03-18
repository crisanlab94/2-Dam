package exception;

public class ActividadNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public ActividadNotFoundException() {
        super();
    }
      

    public ActividadNotFoundException(String message) {
        super(message);
    }
 
    public ActividadNotFoundException(long id) {
        super("Activity not found: " + id);
    }
}



