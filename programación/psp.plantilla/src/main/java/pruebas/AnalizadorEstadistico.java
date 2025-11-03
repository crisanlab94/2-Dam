package pruebas;
	import java.io.*;
	import java.util.*;

	/**
	 * Programa: AnalizadorEstadistico
	 * --------------------------------
	 * Este programa lee un fichero de números (uno por línea),
	 * calcula estadísticas básicas (mínimo, máximo, media, mediana y desviación estándar)
	 * y guarda los resultados en un fichero de salida.
	 *
	 * Uso directo desde consola:
	 *   java psp.AnalizadorEstadistico <ficheroEntrada> <ficheroSalida>
	 *
	 * También puede ser lanzado desde otro programa (por ejemplo, LanzadorAnalisis).
	 */
	public class AnalizadorEstadistico {

	    public static void main(String[] args) {

	        // ==== 1️⃣ Validar los argumentos ====
	        if (args.length != 2) {
	            System.err.println("Uso: java psp.AnalizadorEstadistico <ficheroEntrada> <ficheroSalida>");
	            System.exit(1); // Código de salida 1 → error en parámetros
	        }

	        String ficheroEntrada = args[0];
	        String ficheroSalida = args[1];

	        // ==== 2️⃣ Leer los números del fichero ====
	        List<Double> numeros = leerNumeros(ficheroEntrada);

	        if (numeros.isEmpty()) {
	            System.err.println("El fichero no contiene números válidos o está vacío.");
	            System.exit(1);
	        }

	        // ==== 3️⃣ Calcular las estadísticas ====
	        double minimo = calcularMinimo(numeros);
	        double maximo = calcularMaximo(numeros);
	        double media = calcularMedia(numeros);
	        double mediana = calcularMediana(numeros);
	        double desviacion = calcularDesviacionEstandar(numeros, media);

	        // ==== 4️⃣ Escribir resultados en el fichero ====
	        escribirResultados(ficheroSalida, numeros.size(), minimo, maximo, media, mediana, desviacion);

	        // ==== 5️⃣ Mostrar resultado por consola (opcional) ====
	        System.out.println("===== RESULTADOS DEL ANÁLISIS =====");
	        System.out.println("Números analizados: " + numeros.size());
	        System.out.println("Mínimo: " + minimo);
	        System.out.println("Máximo: " + maximo);
	        System.out.println("Media: " + media);
	        System.out.println("Mediana: " + mediana);
	        System.out.println("Desviación estándar: " + desviacion);
	        System.out.println("===================================");
	        System.exit(0); // Código de salida 0 → ejecución correcta
	    }

	    // ============================================================
	    // 🔹 MÉTODOS AUXILIARES (modularidad)
	    // ============================================================

	    /**
	     * Lee un fichero línea por línea y convierte cada línea a double.
	     * Si una línea no es numérica, se ignora (mostrando aviso).
	     */
	    private static List<Double> leerNumeros(String fichero) {
	        List<Double> lista = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
	            String linea;

	            while ((linea = br.readLine()) != null) {
	                try {
	                    lista.add(Double.parseDouble(linea.trim()));
	                } catch (NumberFormatException e) {
	                    System.err.println("⚠️ Línea ignorada (no numérica): " + linea);
	                }
	            }

	        } catch (FileNotFoundException e) {
	            System.err.println("❌ Error: fichero no encontrado → " + fichero);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("❌ Error leyendo el fichero: " + e.getMessage());
	            System.exit(1);
	        }

	        return lista;
	    }

	    /** Calcula el mínimo usando Collections.min() */
	    private static double calcularMinimo(List<Double> lista) {
	        return Collections.min(lista);
	    }

	    /** Calcula el máximo usando Collections.max() */
	    private static double calcularMaximo(List<Double> lista) {
	        return Collections.max(lista);
	    }

	    /** Calcula la media aritmética */
	    private static double calcularMedia(List<Double> lista) {
	        double suma = 0;
	        for (double n : lista) suma += n;
	        return suma / lista.size();
	    }

	    /** Calcula la mediana (valor central o promedio de los dos centrales) */
	    private static double calcularMediana(List<Double> lista) {
	        List<Double> ordenada = new ArrayList<>(lista);
	        Collections.sort(ordenada);

	        int n = ordenada.size();
	        if (n % 2 == 0)
	            return (ordenada.get(n / 2 - 1) + ordenada.get(n / 2)) / 2.0;
	        else
	            return ordenada.get(n / 2);
	    }

	    /** Calcula la desviación estándar */
	    private static double calcularDesviacionEstandar(List<Double> lista, double media) {
	        double suma = 0;
	        for (double n : lista) {
	            suma += Math.pow(n - media, 2);
	        }
	        return Math.sqrt(suma / lista.size());
	    }

	    /** Escribe los resultados en un fichero de salida */
	    private static void escribirResultados(String ficheroSalida, int n, double min, double max, double media, double mediana, double desviacion) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {
	            bw.write("===== RESULTADOS DEL ANÁLISIS ESTADÍSTICO =====");
	            bw.newLine();
	            bw.write("Números analizados: " + n); bw.newLine();
	            bw.write("Mínimo: " + min); bw.newLine();
	            bw.write("Máximo: " + max); bw.newLine();
	            bw.write("Media: " + media); bw.newLine();
	            bw.write("Mediana: " + mediana); bw.newLine();
	            bw.write("Desviación estándar: " + desviacion); bw.newLine();
	            bw.write("===============================================");
	            bw.newLine();
	        } catch (IOException e) {
	            System.err.println("❌ Error escribiendo resultado en " + ficheroSalida + ": " + e.getMessage());
	            System.exit(1);
	        }
	    }
	}



