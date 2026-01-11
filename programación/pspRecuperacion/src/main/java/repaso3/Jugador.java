package repaso3;

public class Jugador implements Runnable {
    private String id;
    private ClubPadel club;

    public Jugador(String id, ClubPadel club) {
        this.id = id;
        this.club = club;
    }

    @Override
    public void run() {
        // El jugador intenta entrar
        if (club.intentarEntrar(this)) {
            try {
                // Juega durante un tiempo
                Thread.sleep((long) (Math.random() * 3000)); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Siempre liberar y contar al terminar si consiguió entrar
                club.salirYContar(this);
            }
        }
    }

    public String getId() { return id; }
}