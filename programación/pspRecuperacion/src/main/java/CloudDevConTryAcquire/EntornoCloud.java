package CloudDevConTryAcquire;

import java.util.concurrent.Semaphore;

public class EntornoCloud {
	private Tipo tipo;
    private Semaphore sem; // El semáforo gestiona internamente el aforo (sustituye a cuentaActual)

    public EntornoCloud(Tipo tipo, int aforo) {
        this.tipo = tipo;
        // Inicializamos el semáforo con el número de plazas (permisos)
        this.sem = new Semaphore(aforo);
    }

    /**
     * Intenta iniciar sesión. Si no hay sitio, no bloquea al hilo.
     * @return true si pudo entrar, false si estaba lleno.
     */
    public boolean intentarIniciarSesion(Desarrollador ds) {
        // tryAcquire() es el "Aforo con Rechazo":
        // Si hay permisos libres, lo coge y devuelve true.
        // Si está a 0, devuelve false INSTANTÁNEAMENTE y no duerme al hilo.
        if (sem.tryAcquire()) {
            System.out.println("[ENTRADA] El desarrollador " + ds.getId() + " ha entrado en " + tipo);
            return true;
        } else {
            System.out.println("[RECHAZO] El desarrollador " + ds.getId() + " NO ha podido entrar en " + tipo + " (Lleno)");
            return false;
        }
    }

    // Método para liberar la plaza
    public void salir(Desarrollador ds) {
        sem.release();
        System.out.println("[SALIDA] El desarrollador " + ds.getId() + " ha abandonado el entorno " + tipo);
    }

}
