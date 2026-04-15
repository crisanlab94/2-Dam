package controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Astronauta;
import modelo.Seguridad;
import repositorio.RepositorioAstronauta;
import servicio.ServicioSeguridad;
import util.MySqlConector;

public class GestionaSeguridad {
    private static final Logger logger = LogManager.getLogger(GestionaSeguridad.class);

    public static void main(String[] args) {
        try {
            // 1. INICIALIZACIÓN
            MySqlConector conector = new MySqlConector();
            // El RepositorioAstronauta es el que "manda" en las dos tablas
            RepositorioAstronauta repo = new RepositorioAstronauta(conector);
            ServicioSeguridad servicio = new ServicioSeguridad(repo);

            logger.info("=== SISTEMA INICIADO: MODO REPOSITORIO ÚNICO (1:1) ===");

            // 2. CREACIÓN DEL ASTRONAUTA CON TODOS SUS DATOS
            // Cumplimos tu requisito: Astronauta + Seguridad en un solo pack
            Seguridad seguridadPrivada = new Seguridad(1122, "NIVEL_OMEGA");
            Astronauta astronautaCompleto = new Astronauta(77, "John Shepard", seguridadPrivada);

            // 3. REGISTRO
            logger.info("Enviando astronauta completo al servicio...");
            servicio.registrarAstronautaCompleto(astronautaCompleto);

            // 4. MOSTRAR RESULTADOS
            logger.info("--- LISTADO DE SEGURIDAD ACTUALIZADO ---");
            for (Astronauta a : servicio.obtenerPersonalAutorizado()) {
                // Aquí verás tanto el nombre como el PIN/Nivel gracias al JOIN del repo
                logger.info("DATOS CARGADOS -> {}", a.toString());
            }

        } catch (AppException e) {
            logger.error("ERROR CONTROLADO: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("ERROR DEL SISTEMA: ", e);
        }
    }
}