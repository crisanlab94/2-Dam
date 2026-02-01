package mapaSumar;



import java.util.HashMap;
import java.util.Map;

public class PuntosMonitor {
    private Map<String, Integer> mapaPuntos;

    public PuntosMonitor() {
        this.mapaPuntos = new HashMap<>();
    }

    public synchronized void sumarPuntos(String dni, int nuevosPuntos) {
        // LÓGICA DE SUMAR: Buscamos lo que ya hay (o 0) y sumamos
        int puntosActuales = mapaPuntos.getOrDefault(dni, 0);
        int total = puntosActuales + nuevosPuntos;
        
        mapaPuntos.put(dni, total);

        System.out.println("💳 [PUNTOS] DNI: " + dni + " | Ganados: " + nuevosPuntos + " | Total: " + total);
    }
}