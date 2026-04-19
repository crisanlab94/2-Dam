package tests.test;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.modelo1NIntAuto._IntAuto.Application;
import com.modelo1NIntAuto._IntAuto.modelo.*;
import com.modelo1NIntAuto._IntAuto.service.EmpresaService;

// IMPORTS DE TUS EXCEPCIONES
import exceptions.EmpresaNotFoundException;
import exceptions.EmpleadoNotFoundException;

public class TestEmpresa {
    private static final Logger logger = LoggerFactory.getLogger(TestEmpresa.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        EmpresaService service = ctx.getBean(EmpresaService.class);

        logger.info("--- INICIO TEST TOTAL AUTO-INCREMENT ---");

        try {
            // 1. Registro normal
            Empresa e1 = new Empresa("Ciber-Sec", "Malaga", 50.0, false, LocalDate.of(2020, 1, 1));
            Empresa guardada = service.registrarEmpresa(e1);
            logger.info("Empresa guardada con ID: {}", guardada.getId());

            Empleado emp = new Empleado("Cristina Pro", 4000.0, true, Puesto.DIRECTOR, LocalDate.now());
            Empleado empGuardado = service.registrarEmpleado(emp);
            logger.info("Empleado guardado con ID: {}", empGuardado.getId());

            service.contratarEmpleado(empGuardado.getId(), guardada.getId());

            // 2. PRUEBA NEGATIVA: Forzar búsqueda de ID inexistente
            logger.info(">>> TEST: Probando excepciones propias...");
            try {
                service.findEmpresaById(999);
            } catch (EmpresaNotFoundException ex) {
                logger.error("CAPTURADA: {}", ex.getMessage());
            }

        } catch (EmpresaNotFoundException | EmpleadoNotFoundException e) {
            logger.error("ERROR ESPECÍFICO: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("ERROR INESPERADO: {}", e.getMessage());
        }
        
        logger.info("--- FIN DE LAS PRUEBAS ---");
    }
}