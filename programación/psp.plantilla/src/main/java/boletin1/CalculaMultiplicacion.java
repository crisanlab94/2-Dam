package boletin1;

/*
 * ENUNCIADO:
 * Clase CalculaMultiplicacion
 * 
 * Esta clase recibe dos argumentos (inicio y fin) y calcula el producto
 * de todos los números comprendidos entre ambos valores, incluidos.
 * 
 * Se usará desde la clase Lanzador, que la compilará, generará su .jar
 * y la ejecutará pasando los valores de inicio y fin como argumentos.
 */

public class CalculaMultiplicacion {

    public static void main(String[] args) {
        try {
            // Comprobamos que se han pasado los dos argumentos
            if (args.length < 2) {
                System.out.println("Error: Faltan argumentos. Se necesitan dos números (inicio y fin).");
                System.exit(1);
            }

            // Leemos los argumentos
            int inicio = Integer.parseInt(args[0]);
            int fin = Integer.parseInt(args[1]);

            // Calculamos el producto
            long resultado = 1;
            for (int i = inicio; i <= fin; i++) {
                resultado *= i;
            }

            // Mostramos el resultado
            System.out.println("Resultado de multiplicar de " + inicio + " a " + fin + " = " + resultado);

        } catch (NumberFormatException e) {
            System.err.println("Error: Los argumentos deben ser números enteros.");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            System.exit(1);
        }
    }
}
