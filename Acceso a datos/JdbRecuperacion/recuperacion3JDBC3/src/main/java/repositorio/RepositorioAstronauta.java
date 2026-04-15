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
import modelo.Astronauta;
import modelo.RangoAstronauta;
import util.MySqlConector;

public class RepositorioAstronauta {
	private static final Logger logger = LogManager.getLogger(RepositorioAstronauta.class);
    private final MySqlConector mysqlConector;
    private List<Astronauta> astronautas; 
    
    public RepositorioAstronauta(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        this.astronautas = this.read(); 
    }
    
    private Astronauta mapearResultSetAObjeto(ResultSet rs) throws SQLException {
        Astronauta a = new Astronauta();
        
        // El ID se recupera como String
        a.setId(rs.getInt("id")); 
        a.setNombre(rs.getString("nombre"));
       
        
        // Conversión del Enum RangoAstronauta
        a.setRango(RangoAstronauta.valueOf(rs.getString("rango")));
       
        a.setHorasVuelo(rs.getInt("horas_vuelo"));
        
        a.setActivo(rs.getBoolean("activo"));

        return a;
    }

    
    public List<Astronauta> read() throws AppException {
        List<Astronauta> listaBD = new ArrayList<>();
       //nombre real de la tabla después del from 
        String sql = "SELECT * FROM sandovalcristinaAstronauta" ;

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
    
    
    public void guardar(Astronauta astronauta) throws AppException {
       
        if (this.buscarPorId(astronauta.getId()) != null) {
            throw new AppException("No se puede registrar: El astronauta con ID " + astronauta.getId() + " ya existe.");
        }
        //poner nombre de la tabla de la base de datos

        String sqlAst = "INSERT INTO sandovalcristinaAstronauta (id, nombre, rango, horas_vuelo, activo) VALUES (?,?,?,?,?)";
       

        try (Connection conn = mysqlConector.getConnect()) {
            conn.setAutoCommit(false); 

            try (PreparedStatement psA = conn.prepareStatement(sqlAst))
                  {

                // Parámetros tabla Astronauta
                psA.setInt(1, astronauta.getId());
                psA.setString(2, astronauta.getNombre());
                psA.setString(3, astronauta.getRango().name());
                psA.setInt(4, astronauta.getHorasVuelo());
                psA.setBoolean(5, astronauta.isActivo());
                psA.executeUpdate();


                // Si no hay errores, confirmamos en la base de datos
                conn.commit();
                
                // Actualizamos la caché en memoria para que esté sincronizada
                this.astronautas.add(astronauta);
                logger.info("Guardado físico y en caché realizado.");

            } catch (SQLException e) {
                // Si algo falla, deshacemos cualquier cambio parcial
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new AppException("Error crítico durante el guardado: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        
        return; 
    }
    
    public Astronauta buscarPorId(int id) {
        Astronauta encontrado = null;
        for (Astronauta j : astronautas) {
            if (j.getId()==(id)) {
                encontrado = j;
            }
        }
        return encontrado;
    }

    /**
     * GETTER: Devuelve la lista que está en memoria.
     */
    public List<Astronauta> getAstronautas() {
        return astronautas;
    }
    
    public void actualizarHoras(int id, int nuevasHoras) throws AppException {
        // 1. Actualización en la BASE DE DATOS
        String sql = "UPDATE sandovalcristinaAstronauta SET horas_vuelo = ? WHERE id = ?";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, nuevasHoras);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            // 2. Actualización en la CACHÉ (Memoria)
            // Buscamos al astronauta en nuestra lista para que el cambio se vea al momento
            Astronauta a = buscarPorId(id);
            if (a != null) {
                a.setHorasVuelo(nuevasHoras);
            }

            logger.info("Horas actualizadas correctamente para el ID: " + id);

        } catch (SQLException e) {
            throw new AppException("Error al actualizar horas en la BD: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
    }

}
