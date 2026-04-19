package tests.test;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.modeloNMIntAuto._IntAuto.Application;
import com.modeloNMIntAuto._IntAuto.modelo.Empleado;
import com.modeloNMIntAuto._IntAuto.modelo.EstadoProyecto;
import com.modeloNMIntAuto._IntAuto.modelo.Proyecto;
import com.modeloNMIntAuto._IntAuto.service.ProyectoService;

// IMPORTANTE: Importar tus excepciones propias
import exceptions.EmpleadoNotFoundException;
import exceptions.ProyectoNotFoundException;

public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        // 1. ARRANCAR EL CONTEXTO DE SPRING
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        ProyectoService service = ctx.getBean(ProyectoService.class);

        logger.info("==========================================================");
        logger.info("       INICIO DE PRUEBAS N:M (MANY-TO-MANY) AUTO-INT       ");
        logger.info("==========================================================");

        try {
            // --- BLOQUE 1: REGISTRO DE NUEVAS ENTIDADES ---
            logger.info(">>> TEST 1: Registrando nuevas entidades...");
            
            Proyecto pNuevo = new Proyecto("Migración Cloud", true, EstadoProyecto.PLANIFICACION, LocalDate.now().plusMonths(6));
            service.registrarProyecto(pNuevo);

            Empleado eNuevo = new Empleado("Alex Admin", true, 3000.0);
            service.registrarEmpleado(eNuevo);
            
            logger.info("Entidades creadas con éxito. Alex ID: {}, Proyecto ID: {}", eNuevo.getId(), pNuevo.getId());


            // --- BLOQUE 2: VINCULACIÓN MIXTA (NUEVO + EXISTENTE) ---
            logger.info(">>> TEST 2: Vinculando a Alex con proyectos...");
            
            // Alex (nuevo) en el proyecto Migración Cloud (nuevo)
            service.asignarEmpleadoAProyecto(eNuevo.getId(), pNuevo.getId());
            
            // Alex (nuevo) en el Sistema IA (existente - ID 1)
            service.asignarEmpleadoAProyecto(eNuevo.getId(), 1);


            // --- BLOQUE 3: VINCULACIÓN DE REGISTROS EXISTENTES ---
            logger.info(">>> TEST 3: Vinculando Juan (existente) con Web Corp (existente)");
            
            int idJuan = 2;       
            int idWebCorp = 2;    
            
            service.asignarEmpleadoAProyecto(idJuan, idWebCorp);


            // --- BLOQUE 4: PRUEBA DE FILTROS CRUZADOS ---
            logger.info(">>> TEST 4: Verificando filtros cruzados...");

            logger.info("Proyectos donde trabaja 'Alex Admin':");
            service.buscarProyectosDeEmpleado("Alex Admin").forEach(proj -> 
                logger.info(" - {} [Estado: {}]", proj.getTitulo(), proj.getEstado())
            );

            logger.info("Equipo trabajando en el 'Sistema IA':");
            service.buscarEmpleadosPorProyecto("Sistema IA").forEach(emp -> 
                logger.info(" - {}", emp.getNombre())
            );

            // --- BLOQUE 5: PRUEBA DE EXCEPCIONES PROPIAS (NEGATIVO) ---
            logger.info(">>> TEST 5: Forzando error de búsqueda para probar excepciones...");
            
            // Intentamos buscar un ID que sabemos que no existe
            service.findEmpleadoById(999);

        } catch (EmpleadoNotFoundException e) {
            // Captura específica de error en Empleados
            logger.error("CAPTURA ESPECÍFICA (EMPLEADO): {}", e.getMessage());
            
        } catch (ProyectoNotFoundException e) {
            // Captura específica de error en Proyectos
            logger.error("CAPTURA ESPECÍFICA (PROYECTO): {}", e.getMessage());
            
        } catch (Exception e) {
            // Captura de cualquier otro error (seguridad)
            logger.error("!!! ERROR INESPERADO: {}", e.getMessage());
            e.printStackTrace();
        }

        logger.info("==========================================================");
        logger.info("              FIN DE LAS PRUEBAS DE ACCESO A DATOS         ");
        logger.info("==========================================================");
    }
}