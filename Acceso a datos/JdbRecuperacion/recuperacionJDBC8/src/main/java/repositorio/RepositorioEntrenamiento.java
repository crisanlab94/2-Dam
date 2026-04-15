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
import modelo.Entrenamiento;
import modelo.Socio;
import modelo.TipoActividad;
import modelo.TipoCuota;
import util.MySqlConector;

public class RepositorioEntrenamiento {
    private static final Logger logger = LogManager.getLogger(RepositorioEntrenamiento.class);
    private final MySqlConector mysqlConector;
    private List<Entrenamiento> entrenamientos; 

    public RepositorioEntrenamiento(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        this.entrenamientos = this.read(); 
    }
    
    // MÉTODO AUXILIAR PARA MAPEAR RESULTSET
    private Entrenamiento mapearResultSetAObjeto(ResultSet rs) throws SQLException {
        Entrenamiento e = new Entrenamiento();
        e.setId(rs.getInt("id_entreno")); 
        e.setFecha(rs.getDate("fecha"));
        e.setTipoActividad(TipoActividad.valueOf(rs.getString("tipo_actividad")));
        e.setDuracionMin(rs.getInt("duracion_min"));
        
        // Mapeamos el Socio (Padre) que viene en el JOIN
        Socio s = new Socio();
        s.setId(rs.getInt("id_socio")); 
        s.setNombre(rs.getString("nombre"));
        s.setEmail(rs.getString("email"));
        s.setTipoCuota(TipoCuota.valueOf(rs.getString("tipo_cuota")));
        s.setEstaActivo(rs.getBoolean("esta_activo"));
        
        e.setSocio(s); 
        return e; 
    }

    // LISTADO GENERAL (CARGA DE CACHÉ)
    public List<Entrenamiento> read() throws AppException {
        List<Entrenamiento> listaBD = new ArrayList<Entrenamiento>();
        String sql = "SELECT e.id_entreno, e.id_socio, e.fecha, e.tipo_actividad, e.duracion_min, " +
                     "s.nombre, s.email, s.tipo_cuota, s.esta_activo " +
                     "FROM SandovalCristinaEntrenamiento e " +
                     "LEFT JOIN SandovalCristinaSocio s ON e.id_socio = s.id_socio";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listaBD.add(mapearResultSetAObjeto(rs));
            }
            logger.info("Entrenamientos cargados correctamente.");
        } catch (SQLException e) {
            throw new AppException("Error al sincronizar la base de datos con la caché: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return listaBD;
    }

    // APARTADO 6: Consulta Compleja - Top 3 Fuerza > 60 min
    public List<Entrenamiento> obtenerTop3Fuerza() throws AppException {
        List<Entrenamiento> lista = new ArrayList<Entrenamiento>();
        String sql = "SELECT e.*, s.nombre, s.email, s.tipo_cuota, s.esta_activo " +
                     "FROM SandovalCristinaEntrenamiento e " +
                     "JOIN SandovalCristinaSocio s ON e.id_socio = s.id_socio " +
                     "WHERE e.tipo_actividad = 'FUERZA' AND e.duracion_min > 60 " +
                     "ORDER BY e.duracion_min DESC LIMIT 3";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(mapearResultSetAObjeto(rs));
            }
        } catch (SQLException ex) {
            throw new AppException("Error en el ranking de fuerza: " + ex.getMessage());
        } finally {
            mysqlConector.release();
        }
        return lista;
    }

    // APARTADO 7: Borrado Parametrizado (Anteriores a una fecha)
    public int eliminarEntrenamientosAntiguos(Date fechaLimite) throws AppException {
        int filasAfectadas = 0;
        String sql = "DELETE FROM SandovalCristinaEntrenamiento WHERE fecha < ?";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setDate(1, fechaLimite);
            filasAfectadas = ps.executeUpdate();
            
            // Sincronizamos la caché después del borrado
            this.entrenamientos = this.read();
            
        } catch (SQLException ex) {
            throw new AppException("Error al limpiar historial: " + ex.getMessage());
        } finally {
            mysqlConector.release();
        }
        return filasAfectadas;
    }

    // GUARDAR NUEVO ENTRENAMIENTO
    public void guardar(Entrenamiento e) throws AppException {
        String sql = "INSERT INTO SandovalCristinaEntrenamiento (id_socio, fecha, tipo_actividad, duracion_min) VALUES (?,?,?,?)";
        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        
            ps.setInt(1, e.getSocio().getId());
            ps.setDate(2, e.getFecha());
            ps.setString(3, e.getTipoActividad().name());
            ps.setInt(4, e.getDuracionMin());
            ps.executeUpdate();
            
            this.entrenamientos.add(e); 
            logger.info("Entrenamiento guardado con éxito.");
        } catch (SQLException ex) {
            throw new AppException("Error al guardar el entrenamiento: " + ex.getMessage());
        } finally { 
            mysqlConector.release(); 
        }
    }

    // GETTER DE LA LISTA CACHÉ
    public List<Entrenamiento> getEntrenamientos() {
         return entrenamientos;
    }
}