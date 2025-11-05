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
    // - letra inicial + 8 dígitos (ej: X12345678J)
    public boolean esDNIValido(String codigo) {
        boolean valido = false;
        codigo = codigo.trim(); // eliminar espacios

        if (codigo.length() == 0) {
            return false; // línea vacía
        }

        int len = codigo.length();

        if (len == 9) {
            // Español: 8 dígitos + letra
            boolean correcto = true;
            for (int i = 0; i < 8; i++) {
                if (!Character.isDigit(codigo.charAt(i))) {
                    correcto = false;
                }
            }
            if (correcto && Character.isLetter(codigo.charAt(8))) {
                valido = true;
            }
            // Extranjero: letra + 7 dígitos + letra
            else if (Character.isLetter(codigo.charAt(0)) && Character.isLetter(codigo.charAt(8))) {
                boolean digitosCorrectos = true;
                for (int i = 1; i <= 7; i++) {
                    if (!Character.isDigit(codigo.charAt(i))) {
                        digitosCorrectos = false;
                    }
                }
                if (digitosCorrectos) valido = true;
            }
        } else if (len == 10) {
            // Extranjero: letra + 8 dígitos + letra
            if (Character.isLetter(codigo.charAt(0)) && Character.isLetter(codigo.charAt(9))) {
                boolean correcto = true;
                for (int i = 1; i <= 8; i++) {
                    if (!Character.isDigit(codigo.charAt(i))) {
                        correcto = false;
                    }
                }
                if (correcto) valido = true;
            }
        }

        return valido;
    }

    /*
    //Sin validacion usamos otro analizaDNI
    ///tambien eliminamos esDNIValido
       public String analizaDNI(String rutaFichero) throws IOException {
        File archivo = new File(rutaFichero);
        FileReader fichero = new FileReader(archivo);
        Scanner in = new Scanner(fichero);
        StringBuilder contenido = new StringBuilder();

        while (in.hasNextLine()) {
            String linea = in.nextLine().trim();
            if (linea.isEmpty()) continue; // saltar líneas vacías

            // Obtenemos la primera parte de la línea antes de la coma
            String primerCampo = linea.split(",", 2)[0].trim();

            // Si NO empieza por AN, es DNI
            if (!primerCampo.startsWith("AN")) {
                contenido.append(linea).append("\n");
            }
        }

        in.close();
        fichero.close();

        return contenido.toString();
    }
     */
}
