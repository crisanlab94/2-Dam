package exceptions;

/**
 * Excepción para errores de validación de negocio (formato del DNI).
 */
public class DniInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 1. Constructor vacío
    public DniInvalidoException() {
        super();
    }

    // 2. Constructor para mensaje personalizado
    public DniInvalidoException(String message) {
        super(message);
    }
}