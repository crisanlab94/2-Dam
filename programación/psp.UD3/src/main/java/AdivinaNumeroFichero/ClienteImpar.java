package AdivinaNumeroFichero; // Paquete donde se encuentra la clase

import java.io.BufferedReader;       // Para leer texto enviado por el servidor
import java.io.FileReader;           // Para leer el fichero con números impares
import java.io.InputStreamReader;    // Convierte bytes del socket en texto
import java.io.PrintWriter;          // Para enviar texto al servidor
import java.net.Socket;              // Representa la conexión con el servidor

public class ClienteImpar implements Runnable { // Clase que se ejecuta en un hilo independiente

    public void run() { // Método principal del hilo

        try {
            Socket socket = new Socket("localhost", 6666); 
            // Conecta con el servidor en el puerto 6666

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            // Permite leer mensajes enviados por el servidor

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); 
            // Permite enviar mensajes al servidor con auto-flush

            BufferedReader fichero = new BufferedReader(new FileReader("impar.txt")); 
            // Abre el fichero que contiene los números impares

            String linea = fichero.readLine(); 
            // Lee la primera línea del fichero

            boolean seguir = true; 
            // Controla si el cliente sigue enviando números

            while (seguir && linea != null) { 
                // Bucle principal: mientras haya números y el juego no termine

                String mensaje = entrada.readLine(); 
                // Recibe mensaje del servidor

                System.out.println("CLIENTE IMPAR: recibido -> " + mensaje); 
                // Muestra el mensaje recibido

                System.out.println("CLIENTE IMPAR: envío -> " + linea); 
                // Muestra el número que va a enviar

                salida.println(linea); 
                // Envía el número al servidor

                mensaje = entrada.readLine(); 
                // Recibe la respuesta del servidor tras comprobar el número

                System.out.println("CLIENTE IMPAR: recibido -> " + mensaje); 
                // Muestra la respuesta del servidor

                if (mensaje.contains("ACIERTO") || mensaje.contains("FIN")) { 
                    seguir = false; // Termina si el servidor indica acierto o fin del juego
                } else {
                    linea = fichero.readLine(); 
                    // Lee el siguiente número del fichero
                }
            }

            socket.close(); 
            // Cierra la conexión con el servidor

            fichero.close(); 
            // Cierra el fichero

        } catch (Exception e) {
            System.out.println("CLIENTE IMPAR ERROR: " + e.getMessage()); 
            // Muestra errores en consola
        }
    }
}
