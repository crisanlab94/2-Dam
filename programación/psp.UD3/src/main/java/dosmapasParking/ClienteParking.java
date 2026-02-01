package dosmapasParking;



import java.io.PrintWriter;
import java.net.Socket;

public class ClienteParking implements Runnable {
    private String matricula;
    private String nombre;
    private int minutos;

    public ClienteParking(String matricula, String nombre, int minutos) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.minutos = minutos;
    }

    @Override
    public void run() {
        // El cliente abre su propio socket para la comunicación
        try (Socket socket = new Socket("localhost", 9000);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {
            
            // Enviamos la trama: "MATRICULA,NOMBRE,MINUTOS"
            String trama = matricula + "," + nombre + "," + minutos;
            salida.println(trama);
            
            System.out.println("🚀 [VEHÍCULO " + matricula + "] Saliendo del parking y enviando datos...");

        } catch (Exception e) {
            System.err.println("❌ [ERROR VEHÍCULO] No se pudo conectar: " + e.getMessage());
        }
    }
}
