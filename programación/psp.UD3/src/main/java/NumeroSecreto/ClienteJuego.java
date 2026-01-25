package NumeroSecreto;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteJuego extends Thread {
	private int id; 

   
    public ClienteJuego(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 44444);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

            // 1. Leer bienvenida
        	System.out.println("[Cliente " + id + "] Servidor dice: " + entrada.readLine());

            String respuesta = "";
            // El cliente juega mientras el servidor no le diga que el juego ha terminado o ha ganado
            while (!respuesta.contains("ENHORABUENA") && !respuesta.contains("terminado")) {
            	System.out.print("[Cliente " + id + "] Introduce un número: ");
                String miNumero = sc.nextLine();
                salida.println(miNumero); // Enviamos al servidor

                respuesta = entrada.readLine(); // Leemos qué nos dice el servidor
                System.out.println("[Cliente " + id + "] Servidor dice: " + respuesta);
            
            }

        } catch (Exception e) {
        	System.err.println("[Cliente " + id + "] Conexión finalizada.");
        }
    }
}
