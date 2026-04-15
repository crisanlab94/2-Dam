package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.*;
import util.MySqlConector;

public class RepositorioMedico {
    private static final Logger logger = LogManager.getLogger(RepositorioMedico.class);
    private MySqlConector con;
    private List<Medico> cache;

    public RepositorioMedico(MySqlConector con) throws AppException {
        this.con = con;
        this.cache = this.read();
    }

    private Medico mapear(ResultSet rs) throws SQLException {
        Medico m = new Medico();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setEspecialidad(Especialidad.valueOf(rs.getString("especialidad")));
        m.setEmail(rs.getString("email"));
        m.setFechaAlta(rs.getDate("fecha_alta").toLocalDate());
        m.setEstaActivo(rs.getBoolean("esta_activo"));

        Consultorio c = new Consultorio();
        c.setPlanta(rs.getInt("planta"));
        c.setCosteMantenimiento(rs.getDouble("coste_mantenimiento"));
        c.setUltimaInspeccion(rs.getTimestamp("ultima_inspeccion").toLocalDateTime());
        m.setConsultorio(c);
        return m;
    }

    public List<Medico> read() throws AppException {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT m.*, c.* FROM medicos m JOIN consultorios c ON m.id = c.medico_id";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { throw new AppException("Error SQL Read Médicos"); }
        return lista;
    }

    public Medico buscarPorId(int id) throws AppException {
        Medico res = null;
        String sql = "SELECT m.*, c.* FROM medicos m JOIN consultorios c ON m.id = c.medico_id WHERE m.id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) res = mapear(rs); }
        } catch (SQLException e) { throw new AppException("Error SQL Buscar Médico por ID"); }
        return res;
    }

    public void guardar(Medico m) throws AppException {
        String s1 = "INSERT INTO medicos (nombre, especialidad, email, fecha_alta, esta_activo) VALUES (?,?,?,?,?)";
        String s2 = "INSERT INTO consultorios VALUES (?,?,?,?)";
        try (Connection c = con.getConnect()) {
            c.setAutoCommit(false);
            try (PreparedStatement ps1 = c.prepareStatement(s1, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, m.getNombre());
                ps1.setString(2, m.getEspecialidad().name());
                ps1.setString(3, m.getEmail());
                ps1.setDate(4, Date.valueOf(m.getFechaAlta()));
                ps1.setBoolean(5, m.isEstaActivo());
                ps1.executeUpdate();
                ResultSet rs = ps1.getGeneratedKeys();
                if (rs.next()) m.setId(rs.getInt(1));
            }
            try (PreparedStatement ps2 = c.prepareStatement(s2)) {
                ps2.setInt(1, m.getId());
                ps2.setInt(2, m.getConsultorio().getPlanta());
                ps2.setDouble(3, m.getConsultorio().getCosteMantenimiento());
                ps2.setTimestamp(4, Timestamp.valueOf(m.getConsultorio().getUltimaInspeccion()));
                ps2.executeUpdate();
            }
            c.commit();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Error SQL Guardar Médico"); }
    }

    public void actualizarCoste(int id, double nuevoCoste) throws AppException {
        String sql = "UPDATE consultorios SET coste_mantenimiento = ? WHERE medico_id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1, nuevoCoste); ps.setInt(2, id); ps.executeUpdate();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Error SQL Update Coste"); }
    }

    public void borrar(int id) throws AppException {
        String s1 = "DELETE FROM consultorios WHERE medico_id = ?";
        String s2 = "DELETE FROM medicos WHERE id = ?";
        try (Connection c = con.getConnect()) {
            c.setAutoCommit(false);
            try (PreparedStatement p1 = c.prepareStatement(s1)) { p1.setInt(1, id); p1.executeUpdate(); }
            try (PreparedStatement p2 = c.prepareStatement(s2)) { p2.setInt(1, id); p2.executeUpdate(); }
            c.commit();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Error SQL Delete Médico"); }
    }

    public List<Medico> getLista() { return cache; }
}
