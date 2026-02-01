package dosmapasLibreria;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloLibrero implements Runnable {
    private Socket socket;
    private LibreriaMonitor monitor;

    public HiloLibrero(Socket socket, LibreriaMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String datos = entrada.readLine(); // "ISBN,CATEGORIA,CANTIDAD"
            
            if (datos != null) {
                String[] partes = datos.split(",");
                String isbn = partes[0].trim();
                String categoria = partes[1].trim();
                int cantidad = Integer.parseInt(partes[2].trim());

                // Llamada al monitor
                monitor.actualizarStock(isbn, categoria, cantidad);
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
