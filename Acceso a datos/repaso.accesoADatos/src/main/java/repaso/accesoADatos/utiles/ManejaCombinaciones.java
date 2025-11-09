package repaso.accesoADatos.utiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import repaso.accesoADatos.modelo.Combinaciones;
import repaso.accesoADatos.repositorio.HistoricoCombinaciones;

public class ManejaCombinaciones {

    // Método que carga las combinaciones desde un fichero CSV al repositorio
    // Recibe la ruta del fichero y el repositorio donde se almacenarán las combinaciones
    public void cargarCombinaciones(String rutaYNombre, HistoricoCombinaciones repoHistorico) 
            throws FileNotFoundException {
        Scanner in = null;

        try {
            // Abrimos el fichero para lectura
            FileReader fichero = new FileReader(rutaYNombre);
            in = new Scanner(fichero);

            boolean primeraLinea = true; // Se usa para saltar la cabecera

            // Recorremos todas las líneas del fichero
            while (in.hasNextLine()) {
                String linea = in.nextLine();

                // Saltamos la primera línea si es cabecera
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                // Creamos un objeto Combinaciones a partir de la línea
                Combinaciones c = cargaCombinacion(linea);

                // Añadimos la combinación al repositorio
                repoHistorico.agregarCombinacion(c);
            }
        } finally {
            // Cerramos el Scanner
            if (in != null) {
                in.close();
            }
        }
    }

    // Método que convierte una línea del CSV en un objeto Combinaciones
    // La línea tiene el formato: fecha,numero1,numero2,numero3,numero4,numero5,,estrella1,estrella2
    public Combinaciones cargaCombinacion(String linea) {
        // Separamos los números de las estrellas usando la doble coma como delimitador
        String[] numerosYEstrellas = linea.split(",,"); // ["21/04/2023,07,08,18,33,42", "02,08"]

        // Números: separamos por coma y saltamos la primera posición (la fecha)
        String[] numerosStr = numerosYEstrellas[0].split(",");
        int[] numeros = new int[5];
        for (int i = 0; i < 5; i++) {
            // i+1 para saltar la fecha
            numeros[i] = Integer.parseInt(numerosStr[i + 1].trim());
        }

        // Estrellas: se separan por coma
        String[] estrellasStr = numerosYEstrellas[1].split(",");
        int[] estrellas = new int[2];
        for (int i = 0; i < 2; i++) {
            estrellas[i] = Integer.parseInt(estrellasStr[i].trim());
        }

        // Creamos el objeto Combinaciones con los números y estrellas extraídos
        return new Combinaciones(numeros, estrellas);
    }
}
