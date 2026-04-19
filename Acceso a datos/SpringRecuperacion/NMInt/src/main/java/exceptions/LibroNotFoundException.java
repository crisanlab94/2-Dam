package exceptions;



public class LibroNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public LibroNotFoundException() { super(); }
    public LibroNotFoundException(int id) {
        super("Error: No se ha encontrado el libro con ID: " + id);
    }
}
