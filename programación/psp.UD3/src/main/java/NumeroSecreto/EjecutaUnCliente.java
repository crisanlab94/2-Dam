package NumeroSecreto;

public class EjecutaUnCliente {
    public static void main(String[] args) {
        // Creamos un solo cliente y lo lanzamos
        // Puedes cambiar el número de ID cada vez que lo lances si quieres
        ClienteJuego cliente = new ClienteJuego(2); 
        cliente.start();
    }
}
