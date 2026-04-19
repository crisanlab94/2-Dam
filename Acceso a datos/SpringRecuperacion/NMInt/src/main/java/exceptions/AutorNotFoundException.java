package exceptions;



public class AutorNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public AutorNotFoundException() { super(); }
    public AutorNotFoundException(int id) {
        super("Error: No se ha encontrado el autor con ID: " + id);
    }
}
