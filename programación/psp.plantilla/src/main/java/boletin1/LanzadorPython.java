package boletin1;

/*
 * EJERCICIO 8: Lanzador Python
 * ------------------------------------------------------------
 * Crea un fichero Python en resources (fichero.py) que imprima un mensaje,
 * y ejecuta ese script desde Java.
 */

import java.io.*;

public class LanzadorPython {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "src/main/resources/fichero.py");
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

