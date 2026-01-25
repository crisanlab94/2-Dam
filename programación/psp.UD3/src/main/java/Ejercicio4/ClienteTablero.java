package Ejercicio4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTablero extends Thread {
   

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 44444);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

            String idReal = entrada.readLine();
            String estadoInicial = entrada.readLine();
            
            System.out.println("[ID: " + idReal + "] Conexión establecida.");

            if ("JUEGA".equals(estadoInicial)) {
                boolean continuar = true;

                while (continuar) {
                    System.out.print("Introduce fila,columna (ej: 1,1): ");
                    String coords = sc.nextLine();
                    salida.println(coords);

                    // Leemos resultado del premio
                    String msg = entrada.readLine();
                    if (msg != null) System.out.println("SERVIDOR: " + msg);

                    // Leemos señal de control
                    String señal = entrada.readLine();
                    if (señal == null || señal.equals("FIN_PARTIDA")) {
                        continuar = false;
                        String despedida = entrada.readLine();
                        if (despedida != null) System.out.println(despedida);
                    }
                }
            } else {
                System.out.println("Juego finalizado. No quedan premios.");
            }
        } catch (Exception e) {
            System.err.println("Desconectado.");
        }
    }
}