package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Curso;
import modelo.Estudiante;
import modelo.Nivel;
import util.MySqlConector;

public class RepositorioCurso {
    private static final Logger logger = LogManager.getLogger(RepositorioCurso.class);
    private final MySqlConector mysqlConector;
    private List<Curso> cursos;

    public RepositorioCurso(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        this.cursos = this.read();
    }

    /**
     * READ N:M: Lee los cursos y, por cada uno, busca a sus alumnos en la tabla intermedia.
     */
    public List<Curso> read() throws AppException {
        List<Curso> listaBD = new ArrayList<>();
        String sqlCursos = "SELECT * FROM sandovalcristinaCurso";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sqlCursos);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso();
                c.setId(rs.getInt("id"));
                c.setTitulo(rs.getString("titulo"));
                c.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());

                // LLAMADA CLAVE: Buscamos los alumnos de este curso en la tabla intermedia
                c.setListaAlumnos(cargarAlumnosDeCurso(c.getId(), conn));
                
                listaBD.add(c);
            }
            logger.info("Cursos e inscripciones sincronizados con éxito.");
        } catch (SQLException e) {
            throw new AppException("Error al sincronizar cursos: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return listaBD;
    }

    /**
     * MÉTODO AUXILIAR: Consulta la tabla intermedia para rellenar la lista de un curso.
     */
    private List<Estudiante> cargarAlumnosDeCurso(int idCurso, Connection conn) throws SQLException {
        List<Estudiante> alumnos = new ArrayList<>();
        String sql = "SELECT e.* FROM sandovalcristinaEstudiante e " +
                     "JOIN sandovalcristinaMatricula m ON e.id = m.id_estudiante " +
                     "WHERE m.id_curso = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCurso);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Estudiante est = new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        Nivel.valueOf(rs.getString("nivel"))
                    );
                    alumnos.add(est);
                }
            }
        }
        return alumnos;
    }

    /**
     * MATRICULAR: Inserta solo en la tabla intermedia sandovalcristinaMatricula.
     */
    public void registrarMatricula(int idCurso, Estudiante est) throws AppException {
        String sql = "INSERT INTO sandovalcristinaMatricula (id_curso, id_estudiante) VALUES (?, ?)";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idCurso);
            ps.setInt(2, est.getId());
            ps.executeUpdate();

            // Sincronización: Actualizamos la lista en memoria del curso
            Curso c = buscarPorId(idCurso);
            if (c != null) {
                c.getListaAlumnos().add(est);
            }
            logger.info("Sincronización: Alumno {} añadido al curso ID: {}", est.getNombre(), idCurso);

        } catch (SQLException e) {
            throw new AppException("Error al registrar matrícula: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
    }

    public Curso buscarPorId(int id) {
        for (Curso c : cursos) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public List<Curso> getCursos() {
        return cursos;
    }
}