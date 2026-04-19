package tests.test;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.modelo1NString._String.Application;
import com.modelo1NString._String.modelo.Colegio;
import com.modelo1NString._String.modelo.Estudiante;
import com.modelo1NString._String.modelo.Genero;
import com.modelo1NString._String.service.ColegioService;

// IMPORTS DE TUS EXCEPCIONES
import exceptions.ColegioNotFoundException;
import exceptions.EstudianteNotFoundException;
import exceptions.DuplicateIdException;

public class TestColegio {
    private static final Logger logger = LoggerFactory.getLogger(TestColegio.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        ColegioService service = ctx.getBean(ColegioService.class);

        logger.info("======= INICIO TEST 1:N FILTROS TOTALES (ID STRING) =======");

        try {
            // 1. Registro e ingreso
            Colegio nuevoCol = new Colegio("IES-MAD", "IES Madrid", true, LocalDate.of(2000, 5, 20));
            service.registrarColegio(nuevoCol);

            Estudiante e1 = new Estudiante("444X", "Cristina", true, Genero.FEMENINO, LocalDate.now());
            service.registrarEstudiante(e1);
            service.asignarEstudiante("444X", "IES-MAD");

            // 2. Probar Filtros
            logger.info(">>> TEST: Mujeres activas registradas");
            service.buscarMujeresActivas().forEach(m -> logger.info(" - Alumna: {}", m.getNombre()));

            // --- BLOQUE DE PRUEBA DE ERRORES ---
            logger.info(">>> TEST: Forzando excepciones propias...");
            
            // Forzar duplicado
            try {
                service.registrarColegio(new Colegio("IES-MAD", "Duplicado", true, LocalDate.now()));
            } catch (DuplicateIdException e) {
                logger.error("CAPTURADA DUPLICADO: {}", e.getMessage());
            }

            // Forzar No Encontrado
            try {
                service.findEstudianteById("9999Z");
            } catch (EstudianteNotFoundException e) {
                logger.error("CAPTURADA NO ENCONTRADO: {}", e.getMessage());
            }

        } catch (ColegioNotFoundException | EstudianteNotFoundException e) {
            logger.error("ERROR ESPECÍFICO DE BÚSQUEDA: {}", e.getMessage());
        } catch (DuplicateIdException e) {
            logger.error("ERROR ESPECÍFICO DE REGISTRO: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("ERROR GENÉRICO EN TEST: {}", e.getMessage());
        }
        
        logger.info("======= FIN DE PRUEBAS =======");
    }
}