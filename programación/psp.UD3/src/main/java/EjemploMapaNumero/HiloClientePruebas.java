package EjemploMapaNumero;

import java.io.*;
import java.net.Socket;

public class HiloClientePruebas implements Runnable {
    private String comando;

    public HiloClientePruebas(String comando) {
        this.comando = comando;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 5555);
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Enviamos el comando completo (ej: "SUMAR,Ana,10")
            salida.println(comando);
            
            // Leemos la respuesta del servidor
            String respuesta = entrada.readLine();
            System.out.println("CLIENTE enviando [" + comando + "] -> RECIBE: " + respuesta);

            socket.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}