package servicio;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Curso;
import modelo.Estudiante;
import modelo.Nivel;
import repositorio.RepositorioCurso;
import repositorio.RepositorioEstudiante;

public class ServicioAcademico {
    private static final Logger logger = LogManager.getLogger(ServicioAcademico.class);
    
    private final RepositorioEstudiante repoEstudiante;
    private final RepositorioCurso repoCurso;

    public ServicioAcademico(RepositorioEstudiante repoEstudiante, RepositorioCurso repoCurso) {
        this.repoEstudiante = repoEstudiante;
        this.repoCurso = repoCurso;
    }

    /**
     * MATRICULAR: La joya de la corona del examen N:M
     */
    public void matricularAlumno(int idCurso, int idEstudiante) throws AppException {
        // 1. Buscamos a los protagonistas en la caché
        Curso curso = repoCurso.buscarPorId(idCurso);
        Estudiante estudiante = repoEstudiante.buscarPorId(idEstudiante);

        // 2. VALIDACIONES DE SEGURIDAD
        if (curso == null || estudiante == null) {
            throw new AppException("Error: El curso o el estudiante no existen en el sistema.");
        }

        // 3. REGLA 1: Cupo máximo (Ejemplo: 5 alumnos por curso)
        if (curso.getListaAlumnos().size() >= 5) {
            logger.warn("Intento de matrícula fallido: Curso {} lleno.", curso.getTitulo());
            throw new AppException("Cupo alcanzado: El curso " + curso.getTitulo() + " ya tiene 5 alumnos.");
        }

        // 4. REGLA 2: No repetir matrícula (Evitar el error de PK duplicada en SQL)
        for (Estudiante e : curso.getListaAlumnos()) {
            if (e.getId() == idEstudiante) {
                throw new AppException("Aviso: El alumno " + estudiante.getNombre() + " ya está en este curso.");
            }
        }

        // 5. REGLA 3: Restricción de Nivel
        // Si el título del curso contiene "AVANZADO", el alumno no puede ser "BASICO"
        if (curso.getTitulo().toUpperCase().contains("AVANZADO") && estudiante.getNivel() == Nivel.BASICO) {
            throw new AppException("Nivel insuficiente: " + estudiante.getNombre() + " debe subir de nivel antes de entrar aquí.");
        }

        // 6. TODO OK: Mandamos la orden al repositorio
        repoCurso.registrarMatricula(idCurso, estudiante);
        
        logger.info("ÉXITO: {} matriculado en {}", estudiante.getNombre(), curso.getTitulo());
    }

    /**
     * CONSULTA: Obtener informe de ocupación
     */
    public void mostrarEstadoCursos() {
        logger.info("=== ESTADO DE LAS AULAS ESTELARES ===");
        for (Curso c : repoCurso.getCursos()) {
            logger.info("Curso: {} | Alumnos: {}/5", c.getTitulo(), c.getListaAlumnos().size());
            for (Estudiante e : c.getListaAlumnos()) {
                logger.info("   - {} ({})", e.getNombre(), e.getNivel());
            }
        }
    }
    
    public void mostrarExpedienteAlumnos() {
        List<Estudiante> todosLosAlumnos = repoEstudiante.getEstudiantes();
        List<Curso> todosLosCursos = repoCurso.getCursos();

        logger.info("========== EXPEDIENTE ACADÉMICO DE ESTUDIANTES ==========");

        for (Estudiante e : todosLosAlumnos) {
            logger.info("👨‍🎓 ALUMNO: {} | Nivel: {}", e.getNombre(), e.getNivel());
            
            boolean matriculadoEnAlgo = false;
            
            // Buscamos en qué cursos aparece este estudiante
            for (Curso c : todosLosCursos) {
                // Revisamos la lista de alumnos de cada curso
                for (Estudiante alumnoEnCurso : c.getListaAlumnos()) {
                    if (alumnoEnCurso.getId() == e.getId()) {
                        logger.info("   -> Inscrito en: [{}] | Inicia: {}", 
                                    c.getTitulo(), c.getFechaInicio());
                        matriculadoEnAlgo = true;
                        break; // Ya lo encontramos en este curso, pasamos al siguiente curso
                    }
                }
            }

            if (!matriculadoEnAlgo) {
                logger.info("   -> (Sin matrículas activas)");
            }
        }
        logger.info("=========================================================");
    }
}