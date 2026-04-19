package exceptions;

/**
 * Excepción para cuando un Hospital no existe en la base de datos.
 * Adaptada para IDs tipo Integer (Manual).
 */
public class HospitalNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HospitalNotFoundException() {
        super();
    }

    public HospitalNotFoundException(String message) {
        super(message);
    }

    public HospitalNotFoundException(int id) {
        super("Error: No se ha encontrado el hospital con ID: " + id);
    }
}
