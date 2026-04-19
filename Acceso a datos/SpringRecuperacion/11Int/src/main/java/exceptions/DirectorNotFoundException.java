package exceptions;

/**
 * Excepción personalizada siguiendo la estructura del profesor.
 * Recibe el id como 'int' para el mensaje de error.
 */
public class DirectorNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5030665213865363481L;

    public DirectorNotFoundException() { super(); }
    public DirectorNotFoundException(String message) { super(message); }
 
    public DirectorNotFoundException(int id) {
        super("No existe un director con el id: " + id);
    }
}

