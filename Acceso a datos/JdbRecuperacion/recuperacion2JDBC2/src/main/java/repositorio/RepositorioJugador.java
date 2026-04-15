package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Jugador;
import util.MySqlConector;


public class RepositorioJugador {
	 private static final Logger logger = LogManager.getLogger(RepositorioJugador.class);
	    private final MySqlConector mysqlConector;
	    private List<Jugador> jugadores; 
	    
	    public RepositorioJugador(MySqlConector mysqlConector) throws AppException {
	        this.mysqlConector = mysqlConector;
	        this.jugadores = this.read(); 
	    }


	    private Jugador mapearResultSetAObjeto(ResultSet rs) throws SQLException {
	        Jugador j = new Jugador();
	       
	        j.setId(rs.getInt("id")); 
	        j.setNombre(rs.getString("nombre"));
	        j.setEmail(rs.getString("email"));
	        j.setEloPuntos(rs.getInt("elo_puntos"));
	        
	        return j;
	    }
	    
	    public List<Jugador> read() throws AppException {
	        List<Jugador> listaBD = new ArrayList<>();
	       //nombre real de la tabla después del from 
	        String sql = "SELECT * FROM sandovalcristinaJugador" ;

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
	    
	    public void guardar(Jugador  jugador) throws AppException {
	        // Validación: El ID debe ser único 
	        if (this.buscarPorId(jugador.getId()) != null) {
	            throw new AppException("No se puede registrar: El usuario con ID " + jugador.getId() + " ya existe.");
	        }

	        String sqlUser = "INSERT INTO sandovalcristinaJugador (id, nombre, email, elo_puntos) VALUES (?,?,?,?)";
	       

	        try (Connection conn = mysqlConector.getConnect()) {
	            conn.setAutoCommit(false); 

	            try (PreparedStatement gJ = conn.prepareStatement(sqlUser)) {

	                // Parámetros tabla jugador
	                gJ.setInt(1, jugador.getId());
	                gJ.setString(2, jugador.getNombre());
	                gJ.setString(3, jugador.getEmail());
	                gJ.setInt(4, jugador.getEloPuntos()); 
	                gJ.executeUpdate();

	                // Si no hay errores, confirmamos en la base de datos
	                conn.commit();
	                
	                // Actualizamos la caché en memoria para que esté sincronizada
	                this.jugadores.add(jugador);
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
	        
	        return; // Único punto de salida para el método void
	    }
	    
	    public Jugador buscarPorId(int id) {
	        Jugador encontrado = null;
	        for (Jugador j : jugadores) {
	            if (j.getId()==(id)) {
	                encontrado = j;
	            }
	        }
	        return encontrado;
	    }

	    /**
	     * GETTER: Devuelve la lista que está en memoria.
	     */
	    public List<Jugador> getJugadores() {
	        return jugadores;
	    }

	    
	    /**
	     * ACTUALIZAR ELO: Modifica los puntos en la BD y sincroniza la caché.
	     */
	    public void actualizarElo(int id, int nuevosPuntos) throws AppException {
	        String sql = "UPDATE sandovalcristinaJugador SET elo_puntos = ? WHERE id = ?";

	        try (Connection conn = mysqlConector.getConnect();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, nuevosPuntos);
	            stmt.setInt(2, id);
	            stmt.executeUpdate();

	            // Sincronización de CACHÉ:
	            Jugador j = buscarPorId(id);
	            if (j != null) {
	                j.setEloPuntos(nuevosPuntos);
	            }
	            logger.info("ELO actualizado para el ID: " + id);

	        } catch (SQLException e) {
	            throw new AppException("Error al actualizar ELO: " + e.getMessage());
	        } finally {
	            mysqlConector.release();
	        }
	    }

	    /**
	     * MEJOR JUGADOR: Busca en la caché el que tiene más puntos.
	     */
	    public Jugador getMejorJugador() {
	        Jugador mejor = null;
	        if (!jugadores.isEmpty()) {
	            mejor = jugadores.get(0);
	            for (Jugador j : jugadores) {
	                if (j.getEloPuntos() > mejor.getEloPuntos()) {
	                    mejor = j;
	                }
	            }
	        }
	        return mejor;
	    }

	    /**
	     * LISTADO ORDENADO: Devuelve una copia de la lista ordenada por ELO (Descendente).
	     */
	    public List<Jugador> listarJugadoresPorPuntos() {
	        List<Jugador> ordenada = new ArrayList<>(jugadores);
	        // Ordenamos de mayor a menor ELO 
	        ordenada.sort((j1, j2) -> Integer.compare(j2.getEloPuntos(), j1.getEloPuntos()));
	        return ordenada;
	    }

}
