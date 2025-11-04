package pruebas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GeneraDNIFichero {

    private static final String rutaResources = "src/main/resources/";

    // Método principal que se ejecuta como proceso hijo
    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Uso: java pruebas.GeneraDNIFichero <ficheroEntrada>");
            System.exit(1);
        }

        String ficheroEntrada = args[0];
        GeneraDNIFichero g = new GeneraDNIFichero();

        try {
            // Analiza el fichero y obtiene solo las líneas con DNIs válidos
            String contenido = g.analizaDNI(ficheroEntrada);

            // Escribe el contenido filtrado en DNIs.txt
            String ficheroSalida = rutaResources + "DNIs.txt";
            g.escribirDNI(contenido, ficheroSalida);

            // Calcula el número de DNIs y lo imprime (salida que leerá el padre)
            int totalDNI = g.devuelveNumDNI(ficheroSalida);
            System.out.println(totalDNI);

        } catch (IOException e) {
            System.err.println("Error al procesar el fichero: " + e.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }

    // Analiza el fichero de entrada y filtra las líneas que contienen un DNI válido
    public String analizaDNI(String rutaFichero) throws FileNotFoundException {
        File archivo = new File(rutaFichero);
        FileReader fichero = new FileReader(archivo);
        Scanner in = new Scanner(fichero);
        String contenido = "";

        while (in.hasNextLine()) {
            String linea = in.nextLine();
            // Obtenemos la primera parte de la línea antes de la coma
            String parte = linea.split(",")[0].trim();

            // Si es un DNI válido, agregamos la línea al contenido
            if (esDNIValido(parte)) {
                contenido += linea + "\n";
            }
        }

        in.close();
        try {
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contenido;
    }

    // Escribe el contenido filtrado en un fichero de salida
    public void escribirDNI(String contenido, String rutaFichero) {
        FileWriter ficheroSalida = null;
        try {
            ficheroSalida = new FileWriter(rutaFichero);
            // Encabezado opcional
            String encabezado = "DNI\n";
            ficheroSalida.write(encabezado);
            ficheroSalida.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        } finally {
            if (ficheroSalida != null) {
                try {
                    ficheroSalida.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Cuenta el número de DNIs procesados en el fichero de salida
    public int devuelveNumDNI(String ruta) {
        int total = 0;

        try (FileReader fichero = new FileReader(ruta);
             Scanner in = new Scanner(fichero)) {

            // Saltar la primera línea (encabezado)
            if (in.hasNextLine()) in.nextLine();

            // Contar las líneas restantes
            while (in.hasNextLine()) {
                String linea = in.nextLine();
                if (!linea.trim().isEmpty()) {
                    total++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return total;
    }

    // Comprueba si un código es un DNI válido
    // Formatos:
    // - 8 dígitos + letra final (ej: 12345678A)
    // - letra inicial + 8 dígitos (ej: X12345678)
    public boolean esDNIValido(String codigo) {
        boolean valido = false;
        int len = codigo.length();

        // Caso 1: 8 dígitos + letra final
        if (len == 9) {
            boolean correcto = true;
            for (int i = 0; i < 8; i++) {
                if (!Character.isDigit(codigo.charAt(i))) {
                    correcto = false;
                }
            }
            if (correcto && Character.isLetter(codigo.charAt(8))) {
                valido = true;
            }
        }
     // Caso 2: letra inicial + 8 dígitos + letra final
        else if (len == 10) {
            boolean correcto = true;
            if (Character.isLetter(codigo.charAt(0)) && Character.isLetter(codigo.charAt(9))) {
                for (int i = 1; i < 9; i++) {
                    if (!Character.isDigit(codigo.charAt(i))) {
                        correcto = false;
                    }
                }
                if (correcto) {
                    valido = true;
                }
            }
        }

        return valido;
    }
        
}
