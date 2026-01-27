package EjemploMapaNumero;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidorString implements Runnable {
    private Socket socket;
    private PuntuacionJuegoString compartido; // El objeto con el Mapa

    public HiloServidorString(Socket socket, PuntuacionJuegoString compartido) {
        this.socket = socket;
        this.compartido = compartido;
    }

    @Override
    public void run() {
        try {
            // 1. Preparamos la lectura y escritura
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // 2. Leemos la línea del cliente (ej: "Ana,10")
            String linea = entrada.readLine();

            if (linea != null && !linea.isEmpty()) {
                String[] partes = linea.split(",");
                String nombre = partes[0];
                int puntos = Integer.parseInt(partes[1]);

                // 3. Usamos el método acumulador del objeto compartido
                int total = compartido.sumarPuntos(nombre, puntos);

                // 4. Respondemos al cliente
                salida.println("SERVIDOR: Hola " + nombre + ", tu total es " + total);
                System.out.println("Puntos actualizados para " + nombre);
            }

            // 5. Cerramos la conexión
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}