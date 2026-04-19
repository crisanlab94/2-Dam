package exceptions;

public class DuplicateIdException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateIdException(String id) {
        super("Error de registro: El identificador '" + id + "' ya existe.");
    }
}
