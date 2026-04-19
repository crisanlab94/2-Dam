package exceptions;

/**
 * Excepción para controlar cuando se intenta registrar un ID que ya existe.
 */
public class DuplicateIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateIdException() {
        super();
    }

    public DuplicateIdException(int id) {
        super("Error de registro: El ID " + id + " ya existe en la base de datos.");
    }
}
