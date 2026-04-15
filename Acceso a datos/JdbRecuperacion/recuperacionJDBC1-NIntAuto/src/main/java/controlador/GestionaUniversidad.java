package controlador;
import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.*;
import modelo.*;
import servicio.ServicioUniversidad;

public class GestionaUniversidad {
    private static final Logger logger = LogManager.getLogger(GestionaUniversidad.class);

    public static void main(String[] args) {
        try {
            ServicioUniversidad servicio = new ServicioUniversidad();
            logger.info("--- 1. INICIANDO SISTEMA UNIVERSITARIO ---");

            // VOLCADO TOTAL (Atributos de ambas tablas)
            logger.info("\n=== 2. LISTADO COMPLETO (HIDRATADO) ===");
            for (Departamento d : servicio.obtenerTodoHidratado()) {
                logger.info("DEP: [{}] {} | Fundado: {} | Oficial: {}", 
                    d.getId(), d.getNombre(), d.getFechaFundacion(), d.isEsOficial());
                for (Profesor p : d.getProfesores()) {
                    logger.info("   > PROF: {} | DNI: {} | Rol: {} | Salario: {}€",
                        p.getNombre(), p.getDni(), p.getEspecialidad(), p.getSalario());
                }
            }

            // TOP 3
            logger.info("\n=== 3. RANKING TOP 3 SALARIOS ===");
            for (Profesor p : servicio.obtenerTop3Sueldos()) {
                logger.info("Sueldo: " + p.getSalario() + "€ | Prof: " + p.getNombre());
            }

            // EXCEPCIÓN ALTA DUPLICADA
            logger.info("\n=== 4. PRUEBA EXCEPCIÓN: ID DUPLICADO ===");
            try {
                servicio.altaDepartamento(new Departamento("DEP-MAT", "Fallo", 0, LocalDate.now(), true));
            } catch (AppException e) {
                logger.error("CONTROLADO: " + e.getMessage());
            }

            // BORRADO
            servicio.eliminarDepartamento("DEP-FIS");
            logger.info("\n=== 5. SIMULACIÓN FINALIZADA ===");

        } catch (AppException e) {
            logger.fatal("FATAL: " + e.getMessage());
        }
    }
}