package mapaAlmacen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteAlmacen implements Runnable {
    private int id;
    private String estado;
    private boolean urgente;

    public ClienteAlmacen(int id, String estado, boolean urgente) {
        this.id = id;
        this.estado = estado;
        this.urgente = urgente;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 6000);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Enviamos la cadena formateada
            String mensaje = id + "," + estado + "," + urgente;
            salida.println(mensaje);
            
            // Leemos la confirmación del servidor
            String respuesta = entrada.readLine();
            System.out.println("[REPARTIDOR " + id + "] Servidor confirma: " + respuesta);

        } catch (Exception e) {
            System.err.println("Error en cliente: " + e.getMessage());
        }
    }
}