package exceptions;

public class PacienteNotFoundException extends RuntimeException  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public PacienteNotFoundException() {
        super();
    }
    

    public PacienteNotFoundException(String message) {
        super(message);
    }
 
    public PacienteNotFoundException(long id) {
        super("No existe un paciente con dni: " + id);
    }
}

