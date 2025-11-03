package boletin1;

/*
 * EJERCICIO 10: Ejecutar JAR
 * ------------------------------------------------------------
 * Desde Java, ejecuta un fichero .jar previamente generado.
 */

public class LanzadorJar {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "target/miPrograma.jar");
            pb.inheritIO();
            pb.start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

