package pruebas;

import java.io.*;

/**
 * Clase LanzadorNumeros
 * ----------------------
 * Compila y ejecuta la clase AnalizadorNumeros.
 * 
 * Estructura esperada:
 *   src/main/java/pruebas/AnalizarNumeros.java
 *   src/main/resources/numeros.txt
 */
public class LanzadorNumeros {

    // 📂 Rutas base
    private static final String RUTA_SOURCE = "src/main/java/";
    private static final String RUTA_RESOURCES = "src/main/resources/";
    private static final String DIRECTORIO_GENERAR_CLASSES = "target/classes/";

    public static void main(String[] args) {

        LanzadorNumeros lanzador = new LanzadorNumeros();

        // Archivos de entrada/salida
        String ficheroEntrada = "numeros.txt";
        String ficheroSalida = "resultadoNumeros.txt";

        // Valor umbral (por ejemplo, 10)
        String umbral = "10";

        // 1️⃣ Compilar la clase hija
        lanzador.compilaClase("pruebas/AnalizarNumeros.java");

        // 2️⃣ Ejecutar con argumentos
        lanzador.ejecuta(ficheroEntrada, umbral, ficheroSalida);
    }

    // Compila una clase Java
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

            if (exit == 0)
                System.out.println("✅ Compilación correcta de " + rutaRelativa);
            else
                System.err.println("❌ Error compilando " + rutaRelativa);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Ejecuta la clase AnalizadorNumeros con los ficheros
    public void ejecuta(String ficheroEntrada, String umbral, String ficheroSalida) {
      //El que se usa para forzar
    	String javaHome = System.getenv("JAVA_HOME");
    	String javaCmd = javaHome + "\\bin\\java";

    	String[] comando = {
    	    javaCmd, "-cp", "target/classes",
    	    "pruebas.AnalizarNumeros",
    	    RUTA_RESOURCES + ficheroEntrada,umbral,
    	    RUTA_RESOURCES + ficheroSalida
    	};
    	
    	
    	//El que hay que usar
    	/* String[] comando = {
                "java", "-cp", "target/classes",
                "pruebas.AnalizarNumeros",
                RUTA_RESOURCES + ficheroEntrada,
                umbral,
                RUTA_RESOURCES + ficheroSalida
        };*/
        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            pb.inheritIO();
            int exit = pb.start().waitFor();

            if (exit == 0)
                System.out.println("✅ Ejecución correcta de pruebas.AnalizadorNumeros");
            else
                System.err.println("❌ Error en la ejecución del análisis de números");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
