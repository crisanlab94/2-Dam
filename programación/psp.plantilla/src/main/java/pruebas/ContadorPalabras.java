package pruebas;

import java.io.*;

public class ContadorPalabras {

    public static void main(String[] args) {
      
        String rutaFichero = args[0];     // fichero donde buscar
        String palabra = args[1];         // palabra a buscar
        String ficheroSalida = args[2];   // fichero donde escribir el resultado

        int contador = 0; // número de veces que aparece la palabra

        // ========== Leer el fichero y contar coincidencias ==========
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                // Pasamos todo a minúsculas para evitar problemas de mayúsculas/minúsculas
                String[] palabras = linea.toLowerCase().split("\\W+"); // divide por espacios y signos
                for (String p : palabras) {
                    if (p.equals(palabra.toLowerCase())) {
                        contador++;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            // Error 1 = el fichero no existe → se propaga al padre
            System.err.println("Error: fichero no encontrado → " + rutaFichero);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error leyendo el fichero: " + e.getMessage());
            System.exit(1);
        }

        // ========== Escribir el resultado en un fichero ==========
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {
            bw.write(String.valueOf(contador)); // guardamos solo el número
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error escribiendo resultado en " + ficheroSalida + ": " + e.getMessage());
            System.exit(1);
        }

        // También lo mostramos por consola (para el Apartado 3)
        System.out.println("La palabra '" + palabra + "' aparece " + contador + " veces.");

        // Salida correcta
        System.exit(0);
    }
}
