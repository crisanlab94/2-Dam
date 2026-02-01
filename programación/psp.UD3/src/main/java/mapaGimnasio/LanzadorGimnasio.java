package mapaGimnasio;

import java.util.Scanner;

public class LanzadorGimnasio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dni;

        System.out.println("=== BIENVENIDO A LA RECEPCIÓN DE SMARTFIT ===");
        
        while (true) {
            System.out.print("\nIntroduce DNI (o escribe 'salir'): ");
            dni = sc.nextLine();
            
            if (dni.equalsIgnoreCase("salir")) break;

            System.out.print("Introduce Actividad: ");
            String actividad = sc.nextLine();

            // --- BLOQUE DE CONTROL DE ERRORES ---
            int cal;
            try {
                System.out.print("Introduce Calorías: ");
                cal = Integer.parseInt(sc.nextLine());
                
                // Bonus: También evitamos que metan números negativos
                if (cal < 0) {
                    System.out.println(" Error: Las calorías no pueden ser negativas.");
                    continue; // Vuelve al inicio del while
                }
                
            } catch (NumberFormatException e) {
                System.out.println("⚠¡Error! Por favor, introduce un número entero válido para las calorías.");
                continue; // Si hay error, vuelve a empezar el bucle pidiendo el DNI
            }
            // ------------------------------------

            // Si llegamos aquí, los datos son correctos y lanzamos el hilo
            new Thread(new ClienteGimnasio(dni, actividad, cal)).start();
        }

        System.out.println("Cerrando recepción. ¡Buen entrenamiento!");
        sc.close();
    }
}