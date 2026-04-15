package repositorio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Detalle;
import modelo.Dispositivo;
import modelo.Tipo;
import util.MySqlConector;


public class RepositorioSmartHome {
    private static final Logger logger = LogManager.getLogger(RepositorioSmartHome.class);
    private  MySqlConector con;
    private List<Dispositivo> cache; // <--- AQUÍ ESTÁ TU LISTA

    public RepositorioSmartHome( MySqlConector con) throws AppException {
        this.con = con;
        this.cache = this.read(); // Carga inicial
    }

    private Dispositivo mapear(ResultSet rs) throws SQLException {
        Dispositivo disp = new Dispositivo();
        disp.setId(rs.getString("id"));
        disp.setNombre(rs.getString("nombre"));
        disp.setTipo(Tipo.valueOf(rs.getString("tipo")));
        disp.setFecha(rs.getDate("fecha_instalacion").toLocalDate());
        disp.setActivo(rs.getBoolean("activo"));
        Detalle d = new Detalle(rs.getDouble("consumo_doble"), rs.getString("version_firmware"));
        disp.setDetalle(d);
        return disp;
    }

    public List<Dispositivo> read() throws AppException {
        List<Dispositivo> lista = new ArrayList<>();
        String sql = "SELECT d.*, det.* FROM dispositivos d JOIN detalles det ON d.id = det.id_dispositivo";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { throw new AppException("Error SQL Read"); }
        return lista;
    }

    public Dispositivo buscarPorId(String id) throws AppException {
        Dispositivo res = null;
        String sql = "SELECT d.*, det.* FROM dispositivos d JOIN detalles det ON d.id = det.id_dispositivo WHERE d.id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) res = mapear(rs); }
        } catch (SQLException e) { throw new AppException("Error SQL Buscar ID"); }
        return res;
    }

    public void guardar(Dispositivo d) throws AppException {
        String s1 = "INSERT INTO dispositivos VALUES (?,?,?,?,?)";
        String s2 = "INSERT INTO detalles VALUES (?,?,?)";
        try (Connection c = con.getConnect()) {
            c.setAutoCommit(false);
            try (PreparedStatement p1 = c.prepareStatement(s1)) {
                p1.setString(1, d.getId()); p1.setString(2, d.getNombre());
                p1.setString(3, d.getTipo().name()); p1.setDate(4, Date.valueOf(d.getFecha()));
                p1.setBoolean(5, d.isActivo()); p1.executeUpdate();
            }
            try (PreparedStatement p2 = c.prepareStatement(s2)) {
                p2.setString(1, d.getId()); p2.setDouble(2, d.getDetalle().getConsumo());
                p2.setString(3, d.getDetalle().getVersion()); p2.executeUpdate();
            }
            c.commit();
            this.cache = this.read(); // SINCRONIZAMOS LA LISTA
        } catch (SQLException e) { throw new AppException("Error SQL Guardar"); }
    }

    
    /** Guardar Solo Detalle 
     * public void guardarDetalleSolo(String idPadre, Detalle d) throws AppException {
    String sql = "INSERT INTO detalles (id_dispositivo, consumo_doble, version_firmware) VALUES (?, ?, ?)";
    
    try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
        ps.setString(1, idPadre);
        ps.setDouble(2, d.getConsumo());
        ps.setString(3, d.getVersion());
        
        ps.executeUpdate();
        logger.info("Repositorio: Detalle vinculado al dispositivo {} correctamente.", idPadre);
    } catch (SQLException e) {
        throw new AppException("Error al vincular el detalle: " + e.getMessage());
    }
}
*/
    
    public List<Dispositivo> buscarNombreYTipo(String n, Tipo t) throws AppException {
        List<Dispositivo> res = new ArrayList<>();
        String sql = "SELECT d.*, det.* FROM dispositivos d JOIN detalles det ON d.id = det.id_dispositivo WHERE d.nombre LIKE ? AND d.tipo = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%"+n+"%"); ps.setString(2, t.name());
            try (ResultSet rs = ps.executeQuery()) { while (rs.next()) res.add(mapear(rs)); }
        } catch (SQLException e) { throw new AppException("Fallo filtro"); }
        return res;
    }
    
    public void actualizarVersion(String id, String v) throws AppException {
        String sql = "UPDATE detalles SET version_firmware = ? WHERE id_dispositivo = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, v); ps.setString(2, id); ps.executeUpdate();
            this.cache = this.read(); // SINCRONIZAMOS
        } catch (SQLException e) { throw new AppException("Error SQL Update"); }
    }

    public void borrar(String id) throws AppException {
        String s1 = "DELETE FROM detalles WHERE id_dispositivo = ?";
        String s2 = "DELETE FROM dispositivos WHERE id = ?";
        try (Connection c = con.getConnect()) {
            c.setAutoCommit(false);
            try (PreparedStatement p1 = c.prepareStatement(s1)) { p1.setString(1, id); p1.executeUpdate(); }
            try (PreparedStatement p2 = c.prepareStatement(s2)) { p2.setString(1, id); p2.executeUpdate(); }
            c.commit();
            this.cache = this.read(); // SINCRONIZAMOS
        } catch (SQLException e) { throw new AppException("Error SQL Delete"); }
    }

    public List<Dispositivo> getLista() {
    	return cache; 
    	}
}
