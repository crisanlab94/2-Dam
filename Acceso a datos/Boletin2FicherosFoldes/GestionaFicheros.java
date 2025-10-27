package Boletin2FicherosFoldes;

import java.io.File;
import java.util.Collection; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GestionaFicheros {
    private static final Logger logger = LogManager.getLogger(GestionaFicheros.class);

    public static void main(String[] args) {
        try {
            String escritorio = System.getProperty("user.home") + "/Desktop";
            File carpeta2 = new File(escritorio + "/prueba");
            File carpeta1 = new File(escritorio + "/prueba2");

            DiffFolder comparador = new DiffFolder(carpeta1, carpeta2);
            comparador.setFolders(carpeta1, carpeta2);

            Collection<ResultadoComparacion> resultados = comparador.compare();
            
            for (ResultadoComparacion resultadoComparacion : resultados) {
				logger.info(resultadoComparacion);
			}

        } catch (GestionFicherosException e) {
            logger.warn("Error al validar carpetas: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage(), e);
        }
    }
}
