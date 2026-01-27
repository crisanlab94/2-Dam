package EjercicioInventarioMapaCRUD;


import java.io.*;
import java.net.*;

public class ServidorTCPInventario {

    public static void main(String[] args) {

        int puerto = 6000;

        InventarioCompartido compartido = new InventarioCompartido();

        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor Inventario en puerto " + puerto);

            while (true) {
                Socket socketCliente = servidor.accept();
                System.out.println("Cliente conectado: " + socketCliente.getInetAddress());

                HiloServidor hiloServidor = new HiloServidor(socketCliente, compartido);
                hiloServidor.start();
            }

        } catch (IOException e) {
            System.err.println("Error en servidor: " + e.getMessage());
        }
    }
}
