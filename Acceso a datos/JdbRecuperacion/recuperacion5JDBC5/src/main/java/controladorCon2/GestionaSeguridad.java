package controladorCon2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Astronauta;
import modelo.Seguridad;
import servicioCon2.ServicioSeguridad;
import util.MySqlConector;

public class GestionaSeguridad {
    private static final Logger logger = LogManager.getLogger(GestionaSeguridad.class);

    public static void main(String[] args) {
        
        try {
            // 1. INICIALIZACIÓN DE LA INFRAESTRUCTURA
            MySqlConector conector = new MySqlConector();
            ServicioSeguridad servicio = new ServicioSeguridad(conector);

            logger.info("=== AGENCIA ESPACIAL: MÓDULO DE SEGURIDAD INICIADO ===");

            // 2. CREACIÓN DEL OBJETO INTEGRAL (Astronauta + Seguridad)
            // Aquí cumplimos tu regla: el objeto nace con todos sus datos
            Seguridad credenciales = new Seguridad(8822, "NIVEL_ALFA");
            Astronauta nuevoAstro = new Astronauta(50, "Ellen Ripley", credenciales);

            // 3. PRUEBA DE ALTA (Transacción 1:1)
            logger.info("--- Prueba: Registro de nuevo personal con credenciales ---");
            servicio.registrarAstronauta(nuevoAstro);

            // 4. GENERACIÓN DE INFORME (Cruce de datos de repositorios)
            logger.info("--- Prueba: Generando informe completo de personal ---");
            servicio.mostrarExpedienteCompleto();

        } catch (AppException e) {
            // Errores controlados de nuestra lógica (nombre vacío, fallos de SQL, etc.)
            logger.error("ALERTA DE NEGOCIO: {}", e.getMessage());
        } catch (Exception e) {
            // Errores inesperados del sistema
            logger.error("FALLO CRÍTICO: ", e);
        }
    }
}