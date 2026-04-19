package exceptions;

/**
 * Excepción para cuando un Estudiante no existe en la base de datos.
 * Adaptada para IDs tipo String (DNI).
 */
public class EstudianteNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EstudianteNotFoundException() {
        super();
    }

   

    public EstudianteNotFoundException(String dni) {
        super("Error: No se ha encontrado al estudiante con DNI: " + dni);
    }
}