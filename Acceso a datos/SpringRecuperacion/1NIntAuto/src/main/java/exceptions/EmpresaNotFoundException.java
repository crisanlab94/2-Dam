package exceptions;

/**
 * Excepción para cuando una Empresa no existe en la base de datos.
 * Adaptada para IDs tipo Integer (Auto-increment).
 */
public class EmpresaNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmpresaNotFoundException() {
        super();
    }

    public EmpresaNotFoundException(String message) {
        super(message);
    }

    public EmpresaNotFoundException(int id) {
        super("Error: No se ha encontrado la empresa con ID: " + id);
    }
}