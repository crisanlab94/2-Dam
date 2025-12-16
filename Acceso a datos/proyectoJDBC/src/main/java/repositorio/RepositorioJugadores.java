package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Statement;

import excepciones.MiExcepcion;
import modelo.SandovalCristinaJugador;
import utiles.MysqlConector;

public class RepositorioJugadores {
	private static final Logger logger = LogManager.getLogger(RepositorioJugadores.class);
	private MysqlConector conector;
	List<SandovalCristinaJugador> lista ;

	public RepositorioJugadores(MysqlConector conector) {
		this.conector = conector;
		try {
			this.lista=this.listarTodos();
		} catch (MiExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	public void guardar(SandovalCristinaJugador jugador) throws MiExcepcion {
		String sql = "INSERT INTO SandovalCristinaJugador (nombre, email, nick, puntosTotales) VALUES (?, ?, ?, ?)"; 

	    try {
	        Connection conn = this.conector.getConnect();
	        
	        // 1. Usar Statement.RETURN_GENERATED_KEYS para decirle a MySQL que nos devuelva el ID
	        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            
	            stmt.setString(1, jugador.getNombre());
	            stmt.setString(2, jugador.getEmail());
	            stmt.setString(3, jugador.getNick());
	            stmt.setInt(4, jugador.getPuntosTotales());

	            // Ejecuta el INSERT
	            stmt.executeUpdate();
	            
	            // 2. RECUPERAR EL ID GENERADO POR LA BD
	            try (ResultSet rs = stmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    int idGenerado = rs.getInt(1);
	                    jugador.setId(idGenerado); 
	                    logger.info("Jugador guardado correctamente: " + jugador.getNombre() + " con ID asignado: " + idGenerado);
	                
	                    if (this.lista != null) {
                            this.lista.add(jugador);
                            logger.debug("Jugador añadido a la caché del repositorio.");
                        }
	                } else {
	                    logger.warn("El INSERT fue exitoso pero no se pudo recuperar el ID autogenerado.");
	                }
	            } 
	            
	        } catch (SQLException e) {
	            logger.error("Error al guardar jugador", e);
	            throw new MiExcepcion("Error de base de datos al guardar el jugador.");
	        }
	    } catch (MiExcepcion e) {
	        throw e;
	    } finally { 
	        conector.release();
	    }
	}

	public List<SandovalCristinaJugador> listarTodos() throws MiExcepcion {
		List<SandovalCristinaJugador> lista = new ArrayList<SandovalCristinaJugador>();
		String sql = "SELECT * FROM SandovalCristinaJugador";

		try {
            Connection conn = conector.getConnect();
            
			try (PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					SandovalCristinaJugador j = new SandovalCristinaJugador();
					j.setId(rs.getInt("id"));
					j.setNombre(rs.getString("nombre"));
					j.setEmail(rs.getString("email"));
					j.setNick(rs.getString("nick"));
					j.setPuntosTotales(rs.getInt("puntosTotales"));
					lista.add(j);
				}
			} catch (SQLException e) {
				logger.error("Error al listar jugadores", e);
                throw new MiExcepcion("Error de base de datos al listar jugadores.");
			}
        } catch (MiExcepcion e) {
            throw e;
        } finally { 
            conector.release();
        }
		return lista;
	}


	public SandovalCristinaJugador buscarPorId(int id) throws MiExcepcion {
		String sql = "SELECT * FROM SandovalCristinaJugador WHERE id = ?";
		SandovalCristinaJugador jugador = null;

		try {
            Connection conn = conector.getConnect();
            
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setInt(1, id);
				try( ResultSet rs = stmt.executeQuery()){

				if (rs.next()) {
					jugador = new SandovalCristinaJugador();
					jugador.setId(rs.getInt("id"));
					jugador.setNombre(rs.getString("nombre"));
					jugador.setEmail(rs.getString("email"));
					jugador.setNick(rs.getString("nick"));
					jugador.setPuntosTotales(rs.getInt("puntosTotales"));
				}
				}
			} catch (SQLException e) {
				logger.error("Error al buscar jugador por ID: " + id, e);
				throw new MiExcepcion("Fallo en la conexión durante la búsqueda. Causa: " + e.getMessage());
			}
        } catch (MiExcepcion e) {
            throw e;
        } finally { 
            conector.release();
        }
		return jugador;
	}

	public void actualizarPuntos(int id, int nuevosPuntos) throws MiExcepcion {
		String sql = "UPDATE SandovalCristinaJugador SET puntosTotales = ? WHERE id = ?";

		try {
            Connection conn = conector.getConnect();
            
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setInt(1, nuevosPuntos);
				stmt.setInt(2, id);
				stmt.executeUpdate();
				logger.info("Puntos actualizados para el jugador ID " + id);

			} catch (SQLException e) {
				logger.error("Error al actualizar puntos", e);
                throw new MiExcepcion("Error de base de datos al actualizar puntos.");
			}
        } catch (MiExcepcion e) {
            throw e;
        } finally { 
            conector.release();
        }
	}
	
	public void borrarTodos() throws MiExcepcion {
		String deleteSql = "DELETE FROM SandovalCristinaJugador"; 

	    String resetSql = "ALTER TABLE SandovalCristinaJugador AUTO_INCREMENT = 1"; 

	    try {
	        Connection conn = conector.getConnect();
	        
	        try (PreparedStatement stmtDelete = conn.prepareStatement(deleteSql);
	             PreparedStatement stmtReset = conn.prepareStatement(resetSql)) {

	            // Ejecutar DELETE (para que las partidas ya no sean un problema, ya que se borran antes)
	            int filasBorradas = stmtDelete.executeUpdate();
	            
	            if (this.lista != null) {
	            	 this.lista.clear();
	            	 logger.debug("Caché local de jugadores vaciada.");
	            	}
	            
	            // Ejecutar ALTER TABLE (Resetea el contador)
	            stmtReset.executeUpdate(); 
	            
	            logger.warn("Se han eliminado " + filasBorradas + " jugadores de la tabla. AUTO_INCREMENT reseteado.");

	        } catch (SQLException e) {
	            logger.error("Error al intentar borrar/resetear jugadores", e);
	            throw new MiExcepcion("Error de base de datos al borrar jugadores.");
	        }
	    } catch (MiExcepcion e) {
	        throw e;
	    } finally {
	        conector.release();
	    }
	}

	public List<SandovalCristinaJugador> getLista() {
		return lista;
	}

	public void setLista(List<SandovalCristinaJugador> lista) {
		this.lista = lista;
	}
	
	
	
	
}