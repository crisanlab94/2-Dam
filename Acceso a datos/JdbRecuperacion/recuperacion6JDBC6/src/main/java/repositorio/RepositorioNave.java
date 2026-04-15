package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Nave;
import util.MySqlConector;

public class RepositorioNave {
    private static final Logger logger = LogManager.getLogger(RepositorioNave.class);
    private final MySqlConector mysqlConector;
    private List<Nave> naves; 
    
    public RepositorioNave(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        this.naves = this.read(); 
    }

    private Nave mapearResultSetAObjeto(ResultSet rs) throws SQLException {
        Nave n = new Nave();
        n.setId(rs.getInt("id_nave")); 
        n.setNombre(rs.getString("nombre"));
        n.setModelo(rs.getString("modelo"));
        return n;
    }
    
    public List<Nave> read() throws AppException {
        List<Nave> listaBD = new ArrayList<>();
        String sql = "SELECT * FROM sandovalcristinaNave";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listaBD.add(mapearResultSetAObjeto(rs));
            }
            logger.info("Datos cargados en la caché correctamente.");
        } catch (SQLException e) {
            throw new AppException("Error al sincronizar la base de datos con la caché: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return listaBD;
    }
    
    public void guardar(Nave nave) throws AppException {
        if (this.buscarPorId(nave.getId()) != null) {
            throw new AppException("No se puede registrar: La nave con ID " + nave.getId() + " ya existe.");
        }

        String sqlAst = "INSERT INTO sandovalcristinaNave (id_nave, nombre, modelo) VALUES (?,?,?)";

        try (Connection conn = mysqlConector.getConnect()) {
            conn.setAutoCommit(false); 

            try (PreparedStatement psA = conn.prepareStatement(sqlAst)) {
                psA.setInt(1, nave.getId());
                psA.setString(2, nave.getNombre());
                psA.setString(3, nave.getModelo());
                psA.executeUpdate();

                conn.commit();
                this.naves.add(nave);
                logger.info("Guardado físico y en caché realizado.");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new AppException("Error crítico durante el guardado: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
    }
    
    public Nave buscarPorId(int id) {
        Nave encontrado = null;
      
        for (Nave n : naves) {
            if (n.getId() == id) {
                encontrado = n;
                
            }
        }
        
        return encontrado;
    }
    
    /**Version de id tipo String
     * 
     * 
     public Nave buscarPorId(String id) {
    Nave encontrado = null;
    
    for (Nave n : naves) {
        // ¡IMPORTANTE! Con Strings usamos .equals()
        if (n.getId().equals(id)) {
            encontrado = n;
        }
    }
    
    return encontrado;
}
     */

    public List<Nave> getNaves() {
        return naves;
    }
    
    public void eliminar(int id) throws AppException {
        String sql = "DELETE FROM sandovalcristinaNave WHERE id_nave = ?";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                // Borramos de la caché con tu estilo "de siempre"
                int i = 0;
                boolean borrado = false;
                while (i < naves.size() && !borrado) {
                    if (naves.get(i).getId() == id) {
                        naves.remove(i);
                        borrado = true; 
                    }
                    i++;
                }
                logger.info("Nave eliminada de la base de datos y caché.");
            }
            
        } catch (SQLException e) {
            // Aquí es donde capturamos el fallo de la FK
            throw new AppException("No se puede eliminar la nave: Tiene tripulantes asignados. Borra primero a los tripulantes.");
        } finally {
            mysqlConector.release();
        }
    }
}