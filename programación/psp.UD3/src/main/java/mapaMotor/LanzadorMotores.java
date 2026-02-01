package mapaMotor;



public class LanzadorMotores {
    public static void main(String[] args) {
        System.out.println("🚀 [LANZADOR] Iniciando pruebas de presión de motores...");

        // Creamos hilos para simular diferentes motores en la línea de montaje
        // Motor 101 pasa con 70 PSI
        Thread m1 = new Thread(new ClienteMotor(101, 70));
        
        // Motor 202 pasa con 90 PSI
        Thread m2 = new Thread(new ClienteMotor(202, 90));
        
        // El motor 101 vuelve a pasar (reintento) con 85 PSI
        // ¡Esta es la clave para ver que el mapa SOBRESCRIBE!
        Thread m3 = new Thread(new ClienteMotor(101, 85));
        
        // Motor 303 pasa con 75 PSI
        Thread m4 = new Thread(new ClienteMotor(303, 75));

        // Arrancamos todos los hilos a la vez
        m1.start();
        m2.start();
        m3.start();
        m4.start();

        System.out.println("✅ [LANZADOR] Todos los hilos de prueba han sido enviados.");
    }
}