package exceptions;



/**
 * Excepción para cuando un Proyecto no existe en la base de datos.
 * Adaptada al estilo del profesor para IDs tipo Integer (Auto-incremento).
 */
public class ProyectoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 1. Constructor vacío
    public ProyectoNotFoundException() {
        super();
    }

    // 2. Constructor para mensaje personalizado
    public ProyectoNotFoundException(String message) {
        super(message);
    }

    // 3. Constructor específico para el ID int
    public ProyectoNotFoundException(int id) {
        super("Error: No se ha encontrado el proyecto con ID: " + id);
    }
}
