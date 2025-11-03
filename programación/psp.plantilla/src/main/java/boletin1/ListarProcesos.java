package boletin1;

/*
 * EJERCICIO 3: Listar procesos del sistema
 * ------------------------------------------------------------
 * Crea un método principal que a través de ProcessBuilder invoque
 * al comando 'tasklist' de Windows y muestre línea a línea el resultado.
 */

import java.io.*;

public class ListarProcesos {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "tasklist");
            Process proceso = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
