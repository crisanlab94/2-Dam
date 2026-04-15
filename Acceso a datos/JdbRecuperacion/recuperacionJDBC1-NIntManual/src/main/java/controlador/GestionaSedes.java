package controlador;
import java.time.LocalDate;
import org.apache.logging.log4j.*;
import modelo.*;
import servicio.ServicioSede;

public class GestionaSedes {
    private static final Logger logger = LogManager.getLogger(GestionaSedes.class);

    public static void main(String[] args) {
        try {
            ServicioSede servicio = new ServicioSede();
            logger.info("--- INICIANDO CONTROL DE SEDES ---");

            // ALTA MANUAL
            servicio.darAltaSede(new Sede(60, "Sede Remota", LocalDate.now(), true));

            // VOLCADO TOTAL
            logger.info("\n=== LISTADO COMPLETO (ID INT MANUAL) ===");
            for (Sede s : servicio.obtenerTodoHidratado()) {
                logger.info("--------------------------------------------------");
                logger.info("SEDE: [{}] {} | Fundada: {} | Internacional: {}", 
                    s.getId(), s.getNombre(), s.getFechaApertura(), s.isEsInternacional());
                for (Sala sa : s.getListaSalas()) {
                    logger.info("   > SALA: [{}] {} | Tipo: {} | Capacidad: {}", 
                        sa.getId(), sa.getNombre(), sa.getTipo(), sa.getCapacidad());
                }
            }

            // EXCEPCIÓN ID REPETIDO
            logger.info("\n=== PRUEBA EXCEPCIÓN: ID MANUAL REPETIDO ===");
            try {
                servicio.darAltaSede(new Sede(10, "Fallo", LocalDate.now(), false));
            } catch (AppException e) {
                logger.error("Captura correcta: " + e.getMessage());
            }

            // TOP 3 CAPACIDAD
            logger.info("\n=== TOP 3 SALAS POR CAPACIDAD ===");
            for (Sala sa : servicio.obtenerTop3Capacidad()) {
                logger.info("Sala: " + sa.getNombre() + " (" + sa.getCapacidad() + " personas)");
            }

            servicio.eliminarSedeCompleta(20);

        } catch (AppException e) {
            logger.fatal("ERROR: " + e.getMessage());
        }
    }
}
