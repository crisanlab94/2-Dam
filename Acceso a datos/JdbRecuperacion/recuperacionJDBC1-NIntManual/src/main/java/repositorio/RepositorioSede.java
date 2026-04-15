package repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.AppException;
import modelo.Sede;
import util.MySqlConector;

public class RepositorioSede {
    private MySqlConector con;
    private List<Sede> cache;

    public RepositorioSede(MySqlConector con) throws AppException {
        this.con = con;
        this.cache = this.read();
    }

    private Sede mapear(ResultSet rs) throws SQLException {
        return new Sede(rs.getInt("id"), rs.getString("nombre"), 
                        rs.getDate("fecha_apertura").toLocalDate(), rs.getBoolean("es_internacional"));
    }

    public List<Sede> read() throws AppException {
        List<Sede> lista = new ArrayList<>();
        String sql = "SELECT * FROM sedes";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { throw new AppException("Fallo Read Sedes"); }
        return lista;
    }

    public Sede buscarPorId(int id) throws AppException {
        Sede s = null;
        String sql = "SELECT * FROM sedes WHERE id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) s = mapear(rs);
        } catch (SQLException e) { throw new AppException("Fallo Buscar Sede"); }
        return s;
    }

    public void guardar(Sede s) throws AppException {
        String sql = "INSERT INTO sedes VALUES (?,?,?,?)";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, s.getId()); // ID Manual
            ps.setString(2, s.getNombre());
            ps.setDate(3, Date.valueOf(s.getFechaApertura()));
            ps.setBoolean(4, s.isEsInternacional());
            ps.executeUpdate();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Fallo Guardar Sede"); }
    }

    public void borrar(int id) throws AppException {
        String sql = "DELETE FROM sedes WHERE id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Fallo Borrar Sede"); }
    }

    public List<Sede> getLista() { return cache; }
}
