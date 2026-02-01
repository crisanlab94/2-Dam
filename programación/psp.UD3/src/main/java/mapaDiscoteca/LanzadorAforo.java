package mapaDiscoteca;



public class LanzadorAforo {
    public static void main(String[] args) {
        System.out.println("🏁 [LANZADOR] Iniciando simulación automática de aforo...");

        // Creamos hilos individuales. Cada uno representa un sensor enviando datos.
        // No usamos Scanner, pasamos los datos directamente al constructor.
        
        Thread t1 = new Thread(new ClienteAforo("VIP", 5));
        Thread t2 = new Thread(new ClienteAforo("PISTA", 20));
        Thread t3 = new Thread(new ClienteAforo("VIP", 3)); // Sumará a los 5 anteriores
        Thread t4 = new Thread(new ClienteAforo("TERRAZA", 12));
        Thread t5 = new Thread(new ClienteAforo("PISTA", 10)); // Sumará a los 20 anteriores

        // Arrancamos los hilos (esto activa el método run() de cada ClienteAforo)
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        System.out.println("✅ [LANZADOR] Todos los hilos han sido disparados al servidor.");
    }
}