package repaso5;

public class Proyecto implements Runnable {
    private String nombre;
    private int fotogramas;
    private ServidoraRender servidora;

    public Proyecto(String nombre, int fotogramas, ServidoraRender servidora) {
        this.nombre = nombre;
        this.fotogramas = fotogramas;
        this.servidora = servidora;
    }

    @Override
    public void run() {
        System.out.println("Enviando " + nombre + " a la cola de espera...");
        servidora.renderizarEscena(this);
    }

    // Getters para que la servidora pueda leer los datos
    public String getNombre() { return nombre; }
    public int getFotogramas() { return fotogramas; }
}