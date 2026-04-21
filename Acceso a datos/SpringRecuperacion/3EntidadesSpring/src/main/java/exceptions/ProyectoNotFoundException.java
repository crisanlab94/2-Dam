package exceptions;

public class ProyectoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProyectoNotFoundException() {
        super();
    }

   

    // Eliminamos el booleano y dejamos solo el ID (el código del centro)
    public ProyectoNotFoundException(String codigoCentro) {
        super("Error: No se ha encontrado el colegio con código: " + codigoCentro);
    }
}