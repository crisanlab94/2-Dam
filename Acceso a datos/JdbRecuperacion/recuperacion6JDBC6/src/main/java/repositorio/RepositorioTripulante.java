package repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Tripulante;
import util.MySqlConector;

public class RepositorioTripulante {
    private static final Logger logger = LogManager.getLogger(RepositorioTripulante.class);
    private final MySqlConector mysqlConector;
    private List<Tripulante> tripulantes; 

    public RepositorioTripulante(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        this.tripulantes = this.read(); 
    }
    
    private Tripulante mapearResultSetAObjeto(ResultSet rs) throws SQLException {
        Tripulante t = new Tripulante();
        t.setId(rs.getInt("id_tripulante")); 
        t.setNombre(rs.getString("nombre"));
        t.setRango(rs.getString("rango"));
        t.setActivo(rs.getBoolean("activo"));
        t.setIdNave(rs.getInt("id_nave")); 
        return t; // Un solo return
    }

    public List<Tripulante> read() throws AppException {
        List<Tripulante> listaBD = new ArrayList<>();
        String sql = "SELECT * FROM sandovalcristinaTripulante";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listaBD.add(mapearResultSetAObjeto(rs));
            }
            logger.info("Tripulantes cargados correctamente.");
        } catch (SQLException e) {
            throw new AppException("Error al leer tripulantes: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return listaBD; // Un solo return
    }

    public void guardar(Tripulante t) throws AppException {
        String sql = "INSERT INTO sandovalcristinaTripulante (nombre, rango, activo, id_nave) VALUES (?,?,?,?)";
        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getRango());
            ps.setBoolean(3, t.isActivo());
            ps.setInt(4, t.getIdNave());
            ps.executeUpdate();
            this.tripulantes.add(t); 
        } catch (SQLException e) {
            throw new AppException("Error al guardar tripulante: " + e.getMessage());
        } finally { 
            mysqlConector.release(); 
        }
    }

    public void actualizarRango(int id, String nuevoRango) throws AppException {
        String sql = "UPDATE sandovalcristinaTripulante SET rango = ? WHERE id_tripulante = ?";
        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoRango);
            ps.setInt(2, id);
            ps.executeUpdate();
            
            // CORREGIDO: Eliminado el 'break' para cumplir con tu regla
            for (Tripulante t : tripulantes) {
                if (t.getId() == id) {
                    t.setRango(nuevoRango);
                }
            }
        } catch (SQLException e) {
            throw new AppException("Error al actualizar rango: " + e.getMessage());
        } finally { 
            mysqlConector.release(); 
        }
    }

    public void eliminarInactivos(int idNave) throws AppException {
        String sql = "DELETE FROM sandovalcristinaTripulante WHERE id_nave = ? AND activo = false";
        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNave);
            ps.executeUpdate();
            
            // Recorremos de atrás hacia adelante (sin break, revisa todo)
            for (int i = tripulantes.size() - 1; i >= 0; i--) {
                Tripulante t = tripulantes.get(i);
                if (t.getIdNave() == idNave && !t.isActivo()) {
                    tripulantes.remove(i);
                }
            }
        } catch (SQLException e) {
            throw new AppException("Error al eliminar inactivos: " + e.getMessage());
        } finally { 
            mysqlConector.release(); 
        }
    }
    
    public List<Tripulante> getTripulantes() {
        return tripulantes; 
    }
    
    public List<Tripulante> readPorRango(String rango) throws AppException {
        List<Tripulante> lista = new ArrayList<>();
        String sql = "SELECT * FROM sandovalcristinaTripulante WHERE rango = ? ORDER BY nombre ASC";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rango);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearResultSetAObjeto(rs));
                }
            }
        } catch (SQLException e) {
            throw new AppException("Error al filtrar tripulantes: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return lista; 
    }
}