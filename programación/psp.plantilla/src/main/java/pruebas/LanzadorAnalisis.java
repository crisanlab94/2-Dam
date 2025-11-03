package pruebas;

import java.io.*;

/**
 * Clase LanzadorAnalisis
 * ----------------------
 * Programa que:
 *  1️⃣ Compila la clase AnalizadorEstadistico.java
 *  2️⃣ La ejecuta con los parámetros necesarios.
 *
 * Estructura esperada:
 *   src/main/java/pruebas/LanzadorAnalisis.java
 *   src/main/java/pruebas/AnalizadorEstadistico.java
 *   src/main/resources/numeros.txt
 */
public class LanzadorAnalisis {

    // 📂 Rutas base (según tu estructura)
    private static final String RUTA_SOURCE = "src/main/java/";
    private static final String RUTA_RESOURCES = "src/main/resources/";
    private static final String DIRECTORIO_GENERAR_CLASSES = "target/classes/";

    public static void main(String[] args) {

        // Crear objeto lanzador
        LanzadorAnalisis lanzador = new LanzadorAnalisis();

        // Ficheros de trabajo
        String ficheroEntrada = "numeros.txt";
        String ficheroSalida = "resultado.txt";

        // 1️⃣ Compilar la clase AnalizadorEstadistico
        lanzador.compilaClase("pruebas/AnalizadorEstadistico.java");

        // 2️⃣ Ejecutar la clase AnalizadorEstadistico
        lanzador.ejecuta(ficheroEntrada, ficheroSalida);
    }

    // Método que compila una clase Java con javac
    public void compilaClase(String rutaRelativa) {
    	//--release,17 para forzar
        String[] comando = {"javac",  "--release", "17", "-d", DIRECTORIO_GENERAR_CLASSES, RUTA_SOURCE + rutaRelativa};
        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            pb.inheritIO(); // Muestra errores/salida en la consola del padre
            int exit = pb.start().waitFor();

            if (exit == 0)
                System.out.println("✅ Compilación correcta de " + rutaRelativa);
            else
                System.err.println("❌ Error compilando " + rutaRelativa);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método que ejecuta la clase AnalizadorEstadistico
    public void ejecuta(String ficheroEntrada, String ficheroSalida) {
       /*Metodo que hay que usa String[] comando = {
                "java", "-cp", "target/classes",
                "pruebas.AnalizadorEstadistico",
                RUTA_RESOURCES + ficheroEntrada,
                RUTA_RESOURCES + ficheroSalida
        };*/
    	
    	//Para forzar
    	
    	 String[] comando = {
    		        "C:\\Program Files\\Java\\jdk-17\\bin\\java",  // 👈 usa el java de tu JDK 17
    		        "-cp", "target/classes",
    		        "pruebas.AnalizadorEstadistico",
    		        RUTA_RESOURCES + ficheroEntrada,
    		        RUTA_RESOURCES + ficheroSalida
    		    };
    	
        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            pb.inheritIO(); // Muestra la salida del proceso hijo
            int exit = pb.start().waitFor();

            if (exit == 0)
                System.out.println("✅ Ejecución correcta de pruebas.AnalizadorEstadistico");
            else
                System.err.println("❌ Error en la ejecución del análisis estadístico");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
