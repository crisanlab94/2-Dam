package dosmapasbiblioteca;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloBiblioteca implements Runnable {
    private Socket socket;
    private BibliotecaMonitor monitor;

    public HiloBiblioteca(Socket socket, BibliotecaMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String linea = entrada.readLine(); 
            
            if (linea != null) {
                String[] partes = linea.split(",");
                String dni = partes[0].trim();
                String nombre = partes[1].trim(); // <--- Nuevo
                String titulo = partes[2].trim();
                int dias = Integer.parseInt(partes[3].trim());

                // Pasamos el nombre al monitor
                monitor.registrarPrestamo(dni, nombre, titulo);
            }
            socket.close();
        } catch (Exception e) { /* ... */ }
    }
}
