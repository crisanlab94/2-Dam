package AdivinaNumeroFichero; // Paquete donde se encuentra la clase

import java.net.ServerSocket; // Permite crear un servidor que escucha conexiones
import java.net.Socket;       // Representa cada conexión entrante de un cliente

public class Servidor { // Clase principal del servidor

    public static void main(String[] args) { // Punto de entrada del programa

        int puerto = 6666; // Puerto donde escuchará el servidor
        ServerSocket servidor = null; // Referencia al servidor

        try {
            System.out.println("SERVIDOR: iniciando..."); 
            // Mensaje informativo al arrancar

            ObjetoCompartido objeto = new ObjetoCompartido(); 
            // Objeto compartido que contiene la lógica del juego

            servidor = new ServerSocket(puerto); 
            // Crea el servidor y lo vincula al puerto indicado

            System.out.println("SERVIDOR: escuchando en el puerto " + puerto); 
            // Indica que el servidor está listo para aceptar conexiones

            Socket clientePar = servidor.accept(); 
            // Espera y acepta la conexión del cliente PAR

            System.out.println("SERVIDOR: conexión aceptada (cliente PAR)"); 
            // Confirma la conexión del cliente PAR

            Thread hiloPar = new Thread(new HiloServidor(clientePar, objeto, "PAR")); 
            // Crea un hilo para atender al cliente PAR

            hiloPar.start();  
            // Inicia el hilo del cliente PAR

            Socket clienteImpar = servidor.accept(); 
            // Espera y acepta la conexión del cliente IMPAR

            System.out.println("SERVIDOR: conexión aceptada (cliente IMPAR)"); 
            // Confirma la conexión del cliente IMPAR

            Thread hiloImpar = new Thread(new HiloServidor(clienteImpar, objeto, "IMPAR")); 
            // Crea un hilo para atender al cliente IMPAR

            hiloImpar.start(); 
            // Inicia el hilo del cliente IMPAR

        } catch (Exception e) {
            System.out.println("SERVIDOR ERROR: " + e.getMessage()); 
            // Muestra errores ocurridos durante la ejecución
        }

        try {
            if (servidor != null) {
                servidor.close(); 
                // Cierra el servidor si estaba abierto

                System.out.println("SERVIDOR: servidor cerrado."); 
                // Mensaje indicando que el servidor se ha cerrado correctamente
            }
        } catch (Exception e) {
            System.out.println("SERVIDOR ERROR AL CERRAR: " + e.getMessage()); 
            // Muestra errores ocurridos al cerrar el servidor
        }
    }
}
