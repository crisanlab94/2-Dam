package mapaSumar;



import java.util.Scanner;

public class LanzadorPuntos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("--- GESTIÓN DE PUNTOS DE CLIENTES ---");
        
        while (true) {
            System.out.print("\nIntroduce DNI del cliente (o 'fin'): ");
            String dni = sc.nextLine();
            if (dni.equalsIgnoreCase("fin")) break;

            int pts;
            try {
                System.out.print("Introduce puntos a sumar: ");
                pts = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Los puntos deben ser un número.");
                continue;
            }

            // Lanzamos el cliente para que envíe los puntos al servidor
            new Thread(new ClientePuntos(dni, pts)).start();
        }
        
        sc.close();
        System.out.println("Lanzador cerrado.");
    }
}