package controlador;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Detalle;
import modelo.Dispositivo;
import modelo.Tipo;
import servicio.ServicioSmartHome;

/**
 * Controlador principal para la gestión del sistema SmartHome Connect.
 * El controlador solo tiene visibilidad sobre la capa de Servicio.
 */
public class GestionaSmartHome {

    private static final Logger logger = LogManager.getLogger(GestionaSmartHome.class);

    public static void main(String[] args) {
        ServicioSmartHome servicio = null;

        try {
            // --- 1. INICIALIZACIÓN ---
            logger.info("--- INICIANDO SISTEMA DE GESTIÓN SMARTHOME ---");
            servicio = new ServicioSmartHome();
            logger.info("Servicio inicializado y conexión a base de datos establecida.");

            // --- 2. FASE DE LIMPIEZA (Opcional para simulación) ---
            servicio.borrarTodo();
            logger.info("Base de datos limpia para iniciar simulación.");

            // --- 3. ALTA DE 5 DISPOSITIVOS (INTEGRADA) ---
            logger.info("\n=== FASE DE ALTA DE DISPOSITIVOS ===");
            
            // Creamos 5 dispositivos con sus detalles (1:1)
            Dispositivo d1 = new Dispositivo("D01", "Termostato Inteligente", Tipo.SENSOR, LocalDate.of(2024, 1, 10), true);
            d1.setDetalle(new Detalle(1.5, "v1.0"));
            
            Dispositivo d2 = new Dispositivo("D02", "Cámara Seguridad Pro", Tipo.CAMARA, LocalDate.of(2024, 2, 15), true);
            d2.setDetalle(new Detalle(12.8, "v2.1"));
            
            Dispositivo d3 = new Dispositivo("D03", "Bombilla LED Salón", Tipo.LUZ, LocalDate.of(2024, 3, 5), false);
            d3.setDetalle(new Detalle(8.2, "v1.1"));
            
            Dispositivo d4 = new Dispositivo("D04", "Sensor Humedad Jardín", Tipo.SENSOR, LocalDate.of(2024, 3, 20), true);
            d4.setDetalle(new Detalle(0.5, "v1.0"));
            
            Dispositivo d5 = new Dispositivo("D05", "Cámara Garaje", Tipo.CAMARA, LocalDate.of(2024, 4, 12), true);
            d5.setDetalle(new Detalle(10.5, "v2.0"));

            // Guardamos a través del servicio
            servicio.crearDispositivo(d1);
            servicio.crearDispositivo(d2);
            servicio.crearDispositivo(d3);
            servicio.crearDispositivo(d4);
            servicio.crearDispositivo(d5);
            logger.info("5 dispositivos registrados exitosamente.");

            // --- 4. LISTADO COMPLETO (INFO HIDRATADA DE AMBAS TABLAS) ---
            logger.info("\n=== LISTADO COMPLETO DE DISPOSITIVOS (CON DETALLES) ===");
            List<Dispositivo> todos = servicio.listarTodo();
            for (Dispositivo d : todos) {
                logger.info("DISP: [ID: {}, Nombre: {}, Tipo: {}, Activo: {}] | DETALLE: [Consumo: {}W, Firmware: {}]",
                        d.getId(), d.getNombre(), d.getTipo(), d.isActivo(), 
                        d.getDetalle().getConsumo(), d.getDetalle().getVersion());
            }

            // --- 5. BÚSQUEDA POR ID ESPECÍFICO (SQL) ---
            logger.info("\n=== BÚSQUEDA POR ID (D01) ===");
            Dispositivo buscado = servicio.buscarPorId("D01");
            if (buscado != null) {
                logger.info("Encontrado: {} instalado el {}.", buscado.getNombre(), buscado.getFecha());
            }

            // --- 6. TOP 3 RANKING DE CONSUMO ---
            logger.info("\n=== RANKING TOP 3 DISPOSITIVOS POR CONSUMO ===");
            List<Dispositivo> top = servicio.obtenerTop3Consumo();
            int puesto = 1;
            for (Dispositivo d : top) {
                logger.info("Puesto #{}: {} con {} W/h", puesto, d.getNombre(), d.getDetalle().getConsumo());
                puesto++;
            }

            // --- 7. BÚSQUEDA AVANZADA (NOMBRE Y TIPO) ---
            logger.info("\n=== BÚSQUEDA FILTRADA (Nombre contiene 'Camara' y Tipo 'CAMARA') ===");
            List<Dispositivo> filtrados = servicio.buscarAvanzado("Camara", Tipo.CAMARA);
            for (Dispositivo f : filtrados) {
                logger.info("Filtro: {} - Versión: {}", f.getNombre(), f.getDetalle().getVersion());
            }

            // --- 8. ACTUALIZACIÓN (UPDATE) ---
            logger.info("\n=== ACTUALIZACIÓN DE FIRMWARE ===");
            servicio.actualizarFirmware("D01", "v2.0_PRO");
            logger.info("Actualización de firmware para D01 solicitada.");

            // --- 9. BORRADO (DELETE) ---
            logger.info("\n=== ELIMINACIÓN DE DISPOSITIVO ===");
            servicio.eliminarDispositivo("D03");
            logger.info("Dispositivo D03 eliminado.");

            // --- 10. CONTROL DE EXCEPCIONES: ALTA DUPLICADA ---
            logger.info("\n=== PRUEBA DE CONTROL DE EXCEPCIÓN: ID DUPLICADO ===");
            try {
                Dispositivo duplicado = new Dispositivo("D01", "Repetido", Tipo.LUZ, LocalDate.now(), true);
                duplicado.setDetalle(new Detalle(0, "v0"));
                servicio.crearDispositivo(duplicado);
            } catch (AppException e) {
                logger.info("Control de excepción exitoso: " + e.getMessage());
            }

        } catch (AppException e) {
            logger.fatal("¡ERROR FATAL EN EL SISTEMA! Fallo en la lógica o en la base de datos.");
            logger.fatal("Mensaje: " + e.getMessage());
        } finally {
            logger.info("\n--- SIMULACIÓN FINALIZADA ---");
        }
    }
}