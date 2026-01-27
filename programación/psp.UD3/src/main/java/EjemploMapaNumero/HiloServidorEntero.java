package EjemploMapaNumero;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidorEntero implements Runnable {
    private Socket socket;
    private PuntuacionJuegoEntero compartido; // El objeto con el Mapa de tipo <Integer, Integer>

    public HiloServidorEntero(Socket socket, PuntuacionJuegoEntero compartido) {
        this.socket = socket;
        this.compartido = compartido;
    }

    @Override
    public void run() {
        try {
            // 1. Preparamos la lectura y escritura
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // 2. Leemos la línea del cliente (ej: "105,5")
            String linea = entrada.readLine();

            if (linea != null && !linea.isEmpty()) {
                String[] partes = linea.split(",");
                
                // 3. DOBLE CONVERSIÓN: ID y Puntos deben ser enteros
                int idCliente = Integer.parseInt(partes[0]); // El "105" pasa a ser 105
                int puntosEnviados = Integer.parseInt(partes[1]); // El "5" pasa a ser 5

                // 4. Usamos el método del objeto compartido pasándole el ID entero
                int total = compartido.sumarPuntos(idCliente, puntosEnviados);

                // 5. Respondemos al cliente usando el ID
                salida.println("SERVIDOR: ID " + idCliente + ", tu total acumulado es " + total);
                System.out.println("Puntos actualizados para el cliente ID: " + idCliente);
            }

            // 6. Cerramos la conexión (Igual que en tu ejemplo)
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}