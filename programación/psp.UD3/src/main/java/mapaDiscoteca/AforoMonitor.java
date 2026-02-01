package mapaDiscoteca;



import java.util.HashMap;
import java.util.Map;

public class AforoMonitor {
    private Map<String, Integer> mapaAforo;

    public AforoMonitor() {
        this.mapaAforo = new HashMap<>();
    }

    public synchronized void registrarEntrada(String sala, int personas) {
        // Lógica de sumar
        int actual = mapaAforo.getOrDefault(sala, 0);
        int nuevoTotal = actual + personas;
        mapaAforo.put(sala, nuevoTotal);

        // Mensaje en la consola del SERVIDOR
        System.out.println("🕺 [AFORO] Sala: " + sala + " | Entran: " + personas + " | Total: " + nuevoTotal);
    }
}