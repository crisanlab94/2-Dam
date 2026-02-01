package mapaActualizaInfo;



import java.util.Scanner;

public class LanzadorTermico {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== PANEL DE CONTROL DE TEMPERATURA ===");
        
        while (true) {
            System.out.print("\nIntroduce ID del Sensor (o 'fin'): ");
            String id = sc.nextLine();
            if (id.equalsIgnoreCase("fin")) break;

            int temp;
            try {
                System.out.print("Introduce Temperatura (°C): ");
                temp = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Introduce un número entero.");
                continue;
            }

            // Enviamos el dato al servidor
            new Thread(new ClienteTermico(id, temp)).start();
        }
        sc.close();
        System.out.println("Panel cerrado.");
    }
}