package repaso.accesoADatos.controlador;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repaso.accesoADatos.modelo.Pelicula;
import repaso.accesoADatos.servicio.PeliculaServicio;
import repaso.accesoADatos.utiles.PeliculasDOMXml;

public class GestionaPelicula {

    public final static String rutaResources = "src/main/resources"; 
    // Ruta donde se encuentran los recursos del proyecto, como el XML de películas

    private static final Logger logger = LogManager.getLogger(GestionaPelicula.class); 
    // Logger para registrar información, advertencias y errores durante la ejecución

    public static void main(String[] args) {
        // Instancia del objeto que lee y escribe películas en XML
        PeliculasDOMXml pelis = new PeliculasDOMXml();
        
        // Instancia del servicio que aplica las reglas de negocio a las películas
        PeliculaServicio servicio = new PeliculaServicio();

        try {
            // Lee la lista de películas desde el archivo XML
            List<Pelicula> listaPelis = pelis.leerPeliculasDesdeXML("peliculas.xml");

            // Recorre todas las películas leídas
            for (Pelicula pelicula : listaPelis) {
                try {
                    // Aplica las reglas de negocio al objeto película
                    servicio.aplicarReglasNegocio(pelicula);

                    // Muestra la información de la película por consola si es válida
                    System.out.println(pelicula);

                } catch (Exception e) {
                    // Si la película no cumple las reglas de negocio, se registra una advertencia
                    logger.warn("Película no válida: " + pelicula.getTitulo() + " - " + e.getMessage());
                }
            }

        } catch (Exception e) {
            // Si ocurre cualquier error al leer el XML, se registra como error
            logger.error("Error leyendo las películas desde XML", e);
        }
    }
}
