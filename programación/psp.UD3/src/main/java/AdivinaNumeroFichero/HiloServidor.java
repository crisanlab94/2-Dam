package AdivinaNumeroFichero; // Paquete donde se encuentra la clase

import java.io.BufferedReader;       // Para leer texto del cliente
import java.io.InputStreamReader;    // Convierte bytes en texto
import java.io.PrintWriter;          // Para enviar texto al cliente
import java.net.Socket;              // Representa la conexión con el cliente

public class HiloServidor implements Runnable { // Clase que se ejecuta en un hilo independiente
    Socket socket;              // Socket del cliente
    ObjetoCompartido objeto;    // Objeto compartido con la lógica del juego
    String nombre;              // Nombre del cliente

    public HiloServidor(Socket s, ObjetoCompartido o, String nombreCliente) {
        socket = s;             // Guarda el socket del cliente
        objeto = o;             // Guarda el objeto compartido
        nombre = nombreCliente; // Guarda el nombre del cliente
    }

    public void run() { // Método que se ejecuta al iniciar el hilo

        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            // Permite leer mensajes enviados por el cliente

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); 
            // Permite enviar mensajes al cliente con auto-flush

            boolean seguir = true; // Controla si el juego continúa

            while (seguir) { // Bucle principal del juego

                salida.println("SERVIDOR (" + nombre + "): Envíame un número"); 
                // Pide un número al cliente

                String linea = entrada.readLine(); // Lee el mensaje del cliente
                int numero = Integer.parseInt(linea); // Convierte el mensaje a entero

                objeto.comprobarNumero(numero); // Comprueba el número usando el objeto compartido

                salida.println("SERVIDOR (" + nombre + "): " + objeto.ultimaRespuesta); 
                // Envía la respuesta al cliente

                if (objeto.ultimaRespuesta.equals("ACIERTO") || objeto.ultimaRespuesta.equals("FIN")) { 
                    seguir = false; // Termina el juego si acierta o finaliza
                }
            }

            socket.close(); // Cierra la conexión con el cliente

        } catch (Exception e) {
            System.out.println("SERVIDOR (" + nombre + ") ERROR: " + e.getMessage()); 
            // Muestra errores en consola
        }
    }
}
