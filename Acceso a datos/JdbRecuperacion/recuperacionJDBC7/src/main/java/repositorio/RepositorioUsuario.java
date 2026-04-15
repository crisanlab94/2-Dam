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
import modelo.TipoPlan;
import modelo.Usuario;
import util.MySqlConector;

public class RepositorioUsuario {
	 private static final Logger logger = LogManager.getLogger(RepositorioUsuario.class);
	    private final MySqlConector mysqlConector;
	    private List<Usuario> usuarios; 
	    
	    public RepositorioUsuario(MySqlConector mysqlConector) throws AppException {
	        this.mysqlConector = mysqlConector;
	        this.usuarios = this.read(); 
	    }

	    private Usuario mapearResultSetAObjeto(ResultSet rs) throws SQLException {
	        Usuario u = new Usuario();
	        u.setId(rs.getString("id_usuario")); 
	        u.setNombre(rs.getString("nombre"));
	        u.setEmail(rs.getString("email"));
	        u.setPlanActivo(TipoPlan.valueOf(rs.getString("plan_activo")));
	        u.setPuntosFidelidad(rs.getInt("puntos_fidelidad"));
	        u.setNotificacionesPush(rs.getBoolean("notificaciones_push"));
	        u.setDispositivoPrincipal(rs.getString("dispositivo_principal"));
	        return u;
	    }
	    
	    public List<Usuario> read() throws AppException {
	        List<Usuario> listaBD = new ArrayList<>();
	        String sql = "SELECT * FROM SandovalCristinaUsuario";

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
	    
	    public void guardar(Usuario usuario) throws AppException {
	        if (this.buscarPorId(usuario.getId()) != null) {
	            throw new AppException("No se puede registrar: El usuario con ID " + usuario.getId() + " ya existe.");
	        }

	        String sqlAst = "INSERT INTO SandovalCristinaUsuario (id_usuario, nombre, email, plan_activo, puntos_fidelidad, notificaciones_push, dispositivo_principal) VALUES (?,?,?, ?, ?, ?, ?)";

	        try (Connection conn = mysqlConector.getConnect()) {
	            conn.setAutoCommit(false); 

	            try (PreparedStatement psU = conn.prepareStatement(sqlAst)) {
	                psU.setString(1, usuario.getId());
	                psU.setString(2, usuario.getNombre());
	                psU.setString(3, usuario.getEmail());
	                psU.setString(4, usuario.getPlanActivo().name());
	                psU.setInt(5, usuario.getPuntosFidelidad());
	                psU.setBoolean(6, usuario.isNotificacionesPush());
	                psU.setString(7, usuario.getDispositivoPrincipal());
	                psU.executeUpdate();

	                conn.commit();
	                this.usuarios.add(usuario);
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
	    
	     public Usuario buscarPorId(String id) {
	    	    Usuario encontrado = null;
	    	    
	    	    for (Usuario u : usuarios) {
	    	       
	    	        if (u.getId().equals(id)) {
	    	            encontrado = u;
	    	        }
	    	    }
	    	    
	    	    return encontrado;
	    	}
	     
	     public List<Usuario> getUsuario() {
	         return usuarios;
	     }
	     
	  // APARTADO 3: Filtrar por plan y ordenar por email ASC
	     public List<Usuario> getUsuariosPorPlan(TipoPlan plan) throws AppException {
	         List<Usuario> lista = new ArrayList<Usuario>();
	         String sql = "SELECT * FROM SandovalCristinaUsuario WHERE plan_activo = ? ORDER BY email ASC";

	         try (Connection conn = mysqlConector.getConnect();
	              PreparedStatement ps = conn.prepareStatement(sql)) {
	             
	             ps.setString(1, plan.name());
	             try (ResultSet rs = ps.executeQuery()) {
	                 while (rs.next()) {
	                     lista.add(mapearResultSetAObjeto(rs));
	                 }
	             }
	         } catch (SQLException e) {
	             throw new AppException("Error al filtrar por plan: " + e.getMessage());
	         } finally {
	             mysqlConector.release();
	         }
	         return lista;
	     }

	     // APARTADO 5: Contar usuarios VIP
	     public int contarUsuariosVIP() throws AppException {
	         int total = 0;
	         String sql = "SELECT COUNT(*) FROM SandovalCristinaUsuario WHERE plan_activo = 'VIP'";

	         try (Connection conn = mysqlConector.getConnect();
	              PreparedStatement ps = conn.prepareStatement(sql);
	              ResultSet rs = ps.executeQuery()) {
	             
	             if (rs.next()) {
	                 total = rs.getInt(1);
	             }
	         } catch (SQLException e) {
	             throw new AppException("Error al contar VIPs: " + e.getMessage());
	         } finally {
	             mysqlConector.release();
	         }
	         return total;
	     }

	     // APARTADO 6: Consulta Compleja (Top 3 específicos)
	     public List<Usuario> obtenerTop3Mensuales() throws AppException {
	         List<Usuario> lista = new ArrayList<Usuario>();
	         // Filtramos por plan, notificaciones y dispositivo, limitado a 3
	         String sql = "SELECT * FROM SandovalCristinaUsuario " +
	                      "WHERE plan_activo = 'MENSUAL' AND notificaciones_push = false " +
	                      "AND dispositivo_principal = 'android14' LIMIT 3";

	         try (Connection conn = mysqlConector.getConnect();
	              PreparedStatement ps = conn.prepareStatement(sql);
	              ResultSet rs = ps.executeQuery()) {
	             
	             while (rs.next()) {
	                 lista.add(mapearResultSetAObjeto(rs));
	             }
	         } catch (SQLException e) {
	             throw new AppException("Error en consulta compleja: " + e.getMessage());
	         } finally {
	             mysqlConector.release();
	         }
	         return lista;
	     }

	     // APARTADO 7: Borrado por dispositivo
	     public int eliminarPorDispositivo(String dispo) throws AppException {
	         int filasBorradas = 0;
	         String sql = "DELETE FROM SandovalCristinaUsuario WHERE dispositivo_principal = ?";

	         try (Connection conn = mysqlConector.getConnect();
	              PreparedStatement ps = conn.prepareStatement(sql)) {
	             
	             ps.setString(1, dispo);
	             filasBorradas = ps.executeUpdate();
	             
	             // Actualizamos la caché (volvemos a leer para que la lista esté limpia)
	             this.usuarios = this.read();
	             
	         } catch (SQLException e) {
	             throw new AppException("Error al borrar por dispositivo: " + e.getMessage());
	         } finally {
	             mysqlConector.release();
	         }
	         return filasBorradas;
	     }

}
