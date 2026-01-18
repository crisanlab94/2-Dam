package monitorWait;

public class Cocinero implements Runnable {
    private Restaurante res;
    public Cocinero(Restaurante res) { this.res = res; }

    @Override
    public void run() {
        // En vez de while(true), hacemos 5 platos
        for (int i = 1; i <= 5; i++) {
            res.ponerPlato("Chef Pepe");
        }
        System.out.println("Chef Pepe: He terminado mi turno.");
    }
}


