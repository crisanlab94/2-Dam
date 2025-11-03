package padre;

import java.io.*;
import java.util.*;

public class LanzadorContadoresPalabras {

    //  Rutas base
    private static final String rutaResources = "src/main/resources/";
    private static final String directorioGenerarClasses = "target";
    private static final String rutaSource = "src/main/java/";

    public static void main(String[] args) {
        // Palabras a buscar
        String[] palabras = {"servicio", "hilo", "proceso", "multihilo", "concurrencia"};

        // Fichero donde se busca
        String ficheroEntrada = "texto.txt"; // debe existir en resources

        LanzadorContadoresPalabras lanzador = new LanzadorContadoresPalabras();

        // Compilamos el hijo
        lanzador.compilaClase("hijo/ContadorPalabras.java");

        // Ejecutamos los procesos
        lanzador.ejecutaProcesosPalabras(palabras, ficheroEntrada);
    }

    // ==========================================================
    // MÉTODO 1 → COMPILAR EL HIJO
    // ==========================================================
    public void compilaClase(String rutaClaseHijo) {
        String[] comando = {"javac", "-d", directorioGenerarClasses, rutaSource + rutaClaseHijo};

        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            pb.inheritIO();
            int exit = pb.start().waitFor();

            if (exit == 0)
                System.out.println(" Compilación correcta de " + rutaClaseHijo);
            else
                System.err.println(" Error compilando " + rutaClaseHijo);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ==========================================================
    // MÉTODO 2 → LANZAR PROCESOS HIJOS
    // ==========================================================
    public void ejecutaProcesosPalabras(String[] palabras, String ficheroEntrada) {
        List<Process> procesos = new ArrayList<>();

        try {
            for (String palabra : palabras) {
                String salida = palabra + ".txt";

                // Comando: java -cp target/classes hijo.ContadorPalabras texto.txt servicio servicio.txt
                String[] comando = {
                    "java", "-cp", "target/classes",
                    "hijo.ContadorPalabras",
                    rutaResources + ficheroEntrada,
                    palabra,
                    rutaResources + salida
                };

                ProcessBuilder pb = new ProcessBuilder(comando);
                pb.redirectErrorStream(true);
                pb.inheritIO();

                Process proceso = pb.start();
                procesos.add(proceso);
                System.out.println(" Lanzado proceso para palabra: " + palabra);
            }

            // Esperamos a que terminen
            for (int i = 0; i < procesos.size(); i++) {
                int exit = procesos.get(i).waitFor();
                System.out.println(" Proceso hijo (" + palabras[i] + ") terminado con código: " + exit);
            }

            // Leer resultados
            mostrarResultados(palabras);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ==========================================================
    // MÉTODO 3 → LEER LOS FICHEROS GENERADOS Y MOSTRAR RESULTADOS
    // ==========================================================
    public void mostrarResultados(String[] palabras) {
        int total = 0;
        System.out.println("\n RESULTADOS GENERALES:");

        for (String palabra : palabras) {
            String ruta = rutaResources + palabra + ".txt";
            int conteo = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                String linea = br.readLine();
                if (linea != null && !linea.isEmpty()) {
                    conteo = Integer.parseInt(linea.trim());
                }
                System.out.println("   La palabra '" + palabra + "' aparece " + conteo + " veces.");
                total += conteo;

            } catch (FileNotFoundException e) {
                System.err.println("⚠️ Fichero no encontrado: " + ruta);
            } catch (IOException e) {
                System.err.println("⚠️ Error leyendo fichero: " + ruta);
            }
        }

        System.out.println("\nTotal de apariciones de todas las palabras: " + total);
    }
}
