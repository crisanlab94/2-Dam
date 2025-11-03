package hijo;

import java.io.*;

public class HijoUniversal {
    public static void main(String[] args) {

        try {
            // ===============================
            // 1️⃣ GESTIÓN DE ARGUMENTOS
            // ===============================
            if (args.length == 0) {
                System.err.println("No se ha recibido ningún argumento");
                System.exit(1);
            }

            String argumento = args[0];
            boolean esNumero = argumento.matches("\\d+");
            int valor = 0;

            if (esNumero) valor = Integer.parseInt(argumento);

            // ===============================
            // CASO: ARGUMENTO NUMÉRICO
            // ===============================
            if (esNumero) {
                int resultado = valor;
                System.out.println("Resultado numérico: " + resultado);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/resultados_int.txt", true))) {
                    bw.write("Número: " + valor + " → Doble: " + resultado);
                    bw.newLine();
                } catch (IOException e) {
                    System.err.println("Error escribiendo fichero de enteros: " + e.getMessage());
                    System.exit(1);
                }

                leerFichero("src/main/resources/resultados_int.txt");
            }

            // ===============================
            // CASO: ARGUMENTO STRING (provincia)
            // ===============================
            else {
                String provincia = argumento;
                String ficheroEntrada = "src/main/resources/pedidos.txt";
                String ficheroSalida = "src/main/resources/" + provincia + ".txt";
                int contador = 0;

                File fichero = new File(ficheroEntrada);
                if (!fichero.exists()) {
                    System.err.println("❌No se encontró el fichero pedidos.txt");
                    System.exit(1);
                }

                try (BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada));
                     BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {

                    String linea;
                    while ((linea = br.readLine()) != null) {
                        if (linea.startsWith(provincia + ";")) {
                            bw.write(linea);
                            bw.newLine();
                            contador++;
                        }
                    }

                } catch (IOException e) {
                    System.err.println("Error procesando provincia " + provincia + ": " + e.getMessage());
                    System.exit(1);
                }

                System.out.println(provincia + " : " + contador);

                // Muestra el contenido del fichero recién creado
                leerFichero(ficheroSalida);
            }

            // Si todo fue bien, sale con código 0
            System.exit(0);

        } catch (Exception e) {
            System.err.println("Excepción no controlada en HijoUniversal: " + e.getMessage());
            System.exit(1);
        }
    }

    // ====================================================
    // MÉTODO GENÉRICO PARA LEER CUALQUIER FICHERO
    // ====================================================
    private static void leerFichero(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            System.out.println("Contenido de " + ruta + ":");
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("   → " + linea);
            }
        } catch (IOException e) {
            System.err.println("No se pudo leer el fichero " + ruta + ": " + e.getMessage());
            System.exit(1);
        }
    }
}
