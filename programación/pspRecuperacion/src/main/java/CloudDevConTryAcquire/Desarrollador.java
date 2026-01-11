package CloudDevConTryAcquire;

public class Desarrollador implements Runnable {
	private String id;
    private EntornoCloud entorno;

    public Desarrollador(String id, EntornoCloud entorno) {
        this.id = id;
        this.entorno = entorno;
    }

    @Override
    public void run() {
        // Intentamos entrar. El tryAcquire ocurre dentro de este método.
        if (entorno.intentarIniciarSesion(this)) {
            try {
                // Si pudo entrar, simula el tiempo de trabajo
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // IMPORTANTE: Solo liberamos la plaza si realmente entramos
                entorno.salir(this);
            }
        } 
        // Si el 'if' es false, el hilo simplemente llega al final y muere (Rechazo)
    }

    public String getId() { return id; }

}
