package controlador;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Jugador;
import modelo.Partida;
import modelo.ResultadoAjedrez;
import repositorio.RepositorioJugador;
import repositorio.RepositorioPartida;
import servicio.ServicioTorneo;
import util.MySqlConector;

public class GestionaTorneo {
    private static final Logger logger = LogManager.getLogger(GestionaTorneo.class);

    public static void main(String[] args) {
        
        try {
            // 1. INICIALIZACIÓN
            MySqlConector conector = new MySqlConector();
            RepositorioJugador repoJ = new RepositorioJugador(conector);
            RepositorioPartida repoP = new RepositorioPartida(conector);
            ServicioTorneo servicio = new ServicioTorneo(repoJ, repoP);

            logger.info("--- INICIANDO SISTEMA DE TORNEO DE AJEDREZ ---");

            // 2. CARGA DE JUGADORES (Evitando duplicados si ya existen)
            if (repoJ.buscarPorId(1) == null) servicio.darDeAltaJugador(new Jugador(1, "Magnus Carlsen", "magnus@chess.com", 2850));
            if (repoJ.buscarPorId(2) == null) servicio.darDeAltaJugador(new Jugador(2, "Hikaru Nakamura", "hikaru@chess.com", 2800));
            if (repoJ.buscarPorId(3) == null) servicio.darDeAltaJugador(new Jugador(3, "Fabiano Caruana", "fabi@chess.com", 2780));
            if (repoJ.buscarPorId(4) == null) servicio.darDeAltaJugador(new Jugador(4, "Ian Nepo", "ian@chess.com", 2770));

            Jugador magnus = repoJ.buscarPorId(1);
            Jugador hikaru = repoJ.buscarPorId(2);

            // 3. REGISTRO DE PARTIDAS (Hasta completar el cupo de 5)
            // Solo insertamos si hay hueco en la base de datos
            if (repoP.getPartidas().size() < 5) {
                logger.info("Añadiendo partidas para completar el torneo...");
                servicio.registrarNuevaPartida(new Partida(0, magnus, LocalDateTime.now(), ResultadoAjedrez.GANA_BLANCAS));
                if (repoP.getPartidas().size() < 5) {
                    servicio.registrarNuevaPartida(new Partida(0, hikaru, LocalDateTime.now().minusMinutes(10), ResultadoAjedrez.TABLAS));
                }
            }

            // ==========================================================
            // 4. INFO COMPLETA DE CONSULTAS (Rúbrica)
            // ==========================================================
            
            // CONSULTA A: Mejor Jugador (Top ELO)
            Jugador top = repoJ.getMejorJugador();
            logger.info(">>> CONSULTA A: MEJOR JUGADOR");
            logger.info("El líder actual es: " + (top != null ? top.getNombre() + " con " + top.getEloPuntos() + " puntos." : "No hay datos."));

            // CONSULTA B: Ranking General Ordenado
            logger.info(">>> CONSULTA B: RANKING POR PUNTOS (DESC)");
            List<Jugador> ranking = repoJ.listarJugadoresPorPuntos();
            for (int i = 0; i < ranking.size(); i++) {
                logger.info((i + 1) + "º Posición: " + ranking.get(i));
            }

            // CONSULTA C: Historial Cronológico
            logger.info(">>> CONSULTA C: HISTORIAL CRONOLÓGICO DE PARTIDAS");
            List<Partida> historial = servicio.obtenerHistorialFechas();
            for (Partida p : historial) {
                logger.info("Fecha: " + p.getFecha() + " | " + p.getJugadorBlancas().getNombre() + " -> " + p.getResultado());
            }

            // 5. PRUEBA DE LA 6ª PARTIDA (Provocar la excepción)
            logger.info(">>> INTENTANDO REGISTRAR LA 6ª PARTIDA PARA VALIDAR EL LÍMITE...");
            servicio.registrarNuevaPartida(new Partida(0, magnus, LocalDateTime.now(), ResultadoAjedrez.GANA_BLANCAS));

        } catch (AppException e) {
            // Captura el límite de 5 partidas o errores de ID
            logger.error("HA OCURRIDO UNA EXCEPCIÓN: " + e.getMessage());
        }
    }
}