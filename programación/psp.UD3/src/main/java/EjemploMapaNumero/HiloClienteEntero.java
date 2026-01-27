package EjemploMapaNumero;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloClienteEntero implements Runnable {
    private int idJugador; // Ahora es int
    private int puntosParaEnviar;

    public HiloClienteEntero(int id, int puntos) {
        this.idJugador = id;
        this.puntosParaEnviar = puntos;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            // 1. Conectamos al servidor en el puerto 5555
            socket = new Socket("localhost", 5555);

            // 2. Preparamos canales (Estilo clásico)
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 3. Enviamos los datos: "ID,Puntos"
            // Al concatenar con la coma, Java convierte el int a String automáticamente para el envío
            salida.println(idJugador + "," + puntosParaEnviar);
            System.out.println("CLIENTE [ID " + idJugador + "]: He enviado " + puntosParaEnviar + " puntos.");

            // 4. Recibimos respuesta del Mapa del servidor
            String respuesta = entrada.readLine();
            System.out.println("CLIENTE [ID " + idJugador + "]: Respuesta servidor -> " + respuesta);

            // 5. Cerramos
            socket.close();

        } catch (Exception e) {
            System.err.println("Error en el cliente ID " + idJugador + ": " + e.getMessage());
        }
    }
}