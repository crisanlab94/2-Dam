package tests.test;



import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.modelo11String._String.Application; // Asegúrate de que esta sea tu clase @SpringBootApplication
import com.modelo11String._String.modelo.Carnet;
import com.modelo11String._String.modelo.Genero;
import com.modelo11String._String.modelo.Socio;
import com.modelo11String._String.service.SocioService;

import exceptions.DniInvalidoException;
import exceptions.DuplicateIdException;
import exceptions.SocioNotFoundException;

public class TestClubDeportivo {
    private static final Logger logger = LoggerFactory.getLogger(TestClubDeportivo.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        SocioService service = ctx.getBean(SocioService.class);

        logger.info("======= PRUEBAS CLUB DEPORTIVO (1:1 STRING) =======");

        try {
            // 1. Registro Correcto
            Socio s1 = new Socio("12345678Z", "Cristina Pro", LocalDate.of(1995, 5, 20), true, Genero.FEMENINO);
            service.registrarSocio(s1);

            // 2. Asociación Carnet
            Carnet carnet = new Carnet("BARCODE-001", LocalDateTime.now(), 45.50);
            service.asociarCarnet("12345678Z", carnet);
            logger.info("Socio y Carnet vinculados.");

            // --- PRUEBAS DE EXCEPCIONES (NEGATIVAS) ---

            // TEST A: DNI Inválido
            logger.info(">>> Provocando DniInvalidoException...");
            try {
                service.registrarSocio(new Socio("123", "Error", LocalDate.now(), true, Genero.OTRO));
            } catch (DniInvalidoException e) {
                logger.error("CAPTURADA OK: {}", e.getMessage());
            }

            // TEST B: Socio No Encontrado
            logger.info(">>> Provocando SocioNotFoundException...");
            try {
                service.findSocioByDni("00000000A");
            } catch (SocioNotFoundException e) {
                logger.error("CAPTURADA OK: {}", e.getMessage());
            }

            // TEST C: ID Duplicado
            logger.info(">>> Provocando DuplicateIdException...");
            try {
                service.registrarSocio(new Socio("12345678Z", "Repetida", LocalDate.now(), true, Genero.FEMENINO));
            } catch (DuplicateIdException e) {
                logger.error("CAPTURADA OK: {}", e.getMessage());
            }

        } catch (SocioNotFoundException | DniInvalidoException | DuplicateIdException e) {
            logger.error("ERROR ESPECÍFICO: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("ERROR INESPERADO: {}", e.getMessage());
        }

        logger.info("======= FIN DE LAS PRUEBAS =======");
    }
}