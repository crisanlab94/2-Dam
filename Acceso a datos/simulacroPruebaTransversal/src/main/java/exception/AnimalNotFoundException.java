package exception;

public class AnimalNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public AnimalNotFoundException() {
        super();
    }
      

    public AnimalNotFoundException(String message) {
        super(message);
    }
 
    public AnimalNotFoundException(long id) {
        super("Animal not found: " + id);
    }

}
