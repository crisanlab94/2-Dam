package controlador;
import java.time.LocalDate;
import org.apache.logging.log4j.*;
import modelo.*;
import servicio.ServicioCorporativo;

public class GestionaCorporativo {
    private static final Logger logger = LogManager.getLogger(GestionaCorporativo.class);

    public static void main(String[] args) {
        try {
            ServicioCorporativo servicio = new ServicioCorporativo();
            logger.info("--- INICIO GESTIÓN CORPORATIVA ---");

            // 1. ALTA NUEVA EMPRESA
            Empresa e1 = new Empresa(0, "SpaceX", Sector.TECNOLOGIA, LocalDate.now(), true);
            servicio.altaEmpresa(e1); // El ID se actualizará de 0 a 6 (aprox)

            // 2. CONTRATAR EMPLEADO
            Empleado emp1 = new Empleado(0, "Elon Musk", 9999.9, true);
            servicio.contratarEmpleado(emp1, e1.getId());

            // 3. VOLCADO COMPLETO (Ver toda la info)
            logger.info("\n=== INFORME GENERAL DE EMPRESAS ===");
            for (Empresa e : servicio.obtenerTodasHidratadas()) {
                logger.info("--------------------------------------------------");
                logger.info("EMPRESA: [{}] {} | Sector: {} | Multi: {} | Fundada: {}", 
                    e.getId(), e.getNombre(), e.getSector(), e.isEsMultinacional(), e.getFechaCreacion());
                for (Empleado emp : e.getPlantilla()) {
                    logger.info("   > EMPLEADO: {} | Sueldo: {}€ | Remoto: {}", 
                        emp.getNombre(), emp.getSalario(), emp.isEsRemoto());
                }
            }

            // 4. PRUEBA DE ERROR: ALTA DUPLICADA (Por nombre)
            logger.info("\n=== PRUEBA EXCEPCIÓN: NOMBRE REPETIDO ===");
            try {
                servicio.altaEmpresa(new Empresa(0, "TechNova", Sector.FINANZAS, LocalDate.now(), false));
            } catch (AppException ex) {
                logger.error("Controlado correctamente: " + ex.getMessage());
            }

            // 5. FILTRO AVANZADO
            logger.info("\n=== EMPRESAS TECNOLÓGICAS CON AL MENOS 1 EMPLEADO ===");
            for (Empresa grande : servicio.buscarEmpresasGrandesPorSector(Sector.TECNOLOGIA, 1)) {
                logger.info("Empresa detectada: " + grande.getNombre());
            }

            // 6. BORRAR
            servicio.liquidarEmpresa(2); // Borramos HealthSafe

        } catch (AppException e) {
            logger.fatal("ERROR SISTEMA: " + e.getMessage());
        }
    }
}