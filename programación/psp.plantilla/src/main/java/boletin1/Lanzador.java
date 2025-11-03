package boletin1;

import java.io.IOException;

/*
 * ENUNCIADO:
 * Clase Lanzador
 * 
 * 1️⃣ Genera el .jar de CalculaMultiplicacion
 * 2️⃣ Tiene un método lanzaMultiplicador que usa ProcessBuilder para ejecutar el .jar
 * 3️⃣ Ejecuta dos multiplicaciones: 1-4 y 5-9
 */

public class Lanzador {

    // ===============================
    // RUTAS PRINCIPALES
    // ===============================
    private static final String rutaSource = "src/main/java/";
    private static final String directorioGenerarClasses = "target/classes";
    private static final String rutaJar = "target/CalculaMultiplicacion.jar";

    public static void main(String[] args) {
        Lanzador lanzador = new Lanzador();

        try {
            // 1️⃣ Compilar clase hija
            lanzador.compilaClase("boletin1/CalculaMultiplicacion.java");

            // 2️⃣ Generar el JAR
            lanzador.generaJar();

            // 3️⃣ Ejecutar procesos multiplicador
            lanzador.lanzaMultiplicador(1, 4);
            lanzador.lanzaMultiplicador(5, 9);

            System.out.println("\n✅ Ejecución completada correctamente.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // ======================================================
    // MÉTODO COMPILA
    // ======================================================
    public void compilaClase(String rutaClaseHijo) throws IOException, InterruptedException {
        String[] comando = {
                "javac",
                "--release", "17", //obliga a usar la version 17 que es la mia
                "-d", directorioGenerarClasses,
                rutaSource + rutaClaseHijo
        };

        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.inheritIO();
        Process proceso = pb.start();
        int exitCode = proceso.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException("Error compilando la clase hija.");
        }

        System.out.println("✅ Clase hija compilada correctamente.");
    }

    // ======================================================
    // MÉTODO PARA GENERAR JAR
    // ======================================================
    public void generaJar() throws IOException, InterruptedException {
        // Crear manifiesto temporal
        java.nio.file.Path manifestPath = java.nio.file.Paths.get("MANIFEST.MF");
        try (java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(manifestPath)) {
            writer.write("Main-Class: boletin1.CalculaMultiplicacion\n");
        }

        String[] comandoJar = {
                "jar", "cvfm",
                rutaJar,
                "MANIFEST.MF",
                "-C", directorioGenerarClasses, "."
        };

        ProcessBuilder pb = new ProcessBuilder(comandoJar);
        pb.inheritIO();
        Process procesoJar = pb.start();
        int exitCode = procesoJar.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException("Error generando el JAR.");
        }

        java.nio.file.Files.deleteIfExists(manifestPath);

        System.out.println("✅ Archivo JAR generado con éxito: " + rutaJar);
    }

    // ======================================================
    // MÉTODO PARA EJECUTAR EL JAR CON ARGUMENTOS
    // ======================================================
    public void lanzaMultiplicador(int inicio, int fin) throws IOException, InterruptedException {
        System.out.println("\nEjecutando multiplicación de " + inicio + " a " + fin + "...");

        String javaExe = "C:\\Program Files\\Java\\jdk-17\\bin\\java.exe"; // ruta a JDK 17
        String[] comando = {
                //"java",
        		javaExe,
                "-jar",
                rutaJar,
                String.valueOf(inicio),
                String.valueOf(fin)
        };

        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectErrorStream(true);
        pb.inheritIO();

        Process proceso = pb.start();
        int exitCode = proceso.waitFor();

        System.out.println("Proceso hijo finalizado con código: " + exitCode);
       

    }
    
}
