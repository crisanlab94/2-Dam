package monitorWait;

public class Restaurante {
    private boolean hayPlato = false;

    public synchronized void ponerPlato(String nombre) {
        while (hayPlato) {
            try { wait(); } catch (InterruptedException e) {}
        }
        System.out.println(nombre + ": Cocina un plato.");
        hayPlato = true;
        notifyAll();
    }

    public synchronized void comerPlato(String nombre) {
        while (!hayPlato) {
            try { wait(); } catch (InterruptedException e) {}
        }
        System.out.println(nombre + ": Se come el plato.");
        hayPlato = false;
        notifyAll();
    }
}

