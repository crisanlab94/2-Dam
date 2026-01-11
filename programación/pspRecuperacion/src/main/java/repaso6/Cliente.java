package repaso6;

public class Cliente implements Runnable {
    private String nombre;
    private Oficina oficina;

    public Cliente(String nombre, Oficina oficina) {
        this.nombre = nombre;
        this.oficina = oficina;
    }

    @Override
    public void run() {
        oficina.realizarTramite(nombre);
    }
}