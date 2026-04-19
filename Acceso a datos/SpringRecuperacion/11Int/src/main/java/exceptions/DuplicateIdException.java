package exceptions;


public class DuplicateIdException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateIdException(int id) {
        super("Error de registro: El identificador " + id + " ya está en uso.");
    }
}
