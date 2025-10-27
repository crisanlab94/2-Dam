package Palabrillas;

import java.io.IOException;

public class LanzadorContadorPalabras {
    private static final String rutamain = "src/main/java/";
    private static final String rutaResources = "src/main/resources/"; 
    private final static String directorioGenerarClasses = "target";
    private final static String rutaSource = "src/main/java/";

    public static void main(String[] args) {
        LanzadorContadorPalabras a = new LanzadorContadorPalabras();
        a.compilaClase("Palabrillas/ContarPalabras.java");
        a.ejecutaClase("Palabrillas.ContarPalabras", "fichero.txt","java");
        System.out.println(); 
    }

    public void ejecutaClase(String rutaFicheroJava, String nombreFichero, String palabraAbuscar) {
        // Ejecutar la clase compilada usando su nombre de clase, no la ruta al .java
        // doy dos argumentos: fichero.txt y es
        String[] comando = {
            "java",
            "-cp", directorioGenerarClasses,
            rutaFicheroJava,
            nombreFichero,
            palabraAbuscar
        };
        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.redirectErrorStream(true);
            pb.inheritIO();
            pb.start().waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void compilaClase(String ruta) {
        // Compilar el archivo fuente y generar el .class en el directorio target
        String[] comando = {
            "javac",
            "-d", directorioGenerarClasses,
            rutaSource + ruta 
        };
        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.redirectErrorStream(true);
            pb.inheritIO();
            pb.start().waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
