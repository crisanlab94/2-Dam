package controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.*;
import servicio.ServicioMedico;

public class GestionaHospital {
    private static final Logger logger = LogManager.getLogger(GestionaHospital.class);

    public static void main(String[] args) {
        try {
            ServicioMedico servicio = new ServicioMedico();
            logger.info("--- 1. SISTEMA HOSPITALARIO INICIADO ---");

            // ALTA DE PRUEBA
            Medico mNuevo = new Medico(0, "Dr. Prueba", Especialidad.CIRUGIA, "prueba@h.com", LocalDate.now(), true);
            mNuevo.setConsultorio(new Consultorio(5, 900.0, LocalDateTime.now()));
            servicio.darAlta(mNuevo);

            // VOLCADO TOTAL DE INFORMACIÓN
            logger.info("\n=== 2. FICHA COMPLETA DE TODOS LOS MÉDICOS (INFO HIDRATADA) ===");
            List<Medico> todos = servicio.listarTodo();
            for (Medico m : todos) {
                logger.info("------------------------------------------------------------------");
                logger.info("MÉDICO: [ID: {}] | Nombre: {} | Especialidad: {} | Activo: {}", 
                    m.getId(), m.getNombre(), m.getEspecialidad(), m.isEstaActivo());
                logger.info("CONTACTO: Email: {} | Fecha Alta: {}", m.getEmail(), m.getFechaAlta());
                logger.info("CONSULTORIO: Planta: {} | Coste Mant.: {}€ | Última Insp.: {}", 
                    m.getConsultorio().getPlanta(), 
                    m.getConsultorio().getCosteMantenimiento(), 
                    m.getConsultorio().getUltimaInspeccion());
            }

            // TOP 3 RANKING
            logger.info("\n=== 3. RANKING TOP 3 CONSULTORIOS POR COSTE ===");
            List<Medico> top = servicio.obtenerTop3Costosos();
            for (int i = 0; i < top.size(); i++) {
                Medico tm = top.get(i);
                logger.info("#{} -> {} (Coste: {}€ en Planta {})", 
                    (i+1), tm.getNombre(), tm.getConsultorio().getCosteMantenimiento(), tm.getConsultorio().getPlanta());
            }

            // INTENTO DE ERROR (ALTA DUPLICADA POR EMAIL)
            logger.info("\n=== 4. PRUEBA CONTROL DE EXCEPCIÓN (EMAIL REPETIDO) ===");
            try {
                Medico mError = new Medico(0, "Fallo", Especialidad.PEDIATRIA, "holiholi@hospital.com", LocalDate.now(), true);
                mError.setConsultorio(new Consultorio(1, 0, LocalDateTime.now()));
                servicio.darAlta(mError);
            } catch (AppException e) {
                logger.error("CAPTURA EXITOSA: " + e.getMessage());
            }

            // MODIFICACIÓN Y BORRADO
            servicio.modificarCoste(1, 1200.50);
            servicio.eliminar(4); // Borramos a Cuddy
            logger.info("\n--- 5. SIMULACIÓN FINALIZADA CON ÉXITO ---");

        } catch (AppException e) {
            logger.fatal("¡ERROR SISTEMA!: " + e.getMessage());
        }
    }
}