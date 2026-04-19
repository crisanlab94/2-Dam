package exceptions;

/**
 * Excepción para cuando un Empleado no existe en la base de datos.
 * Adaptada para IDs tipo Integer (Auto-increment).
 */
public class EmpleadoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmpleadoNotFoundException() {
        super();
    }

    public EmpleadoNotFoundException(String message) {
        super(message);
    }

    public EmpleadoNotFoundException(int id) {
        super("Error: No se ha encontrado el empleado con ID: " + id);
    }
}