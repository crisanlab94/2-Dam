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
import modelo.Reproduccion;
import modelo.TipoContenido;
import modelo.TipoPlan;
import modelo.Usuario;
import util.MySqlConector;

public class RepositorioReproduccion {
	  private static final Logger logger = LogManager.getLogger(RepositorioReproduccion.class);
	    private final MySqlConector mysqlConector;
	    private List<Reproduccion> reproducciones; 

	    public RepositorioReproduccion(MySqlConector mysqlConector) throws AppException {
	        this.mysqlConector = mysqlConector;
	        this.reproducciones = this.read(); 
	    }
	    
	    private Reproduccion mapearResultSetAObjeto(ResultSet rs) throws SQLException {
	        Reproduccion r = new Reproduccion();
	        r.setId(rs.getInt("id_repro")); 
	        r.setTipo(TipoContenido.valueOf(rs.getString("tipo_contenido")));
	        r.setFecha(rs.getDate("fecha"));
	       r.setMinutosVistos(rs.getInt("minutos_vistos"));
	        
	       
	        Usuario u = new Usuario();
	        u.setId(rs.getString("id_usuario")); 
	        u.setNombre(rs.getString("nombre"));
	        u.setEmail(rs.getString("email"));
	        u.setPlanActivo(TipoPlan.valueOf(rs.getString("plan_activo")));
	        u.setPuntosFidelidad(rs.getInt("puntos_fidelidad"));
	        u.setNotificacionesPush(rs.getBoolean("notificaciones_push"));
	        u.setDispositivoPrincipal(rs.getString("dispositivo_principal"));
	        
	        r.setUsuario(u); 
	        return r; 
	    }
	    
	    public List<Reproduccion> read() throws AppException {
	        List<Reproduccion> listaBD = new ArrayList<>();
	       // El JOIN debe ser por id_usuario ( se llama igual en ambas tablas)
	        String sql = "SELECT r.id_repro, r.id_usuario, r.fecha, r.tipo_contenido, r.minutos_vistos, " +
	                "u.nombre, u.email, u.plan_activo, u.puntos_fidelidad, u.notificaciones_push, u.dispositivo_principal " +
	                "FROM SandovalCristinaReproduccion r " +
	                "LEFT JOIN SandovalCristinaUsuario u ON r.id_usuario = u.id_usuario";

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
	    
	    public void guardar(Reproduccion r) throws AppException {
	        String sql = "INSERT INTO SandovalCristinaReproduccion (id_usuario, fecha, tipo_contenido, minutos_vistos) VALUES (?,?,?,?)";
	        try (Connection conn = mysqlConector.getConnect();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        	ps.setString(1, r.getUsuario().getId());
	            ps.setDate(2, r.getFecha());
	            ps.setString(3, r.getTipo().name());
	            ps.setInt(4, r.getMinutosVistos());
	            ps.executeUpdate();
	            this.reproducciones.add(r); 
	        } catch (SQLException e) {
	            throw new AppException("Error al guardar la reproduccion: " + e.getMessage());
	        } finally { 
	            mysqlConector.release(); 
	        }
	    }

	    
	    public List<Reproduccion> getReproduccion() {
	         return reproducciones;
	     }
}
