package boletin1;

/*
 * EJERCICIO 9: Lanzador Python con redirección de IO
 * ------------------------------------------------------------
 * Modifica el programa anterior para usar redirectErrorStream(true) y inheritIO().
 * La salida del hijo se verá directamente en la consola del padre.
 */

public class LanzadorPythonRedirigido {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "src/main/resources/fichero.py");
            pb.redirectErrorStream(true);
            pb.inheritIO();
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

