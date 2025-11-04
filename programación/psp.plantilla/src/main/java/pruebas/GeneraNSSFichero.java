package pruebas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GeneraNSSFichero {

    private static final String rutaResources = "src/main/resources/";

    // Método principal que se ejecuta como proceso hijo
    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Uso: java pruebas.GeneraNSSFichero <ficheroEntrada>");
            System.exit(1);
        }

        String ficheroEntrada = args[0];
        GeneraNSSFichero g = new GeneraNSSFichero();
        try {
            // Analiza el fichero y obtiene solo las líneas con NSS válidos
            String contenido = g.analizaNSS(ficheroEntrada);

            // Escribe el contenido filtrado en NSSs.txt
            g.escribirNSS(contenido, rutaResources + "NSSs.txt");

            // Calcula el número de NSS y lo imprime (salida que leerá el padre)
            int totalNSS = g.devuelveNumNSS(rutaResources + "NSSs.txt");
            System.out.println(totalNSS);

        } catch (IOException e) {
            System.err.println("Error al procesar el fichero: " + e.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }

    // Analiza el fichero de entrada y filtra las líneas que contienen un NSS válido
    public String analizaNSS(String rutaFichero) throws FileNotFoundException {
        File archivo = new File(rutaFichero);
        FileReader fichero = new FileReader(archivo);
        Scanner in = new Scanner(fichero);
        String contenido = "";

        while (in.hasNextLine()) {
            String linea = in.nextLine();
            // Obtenemos el primer bloque de texto antes de la coma
            String parte = linea.split(",")[0].trim();
            
          //String parte = linea.split("\\s+")[0].trim(); // antes del primer espacio

            // Si es un NSS válido, agregamos la línea al contenido
            if (esNSSValido(parte)) {
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
    public void escribirNSS(String contenido, String rutaFichero) {
        FileWriter ficheroSalida = null;
        try {
            ficheroSalida = new FileWriter(rutaFichero);
            // Encabezado opcional
            String encabezado = "NSS\n";
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

    // Cuenta el número de NSS procesados en el fichero de salida
    public int devuelveNumNSS(String ruta) {
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

    // Comprueba si un código es un NSS válido de Andalucía
    // Formato: AN + 10 dígitos
    public boolean esNSSValido(String codigo) {
        boolean valido = true;

        // Debe empezar por "AN"
        if (!codigo.startsWith("AN")) {
        	// si termina codigo.endsWith("AN")
            valido = false;
        }

        // Debe tener longitud 12: AN + 10 dígitos
        if (valido && codigo.length() != 12) {
            valido = false;
        }

        // Los 10 caracteres después de AN deben ser dígitos
        if (valido) {
            for (int i = 2; i < codigo.length(); i++) {
            	  // Character.isDigit() devuelve true si el carácter es un número 
                if (!Character.isDigit(codigo.charAt(i))) {
                    valido = false;
                }
            }
        }

        return valido;
    }
}
