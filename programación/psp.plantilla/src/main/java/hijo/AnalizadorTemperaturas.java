package hijo;

import java.io.*;

/**
 * Clase hijo que analiza un fichero de temperaturas y cuenta
 * cuántos días tienen una temperatura >= al umbral indicado.
 *
 * Recibe 3 argumentos:
 *   1️⃣ Ruta del fichero de datos (ej: datos.txt)
 *   2️⃣ Umbral (por ejemplo: 25)
 *   3️⃣ Fichero de salida (ej: 25.txt)
 *
 * Ejemplo de ejecución manual:
 * java hijo.AnalizadorTemperaturas src/main/resources/datos.txt 25 src/main/resources/25.txt
 */
public class AnalizadorTemperaturas {

    public static void main(String[] args) {
        // ---------- 1) Validación de argumentos ----------
        if (args.length < 3) {
            System.err.println("Uso: AnalizadorTemperaturas <rutaFichero> <umbral> <ficheroSalida>");
            System.exit(2);
        }

        String rutaFichero = args[0];
        String umbralStr = args[1];
        String ficheroSalida = args[2];

        // ---------- 2) Determinar/validar el umbral ----------
        int umbral;
        if (esNumeroEntero(umbralStr)) {
            umbral = Integer.parseInt(umbralStr);
        } else {
            System.err.println("Error: el umbral debe ser un número entero válido → " + umbralStr);
            System.exit(2);
            return;
        }

        // ---------- 3) Lectura del fichero y conteo ----------
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                try {
                    double temp = Double.parseDouble(linea);
                    if (temp >= umbral) contador++;
                } catch (NumberFormatException nf) {
                    // Ignoramos líneas no numéricas
                }
            }
        } catch (FileNotFoundException fnf) {
            System.err.println("Error: fichero no encontrado → " + rutaFichero);
            System.exit(1);
        } catch (IOException ioe) {
            System.err.println("Error leyendo fichero: " + ioe.getMessage());
            System.exit(1);
        }

        // ---------- 4) Escribir resultado ----------
        // Solo escribimos el número de días (no texto)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {
            bw.write(String.valueOf(contador)); // SOLO el número
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error escribiendo resultado en " + ficheroSalida + ": " + e.getMessage());
            System.exit(1);
        }

        // Mostramos también por consola (opcional)
        System.out.println("Umbral " + umbral + " → " + contador + " días");

        System.exit(0);
    }

    // ---------- Método auxiliar ----------
    private static boolean esNumeroEntero(String s) {
        return s != null && s.matches("-?\\d+");
    }
}
