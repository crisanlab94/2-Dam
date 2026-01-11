package repaso5;

import java.util.concurrent.Semaphore;

public class ServidoraRender {
    private Semaphore capacidad; // Semáforo para controlar el máximo de 2 escenas
    private int totalFotogramas; // Contador global de fotogramas renderizados

    public ServidoraRender() {
        // El enunciado dice máximo 2 simultáneamente
        this.capacidad = new Semaphore(2);
        this.totalFotogramas = 0;
    }

    public void renderizarEscena(Proyecto p) {
        try {
            // 1. ESPERAR HASTA QUE HAYA SITIO (acquire)
            // Si hay 2, el hilo se queda aquí parado hasta que uno salga.
            capacidad.acquire();
            
            System.out.println(">>> [RENDER] " + p.getNombre() + " empieza a renderizar " + p.getFotogramas() + " fotogramas.");
            
            // 2. SIMULAR EL TIEMPO DE RENDERIZADO
            Thread.sleep((long) (Math.random() * 3000) + 1000);

            // 3. REGISTRAR RESULTADOS (synchronized)
            // Llamamos a un método protegido para sumar los fotogramas
            actualizarEstadisticas(p);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 4. SOLTAR LA PLAZA SIEMPRE
            capacidad.release();
        }
    }

    // Método sincronizado para asegurar la integridad del dato (Requisito 4)
    private synchronized void actualizarEstadisticas(Proyecto p) {
        totalFotogramas += p.getFotogramas();
        System.out.println("<<< [FIN] " + p.getNombre() + " terminado. Fotogramas totales en la granja: " + totalFotogramas);
    }
}