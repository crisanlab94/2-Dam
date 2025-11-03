package boletin1;

/*
 * EJERCICIO 12: CalculaSuma + Lanzador
 * ------------------------------------------------------------
 * - Calcula la suma de números pares o impares hasta N.
 * - El lanzador compila y ejecuta esta clase dos veces:
 *   una para pares y otra para impares.
 */

// CalculaSuma.java
public class CalculaSuma {
    public static void main(String[] args) {
        String tipo = args[0]; // "par" o "impar"
        int num = Integer.parseInt(args[1]);
        int suma = 0;

        for (int i = 0; i <= num; i++) {
            if ("par".equals(tipo) && i % 2 == 0) suma += i;
            if ("impar".equals(tipo) && i % 2 != 0) suma += i;
        }

        System.out.println("La suma de los " + tipo + " es: " + suma);
    }
}




