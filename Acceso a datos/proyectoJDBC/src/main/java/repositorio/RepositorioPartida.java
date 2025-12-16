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

import excepciones.MiExcepcion;
import modelo.Resultado;
import modelo.SandovalCristinaJugador;
import modelo.SandovalCristinaPartida;
import utiles.MysqlConector;

public class RepositorioPartida {

	private static final Logger logger = LogManager.getLogger(RepositorioPartida.class);
	private MysqlConector conector;
	private List<SandovalCristinaPartida> lista;


	public RepositorioPartida(MysqlConector conector) {
		this.conector = conector;
		this.lista=new ArrayList<>(); //Para evitar NullPointer
		try {
			this.lista = this.listarTodas();
		} catch (MiExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void guardar(SandovalCristinaPartida partida) throws MiExcepcion { 
		String sql = "INSERT INTO SandovalCristinaPartida (narrador_id,torneo_id, fecha, resultado) VALUES (?, ?, ?,?)";

		try {
            Connection conn = conector.getConnect(); 
            
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setInt(1, partida.getNarrador().getId());
				stmt.setInt(2, partida.getTorneo_id());
				stmt.setDate(3, Date.valueOf(partida.getFecha()));
				stmt.setString(4, partida.getResultado().toString());

				stmt.executeUpdate();
				logger.info("Partida guardada correctamente con fecha: " + partida.getFecha());

				if (this.lista != null) {
                    this.lista.add(partida);
                    logger.debug("Partida añadida a la caché del repositorio.");
                }
				
			} catch (SQLException e) {
				logger.error("Error al guardar la partida", e);
                throw new MiExcepcion("Error de base de datos al guardar la partida.");
			}
        } catch (MiExcepcion e) {
            throw e;
        } finally { 
            conector.release();
        }
	}

	public List<SandovalCristinaPartida> listarTodas() throws MiExcepcion { 
		List<SandovalCristinaPartida> listaTemporal = new ArrayList<SandovalCristinaPartida>();

		String sql = "SELECT p.*, j.nombre, j.email, j.nick, j.puntosTotales " +
				"FROM SandovalCristinaPartida p " +
				"JOIN SandovalCristinaJugador j ON p.narrador_id = j.id " +
				"ORDER BY p.fecha ASC";

		try {
            Connection conn = conector.getConnect(); 
            
			try (PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					SandovalCristinaJugador narrador = new SandovalCristinaJugador();
					narrador.setId(rs.getInt("narrador_id"));
					narrador.setNombre(rs.getString("nombre"));
					narrador.setEmail(rs.getString("email"));
					narrador.setNick(rs.getString("nick"));
					narrador.setPuntosTotales(rs.getInt("puntosTotales"));

					SandovalCristinaPartida p = new SandovalCristinaPartida();
					p.setId(rs.getInt("id"));
					p.setNarrador(narrador);
					p.setTorneo_id(rs.getInt("torneo_id"));
					p.setFecha(rs.getDate("fecha").toLocalDate());
					p.setResultado(Resultado.valueOf(rs.getString("resultado")));

					listaTemporal.add(p);
				}
			} catch (SQLException e) {
				logger.error("Error al listar partidas", e);
                throw new MiExcepcion("Error de base de datos al listar partidas.");
			}
        } catch (MiExcepcion e) {
            throw e;
        } finally { 
            conector.release();
        }
		return listaTemporal;
	}
	
	
	
	

	public List<SandovalCristinaPartida> getLista() {
		return lista;
	}

	public void setLista(List<SandovalCristinaPartida> lista) {
		this.lista = lista;
	}

	public void borrarTodas() throws MiExcepcion {
	    // Usamos TRUNCATE TABLE para eliminar todas las filas Y resetear el AUTO_INCREMENT
	    String sql = "TRUNCATE TABLE SandovalCristinaPartida"; 

	    try {
	        Connection conn = conector.getConnect();
	        
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.executeUpdate(); 
	            logger.warn("Tabla SandovalCristinaPartida TRUNCADA y AUTO_INCREMENT reseteado.");

	            if (this.lista != null) {
                    this.lista.clear();
                    logger.debug("Caché local de partidas vaciada.");
                }
	        
	        } catch (SQLException e) {
	            logger.error("Error al intentar truncar todas las partidas", e);
	            throw new MiExcepcion("Error de base de datos al borrar partidas.");
	        }
	    } catch (MiExcepcion e) {
	        throw e;
	    } finally { 
	        conector.release();
	    }
	}
}