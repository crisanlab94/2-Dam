package controlador;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.*;
import servicio.ServicioDrone;

public class GestionaHogar {
    private static final Logger logger = LogManager.getLogger(GestionaHogar.class);

    public static void main(String[] args) {
        try {
            ServicioDrone servicio = new ServicioDrone();
            logger.info("--- 1. SISTEMA DE CONTROL DE DRONES INICIADO ---");

            // ALTA MANUAL
            Drone dNuevo = new Drone(106, "Phaton 4", Categoria.RECONOCIMIENTO, LocalDateTime.now(), 100, false);
            dNuevo.setComponente(new Componente(600.0, "Fibra Vidrio", true));
            servicio.registrarDrone(dNuevo);

            // VOLCADO TOTAL DE INFORMACIÓN
            logger.info("\n=== 2. INVENTARIO COMPLETO (INFO DE AMBAS TABLAS) ===");
            List<Drone> todos = servicio.listarTodo();
            for (Drone d : todos) {
                logger.info("------------------------------------------------------------------");
                logger.info("DRONE: [ID: {}] | Modelo: {} | Categoría: {}", d.getId(), d.getModelo(), d.getCategoria());
                logger.info("ESTADO: Batería: {}% | Necesita Reparación: {} | Última Misión: {}", 
                    d.getNivelBateria(), d.isNecesitaReparacion(), d.getUltimaMision());
                logger.info("COMPONENTES: Peso: {}g | Material: {} | GPS Activo: {}", 
                    d.getComponente().getPesoGramos(), d.getComponente().getMaterial(), d.getComponente().isGpsActivo());
            }

            // TOP 3 RANKING
            logger.info("\n=== 3. TOP 3 DRONES CON MEJOR BATERÍA ===");
            List<Drone> top = servicio.obtenerTop3Bateria();
            for (int i = 0; i < top.size(); i++) {
                logger.info("Puesto #{}: {} (Batería: {}%)", (i+1), top.get(i).getModelo(), top.get(i).getNivelBateria());
            }

            // INTENTO DE ERROR (ID REPETIDO)
            logger.info("\n=== 4. PRUEBA CONTROL DE EXCEPCIÓN (ID MANUAL REPETIDO) ===");
            try {
                Drone dError = new Drone(101, "Clon", Categoria.DEFENSA, LocalDateTime.now(), 50, false);
                dError.setComponente(new Componente(100, "Plástico", false));
                servicio.registrarDrone(dError);
            } catch (AppException e) {
                logger.error("CAPTURA EXITOSA: " + e.getMessage());
            }

            // ACTUALIZAR Y BORRAR
            servicio.mantenimiento(102, 100);
            servicio.eliminarDrone(105);
            logger.info("\n--- 5. SIMULACIÓN DE HANGAR FINALIZADA ---");

        } catch (AppException e) {
            logger.fatal("¡ERROR FATAL!: " + e.getMessage());
        }
    }
}
