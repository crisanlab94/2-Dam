package tests.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Importaciones para el Logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.modelo11IntAuto._IntAuto.Application;
import com.modelo11IntAuto._IntAuto.modelo.Expediente;
import com.modelo11IntAuto._IntAuto.modelo.Genero;
import com.modelo11IntAuto._IntAuto.modelo.Paciente;
import com.modelo11IntAuto._IntAuto.service.PacienteService;

import exceptions.ExpedienteNotFoundException;
import exceptions.PacienteNotFoundException;


public class TestGestionaPacientes {
    private static final Logger logger = LoggerFactory.getLogger(TestGestionaPacientes.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        PacienteService service = ctx.getBean(PacienteService.class);

        logger.info("======= PRUEBAS R4: GESTIÓN DE PACIENTES (1:1 AUTO) =======");

        try {
            // 1. REGISTRO
            Paciente p1 = new Paciente("Cristina Garcia", LocalDate.of(1995, 5, 20), true, Genero.FEMENINO);
            service.crearPaciente(p1);
            logger.info("Paciente creado. ID generado: {}", p1.getId());

            // 2. ASOCIACIÓN 1:1
            Expediente exp = new Expediente("Historial limpio", 65.5, 1.70, LocalDateTime.now());
            service.agregarExpediente(p1.getId(), exp);

            // --- BLOQUE DE PRUEBA DE EXCEPCIONES ---
            logger.info(">>> TEST: Forzando búsqueda de ID inexistente (999L)...");
            try {
                service.findPacienteById(999L);
            } catch (PacienteNotFoundException e) {
                // Captura específica de tu excepción
                logger.error("CAPTURA CORRECTA: {}", e.getMessage());
            }

        } catch (PacienteNotFoundException | ExpedienteNotFoundException e) {
            // Errores de negocio conocidos
            logger.error("ERROR DE APLICACIÓN: {}", e.getMessage());
            
        } catch (Exception e) {
            // Error técnico no controlado (DB caída, null pointer, etc.)
            logger.error("ERROR TÉCNICO INESPERADO: {}", e.getMessage());
            e.printStackTrace();
        }

        logger.info("======= FIN DE LAS PRUEBAS R4 =======");
    }
}