package exceptions;

public class ColegioNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ColegioNotFoundException() {
        super();
    }

   

    // Eliminamos el booleano y dejamos solo el ID (el código del centro)
    public ColegioNotFoundException(String codigoCentro) {
        super("Error: No se ha encontrado el colegio con código: " + codigoCentro);
    }
}