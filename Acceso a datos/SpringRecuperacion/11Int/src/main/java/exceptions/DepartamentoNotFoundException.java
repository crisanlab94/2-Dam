package exceptions;

/**
 * Excepción personalizada siguiendo la estructura del profesor.
 * Recibe el id como 'int' para el mensaje de error.
 */
public class DepartamentoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5030665213865363481L;

    public DepartamentoNotFoundException() { super(); }
    public DepartamentoNotFoundException(String message) { super(message); }
 
    public DepartamentoNotFoundException(int id) {
        super("No existe un departamento con el id: " + id);
    }
}

