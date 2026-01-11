package repaso6;

import java.util.concurrent.Semaphore;

public class Oficina {
    // El semáforo controla el acceso. 2 ventanillas = 2 permisos.
    private Semaphore ventanillas = new Semaphore(2);

    public void realizarTramite(String nombre) {
        try {
            System.out.println("⌛ " + nombre + " llega a la oficina y espera cola...");
            
            // ACQUIRE: Si no hay hueco, el hilo se detiene aquí automáticamente.
            ventanillas.acquire(); 
            
            System.out.println(">> " + nombre + " está en la ventanilla. Realizando envío...");
            Thread.sleep(3000); // Simulación del trámite
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // RELEASE: Al salir, el hilo despierta al siguiente que esté esperando.
            System.out.println("<< " + nombre + " ha terminado y sale de la oficina.");
            ventanillas.release();
        }
    }
}