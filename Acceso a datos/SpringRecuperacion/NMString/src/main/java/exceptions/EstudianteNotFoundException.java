package exceptions;

public class EstudianteNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EstudianteNotFoundException() { super(); }
    
    public EstudianteNotFoundException(String dni) {
        super("Error: No se ha encontrado el estudiante con DNI: " + dni);
    }
}