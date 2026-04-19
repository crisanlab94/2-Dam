package exceptions;

public class CursoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CursoNotFoundException() { super(); }

    public CursoNotFoundException(String codigo) {
        super("Error: No se ha encontrado el curso con código: " + codigo);
    }
}
