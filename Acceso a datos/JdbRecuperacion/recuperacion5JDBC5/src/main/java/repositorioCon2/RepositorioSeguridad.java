package repositorioCon2;

import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.Seguridad;
import util.MySqlConector;

public class RepositorioSeguridad {
    private static final Logger logger = LogManager.getLogger(RepositorioSeguridad.class);
    private final MySqlConector conector;

    // 1. EL CONSTRUCTOR (Debe recibir el conector)
    public RepositorioSeguridad(MySqlConector conector) {
        this.conector = conector;
    }

    // 2. EL MÉTODO BUSCAR (Debe recibir el ID y la Conexión)
    public Seguridad buscarPorAstronauta(int idAstro, Connection conn) throws SQLException {
        String sql = "SELECT * FROM sandovalcristinaSeguridad WHERE id_astronauta = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAstro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Seguridad(rs.getInt("pin_acceso"), rs.getString("nivel_acceso"));
                }
            }
        }
        return null;
    }

    // 3. EL MÉTODO GUARDAR
    public void guardar(int idAstro, Seguridad s, Connection conn) throws SQLException {
        String sql = "INSERT INTO sandovalcristinaSeguridad (id_astronauta, pin_acceso, nivel_acceso) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAstro);
            ps.setInt(2, s.getPin());
            ps.setString(3, s.getNivel());
            ps.executeUpdate();
            logger.info("Datos de seguridad insertados.");
        }
    }
}