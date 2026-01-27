package Ejercicio4;

import java.net.*;

public class ServidorTablero {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        System.out.println("Servidor iniciado...");
        //Posiciones con premio
        System.out.println("Posiciones con premio: [1, 1], [2, 3], [3, 1], [3, 4]");
        int puerto = 44444;
      	ServerSocket servidor = null;

        try {
        	servidor = new ServerSocket(puerto);
            int idCliente = 1;
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado => " + idCliente);
                new HiloTablero(cliente, tablero, idCliente++).start();
            }
        } catch (Exception e) { 
        	e.printStackTrace(); 
        	}
    }
}

