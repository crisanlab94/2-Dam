package padre;

import java.io.*;
import java.util.*;

/**
 * Clase padre que lanza varios procesos hijos (AnalizadorTemperaturas)
 * para analizar un fichero de temperaturas con distintos umbrales.
 *
 * Contiene:
 *  - Método compilaClase()
 *  - Método ejecutaProcesosTemperaturas()
 *  - Método mostrarResultados()
 */
public class LanzadorAnalisisTemperaturas {

    // === RUTAS BASE (se usan SIEMPRE en todos los ejercicios PSP) ===
    private static final String rutaResources = "src/main/resources/";   // Donde están los ficheros .txt
    private static final String directorioGenerarClasses = "target";     // Donde se guardan los .class
    private static final String rutaSource = "src/main/java/";           // Donde están los .java

    public static void main(String[] args) {
        int[] umbrales = {10, 20, 25, 30, 35};
        String fichero = "datos.txt";

        LanzadorAnalisisTemperaturas lanzador = new LanzadorAnalisisTemperaturas();

        // Compilamos la clase hijo
        lanzador.compilaClase("hijo/AnalizadorTemperaturas.java");

        // Ejecutamos los procesos hijos
        lanzador.ejecutaProcesosTemperaturas(umbrales, fichero);
    }

    // ===============================================================
    // MÉTODO 1 → COMPILA LA CLASE HIJO
    // ===============================================================
    public void compilaClase(String rutaClaseHijo) {
        String[] comando = {"javac", "-d", directorioGenerarClasses, rutaSource + rutaClaseHijo};

        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            pb.inheritIO();
            int exitCode = pb.start().waitFor();

            if (exitCode == 0) {
                System.out.println("Compilación correcta de " + rutaClaseHijo);
            } else {
                System.err.println(" Error al compilar " + rutaClaseHijo);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ===============================================================
    // MÉTODO 2 → LANZA UN PROCESO HIJO POR CADA UMBRAL
    // ===============================================================
    public void ejecutaProcesosTemperaturas(int[] umbrales, String fichero) {
        List<Process> procesos = new ArrayList<>();

        try {
            for (int umbral : umbrales) {
                String nombreSalida = umbral + ".txt";

                // comando para ejecutar: java -cp target/classes hijo.AnalizadorTemperaturas src/main/resources/datos.txt 25 src/main/resources/25.txt
                String[] comando = {
                    "java",
                    "-cp", "target/classes",
                    "hijo.AnalizadorTemperaturas",
                    rutaResources + fichero,
                    String.valueOf(umbral),
                    rutaResources + nombreSalida
                };

                ProcessBuilder pb = new ProcessBuilder(comando);
                pb.redirectErrorStream(true);
                pb.inheritIO();

                Process proceso = pb.start();
                procesos.add(proceso);
                System.out.println("Lanzado proceso para umbral " + umbral);
            }

            // Esperamos que todos terminen
            for (int i = 0; i < procesos.size(); i++) {
                int exit = procesos.get(i).waitFor();
                System.out.println("Proceso hijo (" + umbrales[i] + ") terminado con código: " + exit);
            }

            // Una vez todos han terminado, mostramos los resultados
            mostrarResultados(umbrales);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ===============================================================
    // MÉTODO 3 → LEE LOS FICHEROS GENERADOS (10.txt, 20.txt, etc.)
    // ===============================================================
    public void mostrarResultados(int[] umbrales) {
        System.out.println("\n Resultados finales:");
        int totalDias = 0;

        for (int umbral : umbrales) {
            String nombreFichero = rutaResources + umbral + ".txt";
            int dias = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
                String linea = br.readLine();
                if (linea != null && !linea.isEmpty()) {
                    dias = Integer.parseInt(linea.trim());
                }
                System.out.println("Umbral " + umbral + " → " + dias + " días");
                totalDias += dias;

            } catch (FileNotFoundException e) {
                System.err.println(" Fichero no encontrado: " + nombreFichero);
            } catch (IOException e) {
                System.err.println(" Error leyendo " + nombreFichero);
            }
        }

        System.out.println("\n📈 Total de días contados en todos los umbrales: " + totalDias);
    }
}
