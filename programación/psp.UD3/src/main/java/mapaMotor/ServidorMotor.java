package mapaMotor;



import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMotor {
    public static void main(String[] args) {
        // 1. Creamos el monitor (el objeto compartido)
        MotoresMonitor monitor = new MotoresMonitor();
        int puerto = 7000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("⚙️ Servidor de Control de Motores activo en puerto " + puerto);

            while (true) {
                // 2. El servidor se queda pausado aquí hasta que llega un cliente
                Socket socketCliente = servidor.accept();
                
                // 3. Cuando llega un motor, le lanzamos un hilo para atenderle
                Thread hilo = new Thread(new HiloMotor(socketCliente, monitor));
                hilo.start();
            }
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}