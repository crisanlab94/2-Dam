package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.*;
import util.MySqlConector;

public class RepositorioDrone {
    private static final Logger logger = LogManager.getLogger(RepositorioDrone.class);
    private MySqlConector con;
    private List<Drone> cache;

    public RepositorioDrone(MySqlConector con) throws AppException {
        this.con = con;
        this.cache = this.read();
    }

    private Drone mapear(ResultSet rs) throws SQLException {
        Drone d = new Drone();
        d.setId(rs.getInt("id"));
        d.setModelo(rs.getString("modelo"));
        d.setCategoria(Categoria.valueOf(rs.getString("categoria")));
        d.setUltimaMision(rs.getTimestamp("ultima_mision").toLocalDateTime());
        d.setNivelBateria(rs.getInt("nivel_bateria"));
        d.setNecesitaReparacion(rs.getBoolean("necesita_reparacion"));

        Componente c = new Componente();
        c.setPesoGramos(rs.getDouble("peso_gramos"));
        c.setMaterial(rs.getString("material"));
        c.setGpsActivo(rs.getBoolean("gps_activo"));
        d.setComponente(c);
        return d;
    }

    public List<Drone> read() throws AppException {
        List<Drone> lista = new ArrayList<>();
        String sql = "SELECT d.*, c.* FROM drones d JOIN componentes c ON d.id = c.id_drone";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) { lista.add(mapear(rs)); }
        } catch (SQLException e) { throw new AppException("Error SQL Read Drones"); }
        return lista;
    }

    public Drone buscarPorId(int id) throws AppException {
        Drone res = null;
        String sql = "SELECT d.*, c.* FROM drones d JOIN componentes c ON d.id = c.id_drone WHERE d.id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) res = mapear(rs); }
        } catch (SQLException e) { throw new AppException("Error SQL Buscar Drone"); }
        return res;
    }

    public void guardar(Drone d) throws AppException {
        String s1 = "INSERT INTO drones VALUES (?,?,?,?,?,?)";
        String s2 = "INSERT INTO componentes VALUES (?,?,?,?)";
        try (Connection c = con.getConnect()) {
            c.setAutoCommit(false);
            try (PreparedStatement p1 = c.prepareStatement(s1)) {
                p1.setInt(1, d.getId()); // ID Manual
                p1.setString(2, d.getModelo());
                p1.setString(3, d.getCategoria().name());
                p1.setTimestamp(4, Timestamp.valueOf(d.getUltimaMision()));
                p1.setInt(5, d.getNivelBateria());
                p1.setBoolean(6, d.isNecesitaReparacion());
                p1.executeUpdate();
            }
            try (PreparedStatement p2 = c.prepareStatement(s2)) {
                p2.setInt(1, d.getId()); // Mismo ID Manual
                p2.setDouble(2, d.getComponente().getPesoGramos());
                p2.setString(3, d.getComponente().getMaterial());
                p2.setBoolean(4, d.getComponente().isGpsActivo());
                p2.executeUpdate();
            }
            c.commit();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Fallo SQL Guardar Drone"); }
    }

    public void actualizarBateria(int id, int bateria) throws AppException {
        String sql = "UPDATE drones SET nivel_bateria = ? WHERE id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, bateria); ps.setInt(2, id); ps.executeUpdate();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Fallo update batería"); }
    }

    public void borrar(int id) throws AppException {
        String s1 = "DELETE FROM componentes WHERE id_drone = ?";
        String s2 = "DELETE FROM drones WHERE id = ?";
        try (Connection c = con.getConnect()) {
            c.setAutoCommit(false);
            try (PreparedStatement p1 = c.prepareStatement(s1)) { p1.setInt(1, id); p1.executeUpdate(); }
            try (PreparedStatement p2 = c.prepareStatement(s2)) { p2.setInt(1, id); p2.executeUpdate(); }
            c.commit();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Fallo delete drone"); }
    }

    public List<Drone> getLista() { return cache; }
}
