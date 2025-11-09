package accesoADatos.plantilla;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Modelo;
import Servicio;

/**
 * Controlador genérico
 * Gestiona la interacción con el usuario o la consola
 * Llama al servicio para aplicar reglas y mostrar resultados
 * Usa logger para registrar información y errores
 */
public class Controlador {

    // Logger para registrar info, advertencias y errores
    private static final Logger logger = LogManager.getLogger(Controlador.class);

    public static void main(String[] args) {
        Servicio servicio = new Servicio();

        try {
            // Ejemplo de búsqueda por atributo1
            List<Modelo> lista = servicio.buscarPorAtributo1("valorEjemplo");
            for (Modelo obj : lista) {
                // Registramos la información en el logger
                logger.info(obj);
            }

            // Ejemplo de agregar objeto
            Modelo nueva = new Modelo("titulo", 2025, "director", List.of("actor1","actor2"));
            servicio.agregarEntidad(nueva);
            logger.info("Objeto agregado correctamente: " + nueva);

        } catch (Exception e) {
            // Registramos el error con su mensaje y stack trace
            logger.error("Error al procesar la operación", e);
        }
    }
}
