package repositorio;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.AppException;
import modelo.Sala;
import modelo.TipoAmbiente;
import util.MySqlConector;

public class RepositorioSala {
    private MySqlConector con;
    private List<Sala> cache;

    public RepositorioSala(MySqlConector con) throws AppException {
        this.con = con;
        this.cache = this.read();
    }

    private Sala mapear(ResultSet rs) throws SQLException {
        return new Sala(rs.getInt("id"), rs.getString("nombre"), 
                        TipoAmbiente.valueOf(rs.getString("tipo_ambiente")), rs.getInt("capacidad"));
    }

    public List<Sala> read() throws AppException {
        List<Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM salas";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { throw new AppException("Fallo Read Salas"); }
        return lista;
    }

    public List<Sala> buscarPorSede(int idSede) throws AppException {
        List<Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM salas WHERE id_sede = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idSede);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { throw new AppException("Fallo Buscar Salas por Sede"); }
        return lista;
    }

    public Sala buscarPorId(int id) throws AppException {
        Sala s = null;
        String sql = "SELECT * FROM salas WHERE id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) s = mapear(rs);
        } catch (SQLException e) { throw new AppException("Fallo Buscar Sala por ID"); }
        return s;
    }

    public void guardar(Sala s, int idSede) throws AppException {
        String sql = "INSERT INTO salas VALUES (?,?,?,?,?)";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, s.getId()); // ID Manual
            ps.setString(2, s.getNombre());
            ps.setString(3, s.getTipo().name());
            ps.setInt(4, s.getCapacidad());
            ps.setInt(5, idSede);
            ps.executeUpdate();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Fallo Guardar Sala"); }
    }

    public void borrarPorSede(int idSede) throws AppException {
        String sql = "DELETE FROM salas WHERE id_sede = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idSede); ps.executeUpdate();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Fallo Borrar Salas por Sede"); }
    }

    public List<Sala> getLista() { return cache; }
}
