package boletin1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;

/*
 * PLANTILLA: Trabajar con .JAR en Java
 * ====================================
 * Incluye:
 * 1Compilar clases
 * 2️Generar .jar
 * 3️ Ejecutar .jar con ProcessBuilder
 * 4️ Leer la salida de un proceso
 * 5️ Ejemplos de otras operaciones
 */

public class JarUtils {

    // ================================
    // RUTAS PRINCIPALES
    // ================================
    private static final String SOURCE_DIR = "src/main/java/";
    private static final String CLASSES_DIR = "target/classes";
    private static final String JAR_PATH = "target/MiPrograma.jar";

    // ================================
    // 1️⃣ Método para compilar una clase
    // ================================
    public static void compilaClase(String rutaClase) throws IOException, InterruptedException {
        String[] comando = {"javac", "-d", CLASSES_DIR, SOURCE_DIR + rutaClase};
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.inheritIO(); // para ver salida en consola
        Process p = pb.start();
        int exitCode = p.waitFor();
        if (exitCode != 0) throw new RuntimeException("Error compilando " + rutaClase);
        System.out.println(" Clase compilada: " + rutaClase);
    }

    // ================================
    // 2️ Método para generar un JAR
    // ================================
    public static void generaJar(String clasePrincipal) throws IOException, InterruptedException {
        Path manifestPath = Paths.get("MANIFEST.MF");
        try (BufferedWriter writer = Files.newBufferedWriter(manifestPath)) {
            writer.write("Main-Class: " + clasePrincipal + "\n");
        }

        String[] comandoJar = {"jar", "cvfm", JAR_PATH, "MANIFEST.MF", "-C", CLASSES_DIR, "."};
        ProcessBuilder pb = new ProcessBuilder(comandoJar);
        pb.inheritIO();
        Process p = pb.start();
        int exitCode = p.waitFor();
        if (exitCode != 0) throw new RuntimeException("Error generando el JAR");
        Files.deleteIfExists(manifestPath);

        System.out.println(" Archivo JAR generado: " + JAR_PATH);
    }

    // ================================
    // 3️ Método para ejecutar un JAR
    // ================================
    public static void ejecutaJar(String... args) throws IOException, InterruptedException {
        System.out.println("\nEjecutando JAR...");
        String javaExe = System.getProperty("java.home") + "/bin/java"; // ruta dinámica al JDK
        String[] comando = new String[args.length + 3];
        comando[0] = javaExe;
        comando[1] = "-jar";
        comando[2] = JAR_PATH;
        System.arraycopy(args, 0, comando, 3, args.length);

        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectErrorStream(true); // mezcla stdout y stderr
        Process proceso = pb.start();

        // Leer la salida
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        }

        int exitCode = proceso.waitFor();
        System.out.println("Proceso terminado con código: " + exitCode);
    }

    // ================================
    // 4️ Método para listar el contenido de un JAR
    // ================================
    public static void listarContenidoJar() throws IOException, InterruptedException {
        String[] comando = {"jar", "tf", JAR_PATH}; // "t" = list, "f" = file
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectErrorStream(true);
        Process proceso = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
            String linea;
            System.out.println("\nContenido del JAR:");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        }

        int exitCode = proceso.waitFor();
        System.out.println("Listado terminado con código: " + exitCode);
    }

    // ================================
    // 5️ Ejemplo de main para probar
    // ================================
    public static void main(String[] args) {
        try {
            // Compilar clase
            compilaClase("boletin1/CalculaMultiplicacion.java");

            // Generar JAR
            generaJar("boletin1.CalculaMultiplicacion");

            // Ejecutar JAR con argumentos
            ejecutaJar("1", "4");
            ejecutaJar("5", "9");

            // Listar contenido del JAR
            listarContenidoJar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
