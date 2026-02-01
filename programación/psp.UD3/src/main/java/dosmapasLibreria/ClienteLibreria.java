package dosmapasLibreria;



import java.io.PrintWriter;
import java.net.Socket;

public class ClienteLibreria implements Runnable {
    private String isbn;
    private String categoria;
    private int cantidad;

    public ClienteLibreria(String isbn, String categoria, int cantidad) {
        this.isbn = isbn;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        // El cliente intenta conectar al puerto 8000
        try (Socket socket = new Socket("localhost", 8000);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {
            
            // Enviamos la trama: "ISBN,CATEGORIA,CANTIDAD"
            String trama = isbn + "," + categoria + "," + cantidad;
            salida.println(trama);
            
            System.out.println("🚀 [CLIENTE] Enviado: " + trama);

        } catch (Exception e) {
            System.err.println("❌ [CLIENTE] Error de conexión: " + e.getMessage());
        }
    }
}