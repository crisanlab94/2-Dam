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
import modelo.Partida;
import modelo.ResultadoAjedrez;
import util.MySqlConector;

public class RepositorioPartida {
	 private static final Logger logger = LogManager.getLogger(RepositorioPartida.class);
	    private final MySqlConector mysqlConector;
	    private List<Partida> partidas; 
	    
	    public RepositorioPartida(MySqlConector mysqlConector) throws AppException {
	        this.mysqlConector = mysqlConector;
	        this.partidas = this.read(); 
	    }


	    private Partida mapearResultSetAObjeto(ResultSet rs) throws SQLException {
	        Partida p = new Partida();
	        p.setId(rs.getInt("id_partida")); // Usamos el alias
	        
	        // Mapeo de Fecha
	        java.sql.Timestamp ts = rs.getTimestamp("fecha");
	        if (ts != null) p.setFecha(ts.toLocalDateTime());
	        
	        // Mapeo de Resultado
	        p.setResultado(ResultadoAjedrez.valueOf(rs.getString("resultado")));

	        // CREACIÓN DEL JUGADOR COMPLETO (Hidratación)
	        Jugador j = new Jugador();
	        j.setId(rs.getInt("id_blancas")); 
	        j.setNombre(rs.getString("nombre"));    // Viene del JOIN
	        j.setEmail(rs.getString("email"));      // Viene del JOIN
	        j.setEloPuntos(rs.getInt("elo_puntos")); // Viene del JOIN
	        
	        p.setJugadorBlancas(j); // Conectamos el jugador a la partida
	        
	        return p;
	    }
	    
	    public List<Partida> read() throws AppException {
	        List<Partida> listaBD = new ArrayList<>();
	        String sql = "SELECT p.id AS id_partida, p.fecha, p.resultado, p.id_blancas, " +
	                "j.nombre, j.email, j.elo_puntos " +
	                "FROM sandovalcristinaPartida p " +
	                "LEFT JOIN sandovalcristinaJugador j ON p.id_blancas = j.id";

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
	    
	    public void guardar(Partida partida) throws AppException {
	        // VALIDACIÓN: No puede haber más de 5 partidas en total 
	        if (this.partidas.size() >= 5) {
	            throw new AppException("Límite alcanzado: No se pueden registrar más de 5 partidas."); 
	            
	        }

	        String sql = "INSERT INTO sandovalcristinaPartida (id_blancas, fecha, resultado) VALUES (?, ?, ?)";

	        try (Connection conn = mysqlConector.getConnect();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            // 1. Pasamos el ID del objeto Jugador que está dentro de la Partida [cite: 22]
	            stmt.setInt(1, partida.getJugadorBlancas().getId());
	            
	            // 2. Pasamos la fecha convertida a Timestamp
	            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(partida.getFecha()));
	            
	            // 3. Pasamos el resultado como String (nombre del Enum)
	            stmt.setString(3, partida.getResultado().name());

	            stmt.executeUpdate();
	            
	            // Sincronización: Añadimos la partida a la caché de memoria 
	            this.partidas.add(partida);
	            logger.info("Partida registrada con éxito. Total en memoria: " + partidas.size());

	        } catch (SQLException e) {
	            throw new AppException("Error al insertar la partida en la BD: " + e.getMessage());
	        } finally {
	            mysqlConector.release();
	        }
	    }
	    
	    /**
	     * MÈTODO: listarPartidasPorFecha
	     * REQUISITO: Listar las partidas ordenadas por fechas 
	     */
	    public List<Partida> listarPartidasPorFecha() {
	        List<Partida> ordenada = new ArrayList<>(this.partidas);
	        // Ordenamos de la más antigua a la más reciente
	        ordenada.sort((p1, p2) -> p1.getFecha().compareTo(p2.getFecha()));
	        return ordenada;
	    }
	    
	    public List<Partida> getPartidas() {
	        return partidas;
	    }
}
