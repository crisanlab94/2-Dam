package mapaActualizaInfo;

import java.io.PrintWriter;
import java.net.Socket;

public class ClienteTermico implements Runnable {
    // 1. Necesitamos estas variables para que el hilo sepa qué enviar
    private String id;
    private int temp;

    // 2. Constructor para recibir los datos desde el Lanzador
    public ClienteTermico(String id, int temp) {
        this.id = id;
        this.temp = temp;
    }

    @Override
    public void run() {
        // 3. Declaramos fuera para que sean accesibles en el finally
        Socket s = null;
        PrintWriter out = null;

        try {
            // 4. Inicializamos dentro del try sin paréntesis
            s = new Socket("localhost", 4000);
            out = new PrintWriter(s.getOutputStream(), true);

            // 5. Enviamos la información
            out.println(id + "," + temp);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. Cerramos manualmente. Este es el bloque que suele pedir la "vieja escuela"
            try {
                if (out != null) out.close();
                if (s != null) s.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}