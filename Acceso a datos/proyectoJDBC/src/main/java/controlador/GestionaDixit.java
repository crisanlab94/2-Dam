package controlador;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import excepciones.MiExcepcion;
import modelo.Resultado;
import modelo.SandovalCristinaJugador;
import modelo.SandovalCristinaPartida;
import servicio.DixitServicio;

public class GestionaDixit {

    private static final Logger logger = LogManager.getLogger(GestionaDixit.class);

    public static void main(String[] args) {
        DixitServicio servicio = null;
    	try {
            // *** FASE DE INICIALIZACIÓN Y CONEXIÓN ***
            logger.info("--- 1. INICIANDO PROGRAMA ---");
            
            servicio = new DixitServicio();
            
            logger.info("--- 2. CONEXIÓN EXITOSA ---");
            
            // --- INICIO DEL CÓDIGO DEL JUEGO ---
            
            logger.info("--- INICIO DEL TORNEO DIXIT  ---");
            logger.info("\n=== FASE DE LIMPIEZA INICIAL ===");
	        servicio.borrarTodoElJuego(); 
	        logger.info("Base de datos de partidas y jugadores limpia y AUTO_INCREMENT reseteado.");
            
            // CREACIÓN DE JUGADORES Y DATOS INICIALES
            logger.info("\n=== CREACIÓN DE 4 JUGADORES INICIALES ===");
            
            // Los IDs se actualizarán automáticamente al guardarse en la BD.
            SandovalCristinaJugador j1 = new SandovalCristinaJugador(0, "José Lopez", "joseLopez@mail.com", "Lopez", 0); 
            SandovalCristinaJugador j2 = new SandovalCristinaJugador(0, "Maria Moreno", "mariaMoreno@mail.com", "Maria", 0);
            SandovalCristinaJugador j3 = new SandovalCristinaJugador(0, "Jose Manuel Ruiz", "joseRuiz@mail.com", "Joselito", 0);
            SandovalCristinaJugador j4 = new SandovalCristinaJugador(0, "Sofia Jaen", "sofiaJaen@mail.com", "Sofii", 0);
            
            // Guarda los jugadores (El método guardar actualiza el ID real en los objetos)
            servicio.guardarJugador(j1); 
            servicio.guardarJugador(j2);
            servicio.guardarJugador(j3);
            servicio.guardarJugador(j4);
            logger.info("4 jugadores guardados exitosamente. IDs asignados: 1, 2, 3, 4.");
           
            
         // --- SIMULACIÓN DE 5 PARTIDAS (INTEGRADA EN EL MAIN) ---
            logger.info("\n=== INICIO DE LA SIMULACIÓN DE 5 PARTIDAS ===");
            
            
            
            // ----------------------------------------------------------------------------------
            // PARTIDA 1: José Lopez (Resultado: TODOS aciertan)
            // Constructor: (ID, NARRADOR, FECHA, RESULTADO)
            // ----------------------------------------------------------------------------------
            logger.info("\n-- [Partida 1: Narrador José Lopez (TODOS)] --");
            servicio.crearPartida(new SandovalCristinaPartida(1, j1, LocalDate.of(2025, 12, 1), Resultado.TODOS));
            servicio.actualizarPuntuacionNarrador(j1.getId(), Resultado.TODOS); // 0 puntos
            servicio.actualizarPuntuacionAcertante(j2.getId(), Resultado.TODOS); // 2 puntos
            servicio.actualizarPuntuacionAcertante(j3.getId(), Resultado.TODOS); // 2 puntos
            servicio.actualizarPuntuacionAcertante(j4.getId(), Resultado.TODOS); // 2 puntos
            
            // ----------------------------------------------------------------------------------
            // PARTIDA 2: Maria Moreno narra (Resultado: ALGUNOS aciertan)
            // Constructor: (ID, NARRADOR, FECHA, RESULTADO)
            // ----------------------------------------------------------------------------------
            logger.info("\n-- [Partida 2: Narrador Maria Moreno (ALGUNOS)] --");
            servicio.crearPartida(new SandovalCristinaPartida(2, j2, LocalDate.of(2025, 12, 2), Resultado.ALGUNOS));
            servicio.actualizarPuntuacionNarrador(j2.getId(), Resultado.ALGUNOS); 
            servicio.actualizarPuntuacionAcertante(j1.getId(), Resultado.ALGUNOS); 
            servicio.actualizarPuntuacionAcertante(j3.getId(), Resultado.ALGUNOS); 
            servicio.actualizarPuntuacionNOAcertante(j4.getId(), Resultado.ALGUNOS); 

            // ----------------------------------------------------------------------------------
            // PARTIDA 3: José Manuel Ruiz narra (Resultado: NADIE acierta)
            // Constructor: (ID, NARRADOR, FECHA, RESULTADO)
            // ----------------------------------------------------------------------------------
            logger.info("\n-- [Partida 3: Narrador José Manuel (NADIE)] --");
            servicio.crearPartida(new SandovalCristinaPartida(3, j3, LocalDate.of(2025, 12, 3), Resultado.NADIE));
            servicio.actualizarPuntuacionNarrador(j3.getId(), Resultado.NADIE); 
            servicio.actualizarPuntuacionNOAcertante(j1.getId(), Resultado.NADIE); 
            servicio.actualizarPuntuacionNOAcertante(j2.getId(), Resultado.NADIE); 
            servicio.actualizarPuntuacionNOAcertante(j4.getId(), Resultado.NADIE); 

            // ----------------------------------------------------------------------------------
            // PARTIDA 4: Sofia Jaen narra (Resultado: POCOS aciertan)
            // Constructor: (ID, NARRADOR, FECHA, RESULTADO)
            // ----------------------------------------------------------------------------------
            logger.info("\n-- [Partida 4: Narrador Sofia Jaen (POCOS)] --");
            servicio.crearPartida(new SandovalCristinaPartida(4, j4, LocalDate.of(2025, 12, 4), Resultado.POCOS));
            servicio.actualizarPuntuacionNarrador(j4.getId(), Resultado.POCOS); 
            servicio.actualizarPuntuacionAcertante(j2.getId(), Resultado.POCOS); 
            servicio.actualizarPuntuacionNOAcertante(j1.getId(), Resultado.POCOS); 
            servicio.actualizarPuntuacionNOAcertante(j3.getId(), Resultado.POCOS); 
            
            // ----------------------------------------------------------------------------------
            // PARTIDA 5: José Lopez narra (Resultado: ALGUNOS aciertan)
            // Constructor: (ID, NARRADOR, FECHA, RESULTADO)
            // ----------------------------------------------------------------------------------
            logger.info("\n-- [Partida 5: Narrador José Lopez (ALGUNOS)] --");
            servicio.crearPartida(new SandovalCristinaPartida(5, j1, LocalDate.of(2025, 12, 5), Resultado.ALGUNOS));
            servicio.actualizarPuntuacionNarrador(j1.getId(), Resultado.ALGUNOS); 
            servicio.actualizarPuntuacionAcertante(j2.getId(), Resultado.ALGUNOS); 
            servicio.actualizarPuntuacionAcertante(j4.getId(), Resultado.ALGUNOS); 
            servicio.actualizarPuntuacionNOAcertante(j3.getId(), Resultado.ALGUNOS); 

            logger.info("\n=== 5 PARTIDAS SIMULADAS Y GUARDADAS CON ÉXITO ===");
            
            
         // --- CONTROL DE EXCEPCIONES: Límite de Partidas  ---
            logger.info("\n=== INTENTO DE CREAR LA SEXTA PARTIDA (CONTROL DE EXCEPCIÓN) ===");
            try {
                // Constructor: (ID, NARRADOR, FECHA, RESULTADO)
                SandovalCristinaPartida p6 = new SandovalCristinaPartida(6, j3, LocalDate.of(2025, 12, 6), Resultado.NADIE);
                servicio.crearPartida(p6);
                logger.error("ERROR: La partida 6 fue creada, el control de límite falló.");
            } catch (MiExcepcion e) {
                logger.info("Control exitoso: Excepción capturada al intentar crear la 6ª partida: " + e.getMessage());
            }
            
         // --- CONTROL DE EXCEPCIONES: Narrador  en fin de semana  ---
            logger.info("\n=== INTENTO DE CREAR PARTIDA CON Narrador EN FIN DE SEMANA ===");
            LocalDate sabado = LocalDate.now().with(DayOfWeek.SATURDAY); 
            try {
               // Constructor: (ID, NARRADOR, FECHA, RESULTADO)
            	//j1 es narador
               SandovalCristinaPartida p7 = new SandovalCristinaPartida(7, j1, sabado, Resultado.NADIE); 
               servicio.crearPartida(p7);
               logger.error("ERROR: Partida en fin de semana para 'elNarrador' fue creada, el control falló.");
           } catch (MiExcepcion e) {
               logger.info("Control exitoso: Excepción capturada para 'elNarrador' en fin de semana: " + e.getMessage());
           }
            
         // --- FASE DE REPORTES (GENERACIÓN DE EVIDENCIAS) ---
            
            logger.info("\n===============================");
            logger.info("=== RESULTADOS Y REPORTES FINALES ===");
            logger.info("===============================");
            
            // Reporte 1: Jugador con mayor puntuación
            SandovalCristinaJugador ganador = servicio.mostrarJugadorConMayorPuntuacion();
            if (ganador != null) {
                logger.info("-> JUGADOR GANADOR (Mayor Puntuación): " + ganador.getNick() + " con " + ganador.getPuntosTotales() + " puntos.");
            } else {
                 logger.warn("No se encontró ningún jugador.");
            }
            
            //Listar jugadores ordenados por puntos (DESC)
            List<SandovalCristinaJugador> ranking = servicio.listarJugadoresOrdenadosPorPuntos();
            logger.info("\n-> RANKING DE JUGADORES (DESC):");
            for (SandovalCristinaJugador jugador : ranking) {
                 logger.info("   - Nick: " + jugador.getNick() + ", Puntos: " + jugador.getPuntosTotales());
            }
            
            // Listar partidas ordenadas por fecha (ASC)
            List<SandovalCristinaPartida> listadoPartidas = servicio.listarPartidasOrdenadasPorFecha();
            logger.info("\n-> LISTADO DE PARTIDAS (Ordenadas por Fecha ASC):");
            for (SandovalCristinaPartida partida : listadoPartidas) {
              
                 SandovalCristinaJugador narradorPartida = servicio.buscarJugadorPorId(partida.getNarrador().getId());
                 String nickNarrador = (narradorPartida != null) ? narradorPartida.getNick() : "Desconocido";

                 logger.info("   - ID: " + partida.getId() + ", Fecha: " + partida.getFecha() + ", Narrador: " + nickNarrador + ", Resultado: " + partida.getResultado());
            }


        } catch (MiExcepcion e) { 
            logger.fatal("¡ERROR FATAL! El programa se detiene. Revisar la base de datos o el código.");
            logger.fatal("Causa del fallo: " + e.getMessage());
        } finally {
            logger.info("\n--- FIN DEL PROGRAMA ---");
        }
    }
}