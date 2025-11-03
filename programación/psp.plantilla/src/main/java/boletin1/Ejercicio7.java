package boletin1;

/*
 * EJERCICIO 7: Segundo Lanzador Java
 * ------------------------------------------------------------
 * - La hija genera un número aleatorio.
 * - Si es par: imprime “todo bien”.
 * - Si es impar: lanza excepción y sale con código 1.
 */

public class Ejercicio7 {
    public static void main(String[] args) {
        try {
            System.out.println("Yo soy tu hija.");
            int n = (int) (Math.random() * 10);
            System.out.println("Número generado: " + n);

            if (n % 2 == 0) {
                System.out.println("Ha salido par, todo bien.");
            } else {
                throw new Exception("Ha salido impar, algo va mal.");
            }

            System.exit(0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}

