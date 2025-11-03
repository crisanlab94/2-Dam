package pruebas;

import java.io.*;

/**
 * Clase LanzadorProductos
 * ------------------------
 * Compila y ejecuta la clase hija AnalizadorProductos.
 * Propaga los errores del proceso hijo (exit codes).
 */
public class LanzadorProductos {

    private static final String RUTA_SOURCE = "src/main/java/";
    private static final String RUTA_RESOURCES = "src/main/resources/";
    private static final String DIRECTORIO_GENERAR_CLASSES = "target/classes/";

    public static void main(String[] args) {

        LanzadorProductos lanzador = new LanzadorProductos();
        //Lo comento para probar el error en el padre
        //String ficheroEntrada = "productos.txt";
        //Forzar error 
        String ficheroEntrada="cosas.txt";
        String ficheroSalida = "resultadoProductos.txt";
        String modo = "ordenar"; // o "no_ordenar"

        //  Compilar clase hija
        lanzador.compilaClase("pruebas/AnalizadorProductos.java");

        // Ejecutar hija y propagar errores si los hay
        lanzador.ejecuta(ficheroEntrada, ficheroSalida, modo);
    }

    // Compila el fichero .java
    public void compilaClase(String rutaRelativa) {
        String[] comando = {
            "javac", "--release", "17",
            "-d", DIRECTORIO_GENERAR_CLASSES,
            RUTA_SOURCE + rutaRelativa
        };
        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            pb.inheritIO();
            int exit = pb.start().waitFor();

            if (exit == 0) {
                System.out.println("Compilación correcta de " + rutaRelativa);
            } else {
                System.err.println("Error compilando " + rutaRelativa);
                System.exit(exit); // Propaga el error al sistema
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al compilar: " + e.getMessage());
            System.exit(1);
        }
    }

    // Ejecuta el programa hijo y propaga su estado de salida
    public void ejecuta(String ficheroEntrada, String ficheroSalida, String modo) {
      //Para que funcione en casa
    	String javaHome = System.getenv("JAVA_HOME");
        String javaCmd = javaHome + "\\bin\\java"; // usa el Java 17 real

        String[] comando = {
            javaCmd, "-cp", "target/classes",
            "pruebas.AnalizadorProductos",
            RUTA_RESOURCES + ficheroEntrada,
            RUTA_RESOURCES + ficheroSalida,
            modo
        };
       
    	
    	/* El que vale
        *  String[] comando = {
        
            "java", "-cp", "target/classes",
            "pruebas.AnalizadorProductos",
            RUTA_RESOURCES + ficheroEntrada,
            RUTA_RESOURCES + ficheroSalida,
            modo
        };*/

        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            pb.inheritIO(); // muestra la salida del hijo en la consola del padre
            Process proceso = pb.start();

            int exit = proceso.waitFor(); // código de salida del hijo

            if (exit == 0) {
                System.out.println("Ejecución correcta de pruebas.AnalizadorProductos");
            } else {
                System.err.println("Padre:Propaga error hijo");
                System.exit(exit); // ⛔ Propaga el error del hijo al sistema
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error ejecutando el análisis: " + e.getMessage());
            System.exit(1);
        }
    }
}
