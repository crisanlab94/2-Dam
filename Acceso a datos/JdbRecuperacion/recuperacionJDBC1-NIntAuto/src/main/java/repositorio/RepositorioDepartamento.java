package repositorio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.AppException;
import modelo.Departamento;
import util.MySqlConector;

public class RepositorioDepartamento {
    private MySqlConector con;
    private List<Departamento> cache;

    public RepositorioDepartamento(MySqlConector con) throws AppException {
        this.con = con;
        this.cache = this.read();
    }

    private Departamento mapear(ResultSet rs) throws SQLException {
        return new Departamento(
            rs.getString("id"), rs.getString("nombre"), rs.getDouble("presupuesto"),
            rs.getDate("fecha_fundacion").toLocalDate(), rs.getBoolean("es_oficial")
        );
    }

    public List<Departamento> read() throws AppException {
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM departamentos";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { 
        	e.printStackTrace();
        	throw new AppException("Error Read Departamentos"); }
        return lista;
    }

    public Departamento buscarPorId(String id) throws AppException {
        Departamento d = null;
        String sql = "SELECT * FROM departamentos WHERE id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) d = mapear(rs);
        } catch (SQLException e) { throw new AppException("Error SQL Buscar Departamento"); }
        return d;
    }

    public void guardar(Departamento d) throws AppException {
        String sql = "INSERT INTO departamentos VALUES (?,?,?,?,?)";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, d.getId()); ps.setString(2, d.getNombre());
            ps.setDouble(3, d.getPresupuesto()); ps.setDate(4, Date.valueOf(d.getFechaFundacion()));
            ps.setBoolean(5, d.isEsOficial()); ps.executeUpdate();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Error SQL Guardar Departamento"); }
    }

    public void borrar(String id) throws AppException {
        String sql = "DELETE FROM departamentos WHERE id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id); ps.executeUpdate();
            this.cache = this.read();
        } catch (SQLException e) { throw new AppException("Error SQL Delete Departamento"); }
    }

    public List<Departamento> getLista() { return cache; }
}