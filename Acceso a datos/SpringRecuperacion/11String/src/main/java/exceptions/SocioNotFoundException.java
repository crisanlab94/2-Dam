package exceptions;

/**
 * Excepción para cuando un Socio no existe en la base de datos.
 * Adaptada al estilo del profesor para IDs tipo String (DNI).
 */
public class SocioNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5030665213865363481L;

    // 1. Constructor vacío (Obligatorio por estructura)
    public SocioNotFoundException() {
        super();
    }
    
    // 2. Constructor para mensaje personalizado
    // Este es el que usaremos para pasar el mensaje del DNI desde el Service
    public SocioNotFoundException(String message) {
        super(message);
    }
}