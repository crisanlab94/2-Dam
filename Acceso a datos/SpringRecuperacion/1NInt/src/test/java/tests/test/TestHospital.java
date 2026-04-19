package tests.test;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.modelo1NInt._Int.Application;
import com.modelo1NInt._Int.modelo.Especialidad;
import com.modelo1NInt._Int.modelo.Hospital;
import com.modelo1NInt._Int.modelo.Medico;
import com.modelo1NInt._Int.service.HospitalService;

// IMPORTS DE TUS EXCEPCIONES
import exceptions.HospitalNotFoundException;
import exceptions.MedicoNotFoundException;
import exceptions.DuplicateIdException;

public class TestHospital {
    private static final Logger logger = LoggerFactory.getLogger(TestHospital.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        HospitalService service = ctx.getBean(HospitalService.class);

        logger.info("======= PRUEBAS 1:N INT MANUAL CON EXCEPCIONES =======");

        try {
            // 1. Registro Manual
            service.registrarHospital(new Hospital(303, "Hospital del Mar", true, LocalDate.of(1995, 1, 1)));
            service.registrarMedico(new Medico(50, "Dr. Zoidberg", true, Especialidad.PSIQUIATRIA, LocalDate.now()));
            service.asignarMedicoAHospital(50, 303);

            // 2. Probar Filtros
            logger.info(">>> TEST: Médicos en sanidad privada");
            List<Medico> privados = service.buscarMedicosEnHospitalesPrivados();
            privados.forEach(p -> logger.info(" - {} en hospital {}", p.getNombre(), p.getHospital().getNombre()));

            // --- BLOQUE DE PRUEBAS NEGATIVAS ---
            logger.info(">>> TEST: Probando excepciones propias...");

            // Forzar ID Duplicado
            try {
                service.registrarHospital(new Hospital(303, "Duplicado", false, LocalDate.now()));
            } catch (DuplicateIdException e) {
                logger.error("CAPTURADA DUPLICADO: {}", e.getMessage());
            }

            // Forzar No Encontrado
            try {
                service.findMedicoById(999);
            } catch (MedicoNotFoundException e) {
                logger.error("CAPTURADA NO ENCONTRADO: {}", e.getMessage());
            }

        } catch (HospitalNotFoundException | MedicoNotFoundException e) {
            logger.error("ERROR DE BÚSQUEDA: {}", e.getMessage());
        } catch (DuplicateIdException e) {
            logger.error("ERROR DE REGISTRO: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("ERROR GENÉRICO: {}", e.getMessage());
        }
        
        logger.info("======= FIN DE PRUEBAS =======");
    }
}