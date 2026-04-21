package tests.test;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.modelo3EntidadesSpring._3EntidadesSpring.Application;
import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Cargo;
import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Departamento;
import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Empleado;
import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Proyecto;
import com.modelo3EntidadesSpring._3EntidadesSpring.service.EmpresaService;

import exceptions.EmpleadoNotFoundException;

@SpringBootApplication
public class TestEmpresa {
    private static final Logger logger = LoggerFactory.getLogger(TestEmpresa.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        EmpresaService service = ctx.getBean(EmpresaService.class);

        logger.info("======= INICIO TEST COMPLETO: 3 ENTIDADES =======");

        try {
            // 1. REGISTRO (IDs null porque son autogenerados)
            Departamento depto = service.crearDepartamento(new Departamento(null, "Desarrollo"));
            Proyecto proy = service.crearProyecto(new Proyecto(null, "App Android", 30000.0, true));
            Empleado cris = service.crearEmpleado(new Empleado(null, "Cristina", 4000.0, true, Cargo.MANAGER, LocalDate.now()));

            // 2. VINCULACIÓN
            service.asignarEmpleadoADepartamento(cris.getIdEmp(), depto.getIdDept());
            service.vincularEmpleadoAProyecto(cris.getIdEmp(), proy.getIdProy());
            logger.info("✅ Cristina vinculada a Desarrollo y al proyecto Android.");

            // 3. FILTROS TRADICIONALES (Bucle For)
            List<Empleado> lista = service.obtenerTodosLosEmpleados();
            logger.info(">>> TEST: Filtrando empleados becados...");
            for (Empleado e : lista) {
                if (e.isBecado()) {
                    logger.info(" - Becado encontrado: {}", e.getNombre());
                }
            }

            // 4. FORZAR ERROR DE BÚSQUEDA
            try {
                service.buscarEmpleadoPorId(999L);
            } catch (EmpleadoNotFoundException e) {
                logger.error("CAPTURADO: {}", e.getMessage());
            }

        } catch (Exception e) {
            logger.error("ERROR NO ESPERADO: {}", e.getMessage());
        }

        logger.info("======= FIN DE PRUEBAS =======");
    }
}