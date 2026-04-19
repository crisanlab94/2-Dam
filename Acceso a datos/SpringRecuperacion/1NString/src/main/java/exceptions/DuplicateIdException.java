package exceptions;

/**
 * Excepción para controlar cuando se intenta registrar un ID String
 * que ya existe (DNI o Código Centro).
 */
public class DuplicateIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateIdException() {
        super();
    }

    public DuplicateIdException(String id) {
        super("Error de registro: El identificador '" + id + "' ya existe en el sistema.");
    }
}