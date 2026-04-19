package tests.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.modeloNMString._String.Application;
import com.modeloNMString._String.modelo.Curso;
import com.modeloNMString._String.modelo.Estudiante;
import com.modeloNMString._String.service.AcademiaService;

// IMPORTANTE: Importar todas tus excepciones
import exceptions.CursoNotFoundException;
import exceptions.DuplicateIdException;
import exceptions.EstudianteNotFoundException;

public class TestAcademia {

    private static final Logger logger = LoggerFactory.getLogger(TestAcademia.class);

    public static void main(String[] args) {
        
        // 1. INICIO DEL CONTEXTO
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        AcademiaService service = ctx.getBean(AcademiaService.class);

        logger.info("==========================================================");
        logger.info("   INICIO DE PRUEBAS TOTALES N:M (ID MANUAL STRING)       ");
        logger.info("==========================================================");

        try {
            // --- BLOQUE 1: REGISTRO DE ENTIDADES (ID MANUAL) ---
            logger.info(">>> TEST 1: Registro de Estudiantes y Cursos");
            
            Estudiante e1 = new Estudiante("11111111A", "Cristina Pro", true);
            Estudiante e2 = new Estudiante("22222222B", "Juan Senior", false);
            service.registrarEstudiante(e1);
            service.registrarEstudiante(e2);

            service.registrarCurso(new Curso("JAVA-01", "Java Spring Boot", true));
            service.registrarCurso(new Curso("WEB-01", "Angular & Frontend", true));
            service.registrarCurso(new Curso("DATA-01", "Big Data Python", false));
            
            logger.info("Datos iniciales guardados con éxito.");

            // --- BLOQUE 2: MATRICULACIÓN (RELACIÓN N:M) ---
            logger.info(">>> TEST 2: Matriculación cruzada");
            
            // Cristina en Java y Web
            service.matricular("11111111A", "JAVA-01");
            service.matricular("11111111A", "WEB-01");
            
            // Juan solo en Java
            service.matricular("22222222B", "JAVA-01");
            
            logger.info("Vínculos N:M establecidos en la tabla intermedia.");

            // --- BLOQUE 3: FILTROS DE NAVEGACIÓN (Naming Convention) ---
            logger.info(">>> TEST 3: Probando filtros de navegación entre tablas");

            // 3.1. Buscar alumnos de un curso
            logger.info("Alumnos matriculados en 'Java Spring Boot':");
            service.obtenerBecadosPorCurso("Java").forEach(est -> 
                logger.info(" - Estudiante: {} [Becado: {}]", est.getNombre(), est.isBecado())
            );

            // 3.2. Buscar cursos de un alumno
            logger.info("Cursos donde participa 'Cristina Pro':");
            service.obtenerCursosDeAlumno("Cristina").forEach(cur -> 
                logger.info(" - Curso: {} [Código: {}]", cur.getTitulo(), cur.getCodigoCurso())
            );

            // 3.3. Búsqueda simple por nombre
            List<Estudiante> filtrados = service.buscarEstudiantesPorNombre("Juan");
            logger.info("Estudiantes que se llaman Juan: {}", filtrados.size());

            // --- BLOQUE 4: ACTUALIZACIÓN Y BORRADO ---
            logger.info(">>> TEST 4: Mantenimiento (Update y Delete)");
            
            service.modificarNombreEstudiante("22222222B", "Juan Modificado");
            logger.info("Nombre del ID 22222222B actualizado.");

            // Borramos al alumno Juan (se deberían borrar sus vínculos en la intermedia)
            service.eliminarEstudiante("22222222B");
            logger.info("Estudiante Juan eliminado.");

        } catch (Exception e) {
            logger.error("ERROR INESPERADO EN FLUJO PRINCIPAL: {}", e.getMessage());
            e.printStackTrace();
        }

        // --- BLOQUE 5: PRUEBAS DE EXCEPCIONES PROPIAS (NEGATIVAS) ---
        logger.info(">>> TEST 5: Verificando robustez (Excepciones)");

        // A. Prueba de No Encontrado
        try {
            logger.info("Buscando DNI inexistente (99999999Z)...");
            service.findEstudianteById("99999999Z");
        } catch (EstudianteNotFoundException e) {
            logger.error("CAPTURA OK: {}", e.getMessage());
        }

        // B. Prueba de Curso No Encontrado
        try {
            logger.info("Buscando Curso inexistente (PHP-01)...");
            service.findCursoById("PHP-01");
        } catch (CursoNotFoundException e) {
            logger.error("CAPTURA OK: {}", e.getMessage());
        }

        // C. Prueba de Duplicado (ID String Manual)
        try {
            logger.info("Intentando registrar DNI ya existente (11111111A)...");
            service.registrarEstudiante(new Estudiante("11111111A", "Clon", false));
        } catch (DuplicateIdException e) {
            logger.error("CAPTURA OK: {}", e.getMessage());
        }

        logger.info("==========================================================");
        logger.info("            FIN DE TODAS LAS PRUEBAS R4                   ");
        logger.info("==========================================================");
    }
}
