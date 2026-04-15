package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Estudiante;
import modelo.Nivel;
import util.MySqlConector;

public class RepositorioEstudiante {
    private static final Logger logger = LogManager.getLogger(RepositorioEstudiante.class);
    private final MySqlConector mysqlConector;
    private List<Estudiante> estudiantes;

    public RepositorioEstudiante(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        this.estudiantes = this.read();
    }

    public List<Estudiante> read() throws AppException {
        List<Estudiante> listaBD = new ArrayList<>();
        String sql = "SELECT * FROM sandovalcristinaEstudiante";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setNivel(Nivel.valueOf(rs.getString("nivel")));
                listaBD.add(e);
            }
            logger.info("Caché de estudiantes cargada (Total: " + listaBD.size() + ")");
        } catch (SQLException e) {
            throw new AppException("Error al leer estudiantes: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return listaBD;
    }

    public Estudiante buscarPorId(int id) {
        for (Estudiante e : estudiantes) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}