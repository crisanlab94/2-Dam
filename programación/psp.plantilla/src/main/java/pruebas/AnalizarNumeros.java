package pruebas;

import java.io.*;
import java.util.*;

/**
 * Clase AnalizadorNumeros
 * ------------------------
 * Lee un fichero con números (uno por línea) y realiza:
 *  - Orden ascendente
 *  - Orden descendente
 *  - Suma de pares e impares
 *  - Conteo total
 *  - Filtrado de números mayores que un umbral
 * 
 * Parámetros:
 *   args[0] → ruta del fichero de entrada
 *   args[1] → valor umbral (entero)
 *   args[2] → ruta del fichero de salida
 */
public class AnalizarNumeros {

    public static void main(String[] args) {

        if (args.length < 3) {
            System.err.println("Uso: java pruebas.AnalizadorNumeros <ficheroEntrada> <umbral> <ficheroSalida>");
            System.exit(1);
        }

        String rutaEntrada = args[0];
        int umbral = Integer.parseInt(args[1]);
        String rutaSalida = args[2];

        List<Integer> numeros = new ArrayList<>();

        // ======== Leer los números ========
        try (BufferedReader br = new BufferedReader(new FileReader(rutaEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    numeros.add(Integer.parseInt(linea));
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el fichero: " + e.getMessage());
            System.exit(1);
        }

        if (numeros.isEmpty()) {
            System.err.println("El fichero está vacío o no contiene números válidos.");
            System.exit(1);
        }

        // ======== Procesar datos ========
        List<Integer> ascendente = new ArrayList<>(numeros);
        List<Integer> descendente = new ArrayList<>(numeros);
        Collections.sort(ascendente);
        Collections.sort(descendente, Collections.reverseOrder());

        int sumaPares = numeros.stream().filter(n -> n % 2 == 0).mapToInt(Integer::intValue).sum();
        int sumaImpares = numeros.stream().filter(n -> n % 2 != 0).mapToInt(Integer::intValue).sum();
        long total = numeros.size();
       
        List<Integer> mayoresQueUmbral = new ArrayList<>();

        for (int n : numeros) {
            if (n % 2 == 0) {
                sumaPares += n;
            } else {
                sumaImpares += n;
            }
            if (n > umbral) {
                mayoresQueUmbral.add(n);
            }
        }


        // ======== Escribir resultados ========
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {
            bw.write("Orden ascendente: " + ascendente);
            bw.newLine();
            bw.write("Orden descendente: " + descendente);
            bw.newLine();
            bw.write("Suma de pares: " + sumaPares);
            bw.newLine();
            bw.write("Suma de impares: " + sumaImpares);
            bw.newLine();
            bw.write("Total de números: " + total);
            bw.newLine();
            bw.write("Mayores que " + umbral + ": " + mayoresQueUmbral);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error escribiendo el fichero de salida: " + e.getMessage());
            System.exit(1);
        }

        // ======== Mostrar resumen por consola ========
        System.out.println("📊 Resultados del análisis:");
        System.out.println("Ascendente: " + ascendente);
        System.out.println("Descendente: " + descendente);
        System.out.println("Suma pares: " + sumaPares);
        System.out.println("Suma impares: " + sumaImpares);
        System.out.println("Total: " + total);
        System.out.println("Mayores que " + umbral + ": " + mayoresQueUmbral);

        System.exit(0);
    }
}
