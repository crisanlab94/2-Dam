package EjemploMapaNumero;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloClienteNombre implements Runnable {
    private String nombreJugador;
    private int puntosParaEnviar;

    public HiloClienteNombre(String nombre, int puntos) {
        this.nombreJugador = nombre;
        this.puntosParaEnviar = puntos;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            // 1. Conectamos al servidor en el puerto 5555
            socket = new Socket("localhost", 5555);

            // 2. Preparamos para enviar y recibir
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 3. Enviamos los datos: "Nombre,Puntos"
            salida.println(nombreJugador + "," + puntosParaEnviar);
            System.out.println("CLIENTE [" + nombreJugador + "]: He enviado " + puntosParaEnviar + " puntos.");

            // 4. Recibimos la respuesta del Servidor (la que viene del Mapa)
            String respuesta = entrada.readLine();
            System.out.println("CLIENTE [" + nombreJugador + "]: Respuesta del servidor -> " + respuesta);

            // 5. Cerramos
            socket.close();

        } catch (Exception e) {
            System.err.println("Error en el cliente " + nombreJugador + ": " + e.getMessage());
        }
    }
}