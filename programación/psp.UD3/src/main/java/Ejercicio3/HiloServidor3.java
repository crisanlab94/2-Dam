package Ejercicio3;

import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor3 implements Runnable {
    private Socket socket;
    private Contador contador;

    public HiloServidor3(Socket socket, Contador contador) {
        this.socket = socket;
        this.contador = contador; // Recibimos el contador compartido
    }

    @Override
    public void run() {
        try {
            // 1. Incrementamos y obtenemos el número
            int miNumero = contador.incrementar();
            System.out.println("Nueva conexión. Total: " + miNumero);

            // 2. Informamos al cliente
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            salida.println("Eres el cliente número " + miNumero);

            // Simulamos que el cliente está conectado un rato
            Thread.sleep(5000);

            socket.close();
            System.out.println("Cliente " + miNumero + " desconectado.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}