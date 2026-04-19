package exceptions;

public class DuplicateIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateIdException() {
        super();
    }

    public DuplicateIdException(String id) {
        super("Error: El identificador '" + id + "' ya existe en el club.");
    }
}
