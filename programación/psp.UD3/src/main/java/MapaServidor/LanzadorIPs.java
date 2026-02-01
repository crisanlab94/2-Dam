package MapaServidor;



public class LanzadorIPs {
    public static void main(String[] args) {
        // 1. Cliente que asigna la IP al ID 1
        Thread t1 = new Thread(new ClienteGenerico("ASIGNAR,1,192.168.1.10"));
        
        // 2. Cliente que asigna la IP al ID 2
        Thread t2 = new Thread(new ClienteGenerico("ASIGNAR,2,192.168.1.20"));
        
        try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // 3. Cliente que consulta la IP del ID 1 (Debería responder .10)
        Thread t3 = new Thread(new ClienteGenerico("CONSULTAR,1,0"));
        
        // 4. Cliente que consulta un ID que no existe (Debería responder "no asignada")
        Thread t4 = new Thread(new ClienteGenerico("CONSULTAR,99,0"));

        // Lanzamos todos los hilos
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.out.println("Lanzador IPs: Ráfaga de peticiones enviada.");
    }
}