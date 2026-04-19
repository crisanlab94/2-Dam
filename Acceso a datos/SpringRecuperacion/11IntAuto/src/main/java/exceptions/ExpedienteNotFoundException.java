package exceptions;

public class ExpedienteNotFoundException extends RuntimeException  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public ExpedienteNotFoundException() {
        super();
    }
    

    public ExpedienteNotFoundException(String message) {
        super(message);
    }
 
    public ExpedienteNotFoundException(long id) {
        super("No existe un expediente con id: " + id);
    }
}

