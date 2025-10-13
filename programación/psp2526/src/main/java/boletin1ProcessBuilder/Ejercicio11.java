package boletin1ProcessBuilder;

import java.io.IOException;

public class Ejercicio11 {
/*pb.redirectErrorStream(true); 
 * Esto significa que tanto mensajes normales como mensajes de error 
 * se podrán leer juntos desde getInputStream().
 * En la práctica, si el script Python imprime errores, también los verás en 
 * la salida estándar y no se perderán.
 */
	/*pb.inheritIO();
	 * Esta línea indica que el proceso hijo (Python) hereda los flujos de entrada/salida y error de la consola 
	 * donde se ejecuta el proceso padre (Java).
	 * Esto hace que la salida del proceso hijo (Python) se muestre directamente en la consola de Java, 
	 * sin necesidad de leerla manualmente en Java.
	 * el System.out.println para imprimir línea a línea en Java ya no es necesario porque 
	 * Python imprime directamente a la consola.
	 */
	/*Si usas inheritIO(), no necesitas ni debes leer la salida línea a línea desde Java, 
	 * porque la salida ya aparece en la consola.
	 */
	/*Si quieres controlar o procesar la salida en Java, no uses inheritIO(). Lee la salida con BufferedReader.*/

		/*Si solo quieres que la salida aparezca directamente en la consola sin procesarla, usa inheritIO() y 
		redirectErrorStream(true) para simplificar el código.*/

	public static void main(String[] args) {
        // Ruta y nombre del fichero Python
        String nombreFichero = "src\\main\\resources\\fichero.py";
        LanzadorPython lanzador = new LanzadorPython();
        lanzador.ejecutarPython(nombreFichero);
    }

    public void ejecutarPython(String rutaFichero) {
        // Comando para ejecutar python con el archivo como argumento
        String[] comando = {"python", rutaFichero};

        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true); // Une salida estándar y errores
            pb.inheritIO();               // Hereda la consola para mostrar salida directamente

            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();

            System.out.println("Proceso finalizado con código: " + codigoSalida);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
