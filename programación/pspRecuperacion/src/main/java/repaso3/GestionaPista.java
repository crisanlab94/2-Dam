package repaso3;

public class GestionaPista {
    public static void main(String[] args) {
        // Creamos el club con 4 pistas
        ClubPadel club = new ClubPadel(4);

        // Lanzamos 10 hilos (jugadores)
        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(new Jugador("J-" + i, club));
            t.start();
        }
    }
}