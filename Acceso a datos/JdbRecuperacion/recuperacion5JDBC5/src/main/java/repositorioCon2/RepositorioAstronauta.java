package repositorioCon2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.AppException;
import modelo.Astronauta;
import util.MySqlConector;

public class RepositorioAstronauta {
    private static final Logger logger = LogManager.getLogger(RepositorioAstronauta.class);
    private final MySqlConector conector;

    public RepositorioAstronauta(MySqlConector conector) {
        this.conector = conector;
    }

    public List<Astronauta> readBasico() throws AppException {
        List<Astronauta> lista = new ArrayList<>();
        String sql = "SELECT * FROM sandovalcristinaAstronauta";
        try (Connection conn = conector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Astronauta a = new Astronauta();
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                lista.add(a);
            }
        } catch (SQLException e) {
            throw new AppException("Error en RepositorioAstronauta (read): " + e.getMessage());
        } finally {
            conector.release();
        }
        return lista;
    }

    public void guardar(Astronauta a, Connection conn) throws SQLException {
        String sql = "INSERT INTO sandovalcristinaAstronauta (id, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, a.getId());
            ps.setString(2, a.getNombre());
            ps.executeUpdate();
            logger.info("Tabla Astronauta actualizada.");
        }
    }
}