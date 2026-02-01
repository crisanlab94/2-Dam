package dosmapasParking;


public class LanzadorParking {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO SIMULACIÓN DE TRÁFICO ---");

        // Creamos los hilos usando la clase ClienteParking
        Thread v1 = new Thread(new ClienteParking("1111-AAA", "Cristina", 60));
        Thread v2 = new Thread(new ClienteParking("2222-BBB", "Juan", 120));
        Thread v3 = new Thread(new ClienteParking("1111-AAA", "Cristina", 30)); // Mismo coche vuelve a aparcar
        Thread v4 = new Thread(new ClienteParking("3333-CCC", "Laura", 45));

        // Ejecución concurrente
        v1.start();
        v2.start();
        v3.start();
        v4.start();
        
        System.out.println("✅ Todos los vehículos han procesado su salida.");
    }
}
