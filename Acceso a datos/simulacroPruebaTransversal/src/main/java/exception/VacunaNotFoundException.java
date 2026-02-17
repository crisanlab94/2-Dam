package exception;

public class VacunaNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public VacunaNotFoundException() {
        super();
    }
      

    public VacunaNotFoundException(String message) {
        super(message);
    }
 
    public VacunaNotFoundException(long id) {
        super("Vacuna not found: " + id);
    }

}
