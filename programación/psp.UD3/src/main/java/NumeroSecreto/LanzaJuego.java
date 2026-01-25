package NumeroSecreto;



public class LanzaJuego {
    public static void main(String[] args) {
        // Lanzamos 3 jugadores a la vez
        for (int i = 1; i <= 3; i++) {
            new ClienteJuego(i).start();
            
            try {
                // Una pausa mínima para que no se atropellen al conectar
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
