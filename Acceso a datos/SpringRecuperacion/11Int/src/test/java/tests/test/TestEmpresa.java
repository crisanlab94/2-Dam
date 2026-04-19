package tests.test;


import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.modelo11Int._Int.Application;
import com.modelo11Int._Int.modelo.Departamento;
import com.modelo11Int._Int.modelo.Director;
import com.modelo11Int._Int.service.DirectorService;

import exceptions.DepartamentoNotFoundException;
import exceptions.DirectorNotFoundException;
import exceptions.DuplicateIdException;

public class TestEmpresa {
    private static final Logger logger = LoggerFactory.getLogger(TestEmpresa.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        DirectorService service = ctx.getBean(DirectorService.class);

        logger.info("======= PRUEBAS 1:1 ID MANUAL INT =======");

        try {
            // REGISTRO Y ASOCIACIÓN
            Director d1 = new Director(300, "Marta Master", 5500.0, LocalDate.of(2024, 1, 1));
            service.registrarDirector(d1);

            Departamento dep = new Departamento(99, "Sistemas", 90000.0);
            service.asociarDepartamento(300, dep);

            // --- TEST DE EXCEPCIONES ---
            logger.info(">>> TEST: Forzando excepciones propias...");

            // 1. Director inexistente
            try {
                service.findDirectorById(999);
            } catch (DirectorNotFoundException e) {
                logger.error("CAPTURADA OK: {}", e.getMessage());
            }

            // 2. ID Duplicado
            try {
                service.registrarDirector(new Director(300, "Otro", 1000, LocalDate.now()));
            } catch (DuplicateIdException e) {
                logger.error("CAPTURADA OK: {}", e.getMessage());
            }

        } catch (DirectorNotFoundException | DepartamentoNotFoundException e) {
            logger.error("ERROR DE BÚSQUEDA: {}", e.getMessage());
        } catch (DuplicateIdException e) {
            logger.error("ERROR DE REGISTRO: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("ERROR INESPERADO: {}", e.getMessage());
        }

        logger.info("======= FIN DE PRUEBAS =======");
    }
}