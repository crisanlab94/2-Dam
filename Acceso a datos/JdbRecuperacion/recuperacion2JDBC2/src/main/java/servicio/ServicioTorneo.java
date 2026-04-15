package servicio;



import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Jugador;
import modelo.Partida;
import modelo.ResultadoAjedrez;
import repositorio.RepositorioJugador;
import repositorio.RepositorioPartida;

public class ServicioTorneo {
    private static final Logger logger = LogManager.getLogger(ServicioTorneo.class);
    
    private final RepositorioJugador repoJugador;
    private final RepositorioPartida repoPartida;

    // El servicio recibe ambos para poder coordinarlos [cite: 24, 25]
    public ServicioTorneo(RepositorioJugador repoJugador, RepositorioPartida repoPartida) {
        this.repoJugador = repoJugador;
        this.repoPartida = repoPartida;
    }

    /**
     * 1. REGISTRAR JUGADOR con Validación de Existencia
     */
    public void darDeAltaJugador(Jugador j) throws AppException {
        logger.info("Servicio: Validando registro de jugador: " + j.getNombre());

        // BUSQUEDA PREVIA: Comprobamos si el ID ya está en la caché del repositorio
        if (repoJugador.buscarPorId(j.getId()) != null) {
            logger.warn("El jugador con ID " + j.getId() + " ya está registrado en el sistema.");
            throw new AppException("Error de Registro: El ID " + j.getId() + " ya pertenece a otro jugador.");
        }

        // Si pasa el filtro, procedemos al guardado
        repoJugador.guardar(j);
        logger.info("Jugador dado de alta correctamente.");
    }

    /**
     * 2. REGISTRAR PARTIDA + ACTUALIZAR PUNTOS [cite: 28, 29, 31]
     * Este es el método más importante del examen.
     */
    public void registrarNuevaPartida(Partida p) throws AppException {
        // A. Guardamos la partida (El repositorio validará el límite de 5) [cite: 30]
        repoPartida.guardar(p);

        // B. Lógica de Puntos ELO según el resultado
        int idBlanco = p.getJugadorBlancas().getId();
        Jugador jBlanco = repoJugador.buscarPorId(idBlanco);
        
        if (jBlanco != null) {
            int puntosASumar = 0;
            
            if (p.getResultado() == ResultadoAjedrez.GANA_BLANCAS) {
                puntosASumar = 10; // Victoria blancas [cite: 33]
            } else if (p.getResultado() == ResultadoAjedrez.TABLAS) {
                puntosASumar = 5;  // Empate [cite: 36, 39]
            }
            // Si GANA_NEGRAS, el de blancas suma 0 [cite: 15]

            // C. Mandamos actualizar al repositorio de jugadores
            int nuevoElo = jBlanco.getEloPuntos() + puntosASumar;
            repoJugador.actualizarElo(idBlanco, nuevoElo);
            logger.info("Puntos actualizados: " + jBlanco.getNombre() + " ahora tiene " + nuevoElo + " ELO.");
        }
    }

    /**
     * 3. CONSULTAS PARA LA RÚBRICA [cite: 41, 42, 44]
     */
    public void mostrarEstadisticas() {
        Jugador top = repoJugador.getMejorJugador();
        logger.info("--- ESTADÍSTICAS DEL TORNEO ---");
        logger.info("Mejor Jugador actual: " + (top != null ? top.getNombre() + " (" + top.getEloPuntos() + ")" : "N/A"));
        
        logger.info("Ranking General:");
        for (Jugador j : repoJugador.listarJugadoresPorPuntos()) {
            System.out.println("  > " + j.getNombre() + ": " + j.getEloPuntos() + " ELO");
        }
    }

    public List<Partida> obtenerHistorialFechas() {
        return repoPartida.listarPartidasPorFecha();
    }
}