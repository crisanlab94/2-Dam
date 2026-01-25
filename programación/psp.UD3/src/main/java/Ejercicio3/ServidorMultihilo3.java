package Ejercicio3;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMultihilo3 {
    public static void main(String[] args) {
        // El obejeto se crea fuera del bucle, una sola vez 
        Contador contadorCompartido = new Contador();
        
        try (ServerSocket servidor = new ServerSocket(44444)) {
            System.out.println("Servidor iniciado. Esperando clientes...");

            while (true) {
                Socket cliente = servidor.accept();
                
                // Le pasamos el contador al hilo para que todos usen el mismo
                Thread hilo = new Thread(new HiloServidor3(cliente, contadorCompartido));
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}