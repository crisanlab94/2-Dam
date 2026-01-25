package NumeroSecreto;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorJuego {
    public static void main(String[] args) {
        int numeroSecreto = (int) (Math.random() * 100) + 1;
        ObjetoJuego juego = new ObjetoJuego(numeroSecreto);
        System.out.println("Servidor iniciado. Número secreto: " + numeroSecreto);

        try (ServerSocket servidor = new ServerSocket(44444)) {
            int idCliente = 1;
            while (true) {
                Socket cliente = servidor.accept();
                new HiloJuego(cliente, juego, idCliente++).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
