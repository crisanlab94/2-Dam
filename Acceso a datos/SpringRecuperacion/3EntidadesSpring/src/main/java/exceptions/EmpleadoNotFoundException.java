package exceptions;

/**
 * Excepción para cuando un Estudiante no existe en la base de datos.
 * Adaptada para IDs tipo String (DNI).
 */
public class EmpleadoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmpleadoNotFoundException() {
        super();
    }

   

    public EmpleadoNotFoundException(String dni) {
        super("Error: No se ha encontrado al estudiante con DNI: " + dni);
    }
}