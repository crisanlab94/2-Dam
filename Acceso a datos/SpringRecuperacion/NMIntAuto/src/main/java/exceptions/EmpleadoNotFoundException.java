package exceptions;

/**
 * Excepción para cuando un Empleado no existe en la base de datos.
 * Adaptada al estilo del profesor para IDs tipo Integer (Auto-incremento).
 */
public class EmpleadoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 1. Constructor vacío (Obligatorio por estructura)
    public EmpleadoNotFoundException() {
        super();
    }

    // 2. Constructor para mensaje personalizado
    public EmpleadoNotFoundException(String message) {
        super(message);
    }

    // 3. Constructor específico para el ID int
    public EmpleadoNotFoundException(int id) {
        super("Error: No se ha encontrado el empleado con ID: " + id);
    }
}