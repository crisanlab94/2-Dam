package mapaActualizaInfo;



import java.util.HashMap;
import java.util.Map;

public class TermostatoMonitor {
    // Un solo mapa: ID del sensor -> Última temperatura
    private Map<String, Integer> registroTemperaturas;

    public TermostatoMonitor() {
        this.registroTemperaturas = new HashMap<>();
    }

    public synchronized void actualizarTemperatura(String id, int temp) {
        // Al usar .put, si el ID ya existe, la temperatura vieja se borra
        registroTemperaturas.put(id, temp);

        System.out.println("🌡️ [CALDERA] Sensor: " + id + " | Temp Actual: " + temp + "°C");
    }
}
