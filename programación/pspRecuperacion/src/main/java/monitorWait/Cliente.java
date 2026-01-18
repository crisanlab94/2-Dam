package monitorWait;


public class Cliente implements Runnable {
    private Restaurante res;
    public Cliente(Restaurante res) { this.res = res; }

    @Override
    public void run() {
        // El cliente también come 5 veces para ir a la par
        for (int i = 1; i <= 5; i++) {
            res.comerPlato("Cristina");
        }
        System.out.println("Cristina: Estoy llena, me voy.");
    }
}


