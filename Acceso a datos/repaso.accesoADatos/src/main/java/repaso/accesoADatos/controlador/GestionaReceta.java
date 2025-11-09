package repaso.accesoADatos.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import repaso.accesoADatos.modelo.Ingrediente;
import repaso.accesoADatos.modelo.Receta;
import repaso.accesoADatos.repositorio.RepositorioReceta;
import repaso.accesoADatos.utiles.RecetasDOM;

public class GestionaReceta {

    private static final Logger logger = LogManager.getLogger(GestionaReceta.class);

    public static void main(String[] args) {
        try {
            // Crear repositorio y DOM
            RepositorioReceta repositorio = new RepositorioReceta();
            RecetasDOM recetasDom = new RecetasDOM();

            // Crear lista de ingredientes para la receta
            List<Ingrediente> ingredientes = new ArrayList<>();
            ingredientes.add(new Ingrediente("Pollo", "750gr"));
            ingredientes.add(new Ingrediente("Especias", "una pizca"));

            // Crear objeto Receta
            Receta receta = new Receta(
                    "Pollo a ll chilindron",
                    ingredientes,
                    "Asar el pollo y echarle especias",
                    "30 min"
            );

            // Agregar la receta al repositorio
            repositorio.agregarReceta(receta);
            logger.info("Receta agregada al repositorio: {}", receta.getTitulo());

            // Generar XML desde la lista de recetas del repositorio
            recetasDom.escribirEnXML("RecetaDOM.xml", repositorio.leerLista());
            logger.info("Archivo RecetaDOM.xml generado correctamente en src/main/resources/");

        } catch (Exception e) {
            logger.error("Ocurrió un error: ", e);
        }
    }

}
