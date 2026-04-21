package exceptions;

public class DepartamentoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DepartamentoNotFoundException() {
        super();
    }

   

    // Eliminamos el booleano y dejamos solo el ID (el código del centro)
    public DepartamentoNotFoundException(String codigoCentro) {
        super("Error: No se ha encontrado el colegio con código: " + codigoCentro);
    }
}