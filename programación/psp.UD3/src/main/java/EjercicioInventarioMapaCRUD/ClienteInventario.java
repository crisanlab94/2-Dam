package EjercicioInventarioMapaCRUD;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClienteInventario {

    public static void main(String[] args) {

        String host = "localhost";
        int puerto = 6000;

        try {
            Socket socket = new Socket(host, puerto);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            boolean seguir = true;

            while (seguir) {

                System.out.println("\n--- MENÚ INVENTARIO (CRUD) ---");
                System.out.println("1. Crear producto");
                System.out.println("2. Leer producto");
                System.out.println("3. Actualizar producto");
                System.out.println("4. Eliminar producto");
                System.out.println("5. Listar productos");
                System.out.println("6. Salir");
                System.out.print("Opción: ");

                int opcion = sc.nextInt();
                sc.nextLine();

                if (opcion == 1) {
                    System.out.print("Producto nuevo: ");
                    String prod = sc.nextLine();
                    System.out.print("Cantidad inicial: ");
                    int cant = sc.nextInt();
                    sc.nextLine();

                    salida.println("CREAR:" + prod + ":" + cant);
                    System.out.println(entrada.readLine());

                } else {

                    if (opcion == 2) {
                        System.out.print("Producto: ");
                        String prod = sc.nextLine();

                        salida.println("LEER:" + prod);
                        System.out.println(entrada.readLine());

                    } else {

                        if (opcion == 3) {
                            System.out.print("Producto: ");
                            String prod = sc.nextLine();
                            System.out.print("Nuevo stock: ");
                            int cant = sc.nextInt();
                            sc.nextLine();

                            salida.println("ACTUALIZAR:" + prod + ":" + cant);
                            System.out.println(entrada.readLine());

                        } else {

                            if (opcion == 4) {
                                System.out.print("Producto a eliminar: ");
                                String prod = sc.nextLine();

                                salida.println("ELIMINAR:" + prod);
                                System.out.println(entrada.readLine());

                            } else {

                                if (opcion == 5) {
                                    salida.println("LISTAR");
                                    System.out.println(entrada.readLine());

                                } else {

                                    if (opcion == 6) {
                                        salida.println("EXIT");
                                        seguir = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            socket.close();

        } catch (IOException e) {
            System.err.println("Error cliente: " + e.getMessage());
        }
    }
}
