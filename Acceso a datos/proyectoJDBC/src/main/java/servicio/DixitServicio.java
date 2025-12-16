package servicio;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import excepciones.MiExcepcion;
import modelo.PuntosJugadorComparator;
import modelo.Resultado;
import modelo.SandovalCristinaJugador;
import modelo.SandovalCristinaPartida;
import repositorio.RepositorioJugadores;
import repositorio.RepositorioPartida;
import utiles.MysqlConector;

public class DixitServicio {
	
	private static final Logger logger = LogManager.getLogger(DixitServicio.class);
	private MysqlConector conector;
	private RepositorioJugadores repoJugador;
    private RepositorioPartida repoPartida;

	public DixitServicio() throws MiExcepcion {
		try {
	        this.conector = new MysqlConector();
	        this.repoJugador = new RepositorioJugadores(conector); 
	        this.repoPartida = new RepositorioPartida(conector); 
	    } catch (MiExcepcion e) {
	    	logger.error("Error al inicializar el servicio y la conexión", e);
	    	throw new MiExcepcion("Error al inicializar el servicio y la conexión: " + e.getMessage());
	 	}
	}
	
	
	 // --- MÉTODOS AUXILIARES Y CRUD ---

	public SandovalCristinaJugador buscarJugadorPorId(int id) throws MiExcepcion {
        return repoJugador.buscarPorId(id);
    }  
	
    public List<SandovalCristinaPartida> listarPartidas() throws MiExcepcion {
        return repoPartida.listarTodas();
    }
    
    public List<SandovalCristinaJugador> listarJugadores() throws MiExcepcion {
        return repoJugador.listarTodos();
    }
    
    public void guardarJugador(SandovalCristinaJugador jugador) throws MiExcepcion {
        repoJugador.guardar(jugador);
    }
    
    public void borrarTodoElJuego() throws MiExcepcion {
      
        borrarTodasLasPartidas(); 
        repoJugador.borrarTodos(); 
    }
    
    public void borrarTodasLasPartidas() throws MiExcepcion {
        repoPartida.borrarTodas();
    }
    
    public void actualizarPuntosJugador(int id, int nuevosPuntos) throws MiExcepcion {
        repoJugador.actualizarPuntos(id, nuevosPuntos);
    }
    
    public int contarPartidas() throws MiExcepcion {
        return repoPartida.listarTodas().size();
    }
   
	
    // --- LÓGICA DE GESTIÓN DE PARTIDAS ---

    public void crearPartida(SandovalCristinaPartida partida) throws MiExcepcion {
        
        // Criterio 1: Límite de 5 partidas 
        if (contarPartidas() >= 5) {
            throw new MiExcepcion("ERROR: Se ha alcanzado el límite de 5 partidas por defecto.");
        }
        
        // Criterio 2: El narrador 'elNarrador' no puede narrar en fin de semana
        else  if (partida.getNarrador().getNick().equals("elNarrador") && esFinDeSemana(partida.getFecha())) {
             throw new MiExcepcion("ERROR: 'elNarrador' no puede narrar partidas en fin de semana.");
        }
        
        repoPartida.guardar(partida);
    }
    
  
    
    private boolean esFinDeSemana(LocalDate fecha) {
        int diaNumerico = fecha.getDayOfWeek().getValue();
        return diaNumerico == 6 || diaNumerico == 7;
    }
    
    // --- LÓGICA DE PUNTUACIONES  ---
 
    /**
     *  incrementar puntos y actualizar la BD.
     */
    private void sumarPuntos(int idJugador, int puntosASumar) throws MiExcepcion {
        if (puntosASumar > 0) {
            // Obtener el jugador actual de la BD
            SandovalCristinaJugador jugadorBD = repoJugador.buscarPorId(idJugador);
            
            if (jugadorBD == null) {
                logger.warn("No se pudo sumar puntos. Jugador ID " + idJugador + " no encontrado en la BD.");
                
            }
            
            // Calcular y actualizar
            int nuevosPuntos = jugadorBD.getPuntosTotales() + puntosASumar;
            actualizarPuntosJugador(idJugador, nuevosPuntos);
            logger.info("Puntuación actualizada: Jugador ID " + idJugador + " suma " + puntosASumar + " puntos. Total: " + nuevosPuntos);
        }
    }


    /**
     * Actualiza la puntuación del Narrador.
     * Si es ALGUNOS/POCOS: 3 puntos. Si es TODOS/NADIE: 0 puntos.
     */
    public void actualizarPuntuacionNarrador(int idJugador, Resultado resultado) throws MiExcepcion {
        int puntos = 0;
        
        // Si algunos aciertan: 3 puntos
        if (resultado == Resultado.ALGUNOS || resultado == Resultado.POCOS) { 
            puntos = 3;
        } 
        
        sumarPuntos(idJugador, puntos);
    }

    /**
     * Actualiza la puntuación de un Jugador NO Acertante.
     * Con dos puntos si el resultado ha sido TODOS o NADIE.
     */
    public void actualizarPuntuacionNOAcertante(int idJugador, Resultado resultado) throws MiExcepcion {
        int puntos = 0;
        
        // Con dos puntos si el resultado ha sido TODOS o NADIE
        if (resultado == Resultado.TODOS || resultado == Resultado.NADIE) {
            puntos = 2;
        }

        sumarPuntos(idJugador, puntos);
    }

    /**
     * Actualiza la puntuación de un Jugador Acertante.
     * Con dos puntos si es TODOS/NADIE. Con tres puntos si es ALGUNOS/POCOS.
     */
    public void actualizarPuntuacionAcertante(int idJugador, Resultado resultado) throws MiExcepcion {
        int puntos = 0;
        
        // Con tres puntos si recibe ALGUNOS O POCOS
        if (resultado == Resultado.ALGUNOS || resultado == Resultado.POCOS) {
            puntos = 3;
        } 
        // Con dos puntos si el resultado ha sido TODOS o NADIE
        else if (resultado == Resultado.TODOS || resultado == Resultado.NADIE) {
            puntos = 2;
        }
        
        sumarPuntos(idJugador, puntos);
    }
    
   

    /**
     * Muestra el jugador con mayor puntuación.
     */
    public SandovalCristinaJugador mostrarJugadorConMayorPuntuacion() throws MiExcepcion {
        List<SandovalCristinaJugador> jugadores = repoJugador.listarTodos();
        SandovalCristinaJugador ganador= null;
        if (jugadores.isEmpty()) {
        	ganador=null;
        }
        
         ganador = jugadores.get(0);
        
        // Bucle clásico para encontrar el máximo
        for (int i = 1; i < jugadores.size(); i++) {
            if (jugadores.get(i).getPuntosTotales() > ganador.getPuntosTotales()) {
                ganador = jugadores.get(i);
            }
        }
        return ganador;
    }

    /**
     * Muestra nombre y puntos de cada jugador ordenados por puntos descendentemente.
     */
    public List<SandovalCristinaJugador> listarJugadoresOrdenadosPorPuntos() throws MiExcepcion {
        // 1. Obtener la lista de la base de datos
        List<SandovalCristinaJugador> jugadores = repoJugador.listarTodos();
        
      
        PuntosJugadorComparator comparador = new PuntosJugadorComparator();
        Collections.sort(jugadores, comparador);
        
        // 3. Devolver la lista ordenada
        return jugadores;
    }

    /**
     * Listar las partidas ordenadas por fechas.
     */
    public List<SandovalCristinaPartida> listarPartidasOrdenadasPorFecha() throws MiExcepcion {
        // El repositorio ya hace el ORDER BY fecha ASC
        return repoPartida.listarTodas();
    }
}