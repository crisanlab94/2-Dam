package NumeroSecreto;


import java.io.*;
import java.net.Socket;

public class HiloJuego extends Thread {
    private Socket socket;
    private ObjetoJuego juego;
    private int id;

    public HiloJuego(Socket socket, ObjetoJuego juego, int id) {
        this.socket = socket;
        this.juego = juego;
        this.id = id;
    }

    public void run() {
        try {
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            salida.println("Bienvenido al juego. Adivina el número.");

            String texto;
            // El bucle lee y comprueba el final de conexión a la vez
            // Se ejecuta MIENTRAS el juego no termine Y la línea leída no sea null
            while (!juego.isJuegoTerminado() && (texto = entrada.readLine()) != null) {
                
                try {
                    int intento = Integer.parseInt(texto);
                    int resultado = juego.comprobarNumero(intento, id);

                    if (resultado == 0) {
                        salida.println("¡ENHORABUENA! HAS GANADO");
                        System.out.println("El cliente " + id + " ha ganado.");
                    } else if (resultado == 1) {
                        salida.println("Juego terminado. Otro jugador acertó.");
                    } else {
                        salida.println("Sigue intentándolo...");
                    }
                } catch (NumberFormatException e) {
                    salida.println("Error: Introduce un número válido.");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error en el hilo " + id + ": " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}