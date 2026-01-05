package tema1Ficheros;

import java.io.File;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Excepciones.Ejercicio1Exception;

public class Ejercicio1 {
    private static final Logger logger = LogManager.getLogger(Ejercicio1.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la ruta del directorio o fichero: ");
        String ruta = scanner.nextLine();

        Ejercicio1 programa = new Ejercicio1();
        try {
            programa.mostrarContenido(ruta);
        } catch (Ejercicio1Exception e) {
            logger.error("Excepción: " + e.getMessage());
        }

        scanner.close();
    }

    public void mostrarContenido(String ruta) throws Ejercicio1Exception {
        File rutaFicheros = new File(ruta);

        if (!rutaFicheros.exists()) {
            throw new Ejercicio1Exception("No existe la ruta del fichero.");
        }

        if (rutaFicheros.isFile()) {
            logger.info("Es un fichero: " + rutaFicheros.getName());
        } else if (rutaFicheros.isDirectory()) {
            logger.info("Es un directorio.");
            File[] archivos = rutaFicheros.listFiles();
            if (archivos != null) {
                logger.info("Total de elementos: " + archivos.length);
                for (File archivo : archivos) {
                    String tipo = archivo.isDirectory() ? "Directorio" : "Fichero";
                    logger.info("- " + archivo.getName() + " (" + tipo + ")");
                }
            } else {
                logger.warn("No se pudo acceder al contenido del directorio.");
            }
        }
    }
}