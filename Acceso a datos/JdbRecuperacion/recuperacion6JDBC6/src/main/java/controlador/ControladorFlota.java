package controlador;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Nave;
import modelo.Tripulante;
import repositorio.RepositorioNave;
import repositorio.RepositorioTripulante;
import servicio.ServicioFlota;
import util.MySqlConector;

public class ControladorFlota {
    private static final Logger logger = LogManager.getLogger(ControladorFlota.class);

    public static void main(String[] args) {
        try {
            // 0. INICIALIZACIÓN DE CAPAS
            MySqlConector conector = new MySqlConector();
            RepositorioNave repoNave = new RepositorioNave(conector);
            RepositorioTripulante repoTrip = new RepositorioTripulante(conector);
            ServicioFlota servicio = new ServicioFlota(repoNave, repoTrip);

            logger.info("=== SISTEMA DE GESTIÓN DE FLOTA ESTELAR INICIADO ===");

            // APARTADO 1: Obtener todos (Hidratación 1:N)
            logger.info("--- 1. LISTADO DE NAVES Y SUS TRIPULANTES ---");
            List<Nave> flota = servicio.obtenerFlota();
            for (Nave n : flota) {
                System.out.println(n.toString());
                for (Tripulante t : n.getTripulacion()) {
                    System.out.println("   -> " + t.toString());
                }
            }

            // APARTADO 2: Buscar por ID
            logger.info("--- 2. BUSCANDO NAVE CON ID 1 ---");
            Nave naveEncontrada = repoNave.buscarPorId(1);
            if (naveEncontrada != null) {
                System.out.println("Encontrada: " + naveEncontrada.getNombre());
            }

            // APARTADO 3: Filtrar por Rango
            logger.info("--- 3. FILTRANDO TRIPULANTES POR RANGO: Piloto ---");
            List<Tripulante> pilotos = servicio.getTripulantesPorRango("Piloto");
            for (Tripulante p : pilotos) {
                System.out.println("Piloto detectado: " + p.getNombre());
            }

            // APARTADO 4: Registrar Nuevo Tripulante
            logger.info("--- 4. REGISTRANDO NUEVO TRIPULANTE ---");
            Tripulante nuevo = new Tripulante(0, "Lando Calrissian", "Piloto", true, 1);
            servicio.registrarTripulante(nuevo);
            logger.info("Registro completado con éxito.");

            // APARTADO 5: Actualizar (Ascender)
            logger.info("--- 5. ASCENDER TRIPULANTE CON ID 1 ---");
            servicio.ascender(1, "General de Flota");
            logger.info("Ascenso procesado.");

            // APARTADO 7: Eliminar Inactivos
            logger.info("--- 7. LIMPIANDO PERSONAL INACTIVO EN NAVE 2 ---");
            servicio.limpiarNave(2);
            logger.info("Limpieza finalizada.");

            // --- PRUEBA DE FUEGO: BORRAR NAVE CON TRIPULANTES ---
            logger.info("--- EXTRA: INTENTANDO BORRAR NAVE 1 (TIENE TRIPULACIÓN) ---");
            try {
                // Como Han Solo y Lando están en la Nave 1, esto debería fallar
                servicio.eliminarNave(1); 
            } catch (AppException e) {
                // Capturamos el error de integridad referencial amigablemente
                logger.warn("AVISO CONTROLADO: " + e.getMessage());
            }

            // COMPROBACIÓN FINAL
            logger.info("--- ESTADO FINAL DE LA FLOTA ---");
            List<Nave> flotaFinal = servicio.obtenerFlota();
            for (Nave n : flotaFinal) {
                System.out.println(n.toString());
                for (Tripulante t : n.getTripulacion()) {
                    System.out.println("   [OK] " + t.getNombre() + " - " + t.getRango());
                }
            }

        } catch (AppException e) {
            logger.error("ERROR DE APLICACIÓN: " + e.getMessage());
        } catch (Exception e) {
            logger.error("ERROR CRÍTICO: " + e.getMessage());
            e.printStackTrace();
        }
    }
}