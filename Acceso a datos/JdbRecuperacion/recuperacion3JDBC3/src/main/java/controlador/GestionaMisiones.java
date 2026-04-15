package controlador;

import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Astronauta;
import modelo.EstadoMision;
import modelo.Mision;
import modelo.RangoAstronauta;
import repositorio.RepositorioAstronauta;
import repositorio.RepositorioMision;
import servicio.ServicioGalactico;
import util.MySqlConector;

public class GestionaMisiones {
    private static final Logger logger = LogManager.getLogger(GestionaMisiones.class);

    public static void main(String[] args) {
        
        try {
            // 1. INICIALIZACIÓN
            MySqlConector conector = new MySqlConector();
            RepositorioAstronauta repoA = new RepositorioAstronauta(conector);
            RepositorioMision repoM = new RepositorioMision(conector);
            ServicioGalactico servicio = new ServicioGalactico(repoA, repoM);

            logger.info("=== SISTEMA DE CONTROL ESPACIAL ACTIVADO ===");

            // 2. ALTA DE ASTRONAUTAS
            if (repoA.buscarPorId(6) == null) {
                servicio.darDeAltaAstronauta(new Astronauta(6, "Elena Ochoa", RangoAstronauta.COMANDANTE, 500, true));
            }
            if (repoA.buscarPorId(7) == null) {
                servicio.darDeAltaAstronauta(new Astronauta(7, "Pedro Duque", RangoAstronauta.PILOTO, 300, true));
            }

            // 3. PRUEBA DE LÍMITE
            Astronauta elena = repoA.buscarPorId(6);
            logger.info("--- Probando límite de misiones para {} ---", elena.getNombre());
            
            try {
                for (int i = 1; i <= 4; i++) {
                    Mision m = new Mision();
                    m.setAstronauta(elena);
                    m.setNombreNave("Nave-Test-" + i);
                    m.setFechaLanzamiento(LocalDate.now());
                    m.setEstado(EstadoMision.EN_CURSO);
                    m.setCombustibleExtra(false);
                    
                    repoM.guardar(m);
                    logger.info("Misión {} registrada con éxito.", i);
                }
            } catch (AppException e) {
                logger.error("CAPTURA DE ERROR CONTROLADA: {}", e.getMessage());
            }

            // 4. PROCESAR RESULTADO
            List<Mision> todas = repoM.getMisiones();
            if (!todas.isEmpty()) {
                Mision mTerminada = todas.get(todas.size() - 1);
                mTerminada.setEstado(EstadoMision.EXITO);
                servicio.registrarResultadoMision(mTerminada);
            }

            // 5. INFORMES FINALES (Todos con Logger)
            servicio.generarRankingPilotos(); // Asegúrate de cambiar los syso dentro de este método también
            
            Astronauta lider = servicio.obtenerLiderGalactico();
            logger.info("LÍDER GALÁCTICO ACTUAL: {}", (lider != null ? lider.getNombre() : "Nadie"));

            // 6. INFORME DETALLADO (Lo que pediste)
            servicio.mostrarDetalleAgencia();

        } catch (AppException e) {
            logger.error("Error en la lógica de la aplicación: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Error crítico inesperado: ", e);
        }
    }
}