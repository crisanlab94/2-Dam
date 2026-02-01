package mapaActualizar;


import java.util.Scanner;

public class LanzadorEnvios {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("📦 SISTEMA DE SEGUIMIENTO DE ENVÍOS");
        
        while (true) {
            System.out.print("\nIntroduce ID del Paquete (o 'salir'): ");
            String id = sc.nextLine();
            if (id.equalsIgnoreCase("salir")) break;

            System.out.print("Introduce Nueva Ubicación (Ciudad): ");
            String ciudad = sc.nextLine();

            // Lanzamos el hilo cliente para actualizar la posición
            new Thread(new ClienteEnvios(id, ciudad)).start();
        }
        
        sc.close();
        System.out.println("Sistema de entrada cerrado.");
    }
}