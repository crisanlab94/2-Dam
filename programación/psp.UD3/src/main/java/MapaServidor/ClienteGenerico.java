package MapaServidor;


import java.io.*;
import java.net.Socket;

public class ClienteGenerico implements Runnable {
    private String mensajeAEnviar;

    // El constructor recibe el comando (ej: "ASIGNAR,1,192.168.1.1")
    public ClienteGenerico(String mensaje) {
        this.mensajeAEnviar = mensaje;
    }

    @Override
    public void run() {
        // Usamos try-with-resources para que el socket se cierre solo al terminar
        try (Socket socket = new Socket("localhost", 5555);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // 1. Enviamos el comando
            salida.println(mensajeAEnviar);

            // 2. Leemos la respuesta
            String respuesta = entrada.readLine();
            System.out.println("[CLIENTE] Enviado: " + mensajeAEnviar);
            System.out.println("[SERVIDOR] Responde: " + respuesta);

        } catch (Exception e) {
            System.err.println("Error en la conexión del cliente: " + e.getMessage());
        }
    }
}