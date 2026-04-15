package controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import repositorio.RepositorioCurso;
import repositorio.RepositorioEstudiante;
import servicio.ServicioAcademico;
import util.MySqlConector;

public class GestionaAcademia {
    private static final Logger logger = LogManager.getLogger(GestionaAcademia.class);

    public static void main(String[] args) {
        
        try {
            // 1. INICIALIZACIÓN DE CAPAS
            MySqlConector conector = new MySqlConector();
            RepositorioEstudiante repoE = new RepositorioEstudiante(conector);
            RepositorioCurso repoC = new RepositorioCurso(conector);
            ServicioAcademico servicio = new ServicioAcademico(repoE, repoC);

            logger.info("=== AGENCIA ACADÉMICA ESPACIAL: SISTEMA INICIADO ===");

            // 2. PRUEBA: MATRÍCULA EXITOSA
            // Matriculamos a Din Djarin (ID 5, INTERMEDIO) en Navegación (ID 1)
            logger.info("--- Prueba 1: Matrícula estándar ---");
            servicio.matricularAlumno(1, 5); 

            // 3. PRUEBA: ERROR DE NIVEL
            // Intentamos meter a Han Solo (ID 3, BASICO) en un curso AVANZADO (ID 3)
            logger.info("--- Prueba 2: Validación de Nivel ---");
            try {
                servicio.matricularAlumno(3, 3); 
            } catch (AppException e) {
                logger.error("CAPTURA PREVISTA: {}", e.getMessage());
            }

            // 4. PRUEBA: ERROR DE CUPO (MÁXIMO 5)
            // El curso 1 ya tiene 3 alumnos del SQL + 1 que hemos metido = 4.
            // Vamos a intentar meter a dos más para que salte el límite en el segundo.
            logger.info("--- Prueba 3: Límite de cupo (Máx 5) ---");
            try {
                servicio.matricularAlumno(1, 4); // El 5º entra bien
                logger.info("Quinta matrícula realizada.");
                
                servicio.matricularAlumno(1, 2); // El 6º debería petar (Leia ya está, pero imaginemos ID diferente o duplicado)
            } catch (AppException e) {
                logger.error("CAPTURA PREVISTA: {}", e.getMessage());
            }

            // 5. PRUEBA: MATRÍCULA DUPLICADA
            logger.info("--- Prueba 4: Evitar duplicados ---");
            try {
                servicio.matricularAlumno(2, 1); // Luke ya está en el curso 2 según el SQL
            } catch (AppException e) {
                logger.error("CAPTURA PREVISTA: {}", e.getMessage());
            }

         

            
         // 6. ESTADO DE CURSOS (Relación Curso -> Alumnos)
            logger.info("--- GENERANDO INFORME DE OCUPACIÓN ---");
            servicio.mostrarEstadoCursos();

            // 7. EXPEDIENTE DE ESTUDIANTES (Relación Alumno -> Cursos)
            logger.info("--- GENERANDO INFORME POR ESTUDIANTE ---");
            servicio.mostrarExpedienteAlumnos();
            
            
        } catch (AppException e) {
            logger.error("Error crítico en la lógica: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Error inesperado del sistema: ", e);
        }
    }
}
