package repositorio;
import java.sql.*;
import java.util.*;
import modelo.*;
import util.MySqlConector;

public class RepositorioEmpresa {
    private MySqlConector con;
    private List<Empresa> cache;

    public RepositorioEmpresa(MySqlConector con) throws AppException {
        this.con = con;
        this.cache = this.read();
    }

    private Empresa mapear(ResultSet rs) throws SQLException {
        return new Empresa(
            rs.getInt("id"), rs.getString("nombre"), Sector.valueOf(rs.getString("sector")),
            rs.getDate("fecha_creacion").toLocalDate(), rs.getBoolean("es_multinacional")
        );
    }

    public List<Empresa> read() throws AppException {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresas";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { throw new AppException("Error Read Empresas"); }
        return lista;
    }

    public void guardar(Empresa e) throws AppException {
        String sql = "INSERT INTO empresas (nombre, sector, fecha_creacion, es_multinacional) VALUES (?,?,?,?)";
        try (Connection c = con.getConnect(); 
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getSector().name());
            ps.setDate(3, java.sql.Date.valueOf(e.getFechaCreacion()));
            ps.setBoolean(4, e.isEsMultinacional());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) e.setId(rs.getInt(1)); // Recuperamos el ID autogenerado
            this.cache = this.read();
        } catch (SQLException ex) { throw new AppException("Error Guardar Empresa"); }
    }

    public Empresa buscarPorId(int id) throws AppException {
        Empresa e = null;
        String sql = "SELECT * FROM empresas WHERE id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) e = mapear(rs);
        } catch (SQLException ex) { throw new AppException("Error Buscar Empresa SQL"); }
        return e;
    }

    public void borrar(int id) throws AppException {
        String sql = "DELETE FROM empresas WHERE id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
            this.cache = this.read();
        } catch (SQLException ex) { throw new AppException("Error Delete Empresa SQL"); }
    }

    public List<Empresa> getLista() { return cache; }
}