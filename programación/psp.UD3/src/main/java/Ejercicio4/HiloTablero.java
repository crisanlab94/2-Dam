package Ejercicio4;

import java.io.*;
import java.net.Socket;

public class HiloTablero extends Thread {
    private Socket socket;
    private Tablero tablero;
    private int id;

    public HiloTablero(Socket socket, Tablero tablero, int id) {
        this.socket = socket;
        this.tablero = tablero;
        this.id = id;
    }

    @Override
    public void run() {
        try {
        	PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            salida.println(id);

            if (tablero.getPremiosRestantes() <= 0) {
                salida.println("FIN"); 
            } else {
                salida.println("JUEGA");
                
                int intentos = 0;
                String linea;
                linea = entrada.readLine();

                while (intentos < 3 && tablero.getPremiosRestantes() > 0 && linea != null ){
                    try {
                        intentos++;
                        String[] partes = linea.split(",");
                        int f = Integer.parseInt(partes[0].trim());
                        int c = Integer.parseInt(partes[1].trim());

                        String resultado = tablero.intentarCapturar(f, c);
                        
                        // Lógica para el mensaje exacto
                        if (resultado.startsWith("REPETIDO:")) {
                            String nombre = resultado.split(":")[1];
                            salida.println("El premio " + nombre + " ya ha sido concedido. Sigue jugando…");
                        } else if (resultado.equals("VACIO")) {
                            salida.println("No hay premio en esa posición. Intento " + intentos + "/3.");
                        } else if (resultado.equals("ERROR")) {
                            salida.println("Coordenadas incorrectas.");
                        } else {
                            salida.println("¡HAS GANADO UN " + resultado + "!");
                        }

                        // Señal de control para el cliente
                        if (intentos < 3 && tablero.getPremiosRestantes() > 0) {
                            salida.println("SIGUE");
                        } else {
                            salida.println("FIN_PARTIDA");
                        }
                        
                    } catch (Exception e) {
                        salida.println("Error de formato (fila,columna)");
                        salida.println("SIGUE");
                    }
                }
                if (tablero.getPremiosRestantes() <= 0) {
                    salida.println("Juego finalizado. No quedan premios en el tablero.");
                } else {
                    salida.println("Partida finalizada. Has agotado tus 3 intentos.");
                }
            }
        } catch (IOException e) {
            System.out.println("Cliente " + id + " desconectado.");
        } finally {
            System.out.println("Cerrando hilo del cliente => " + id);
        }
    }
}