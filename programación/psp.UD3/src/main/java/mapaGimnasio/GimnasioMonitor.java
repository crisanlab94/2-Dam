package mapaGimnasio;



import java.util.HashMap;
import java.util.Map;

public class GimnasioMonitor {
    private Map<String, String> ultimaActividad;
    private Map<String, Integer> caloriasTotales;

    public GimnasioMonitor() {
        this.ultimaActividad = new HashMap<>();
        this.caloriasTotales = new HashMap<>();
    }

    public synchronized void registrarEntrenamiento(String dni, String actividad, int calorias) {
        // Actualizamos la última actividad (sobrescribe)
        ultimaActividad.put(dni, actividad);

        // Acumulamos las calorías (suma)
        int anteriores = caloriasTotales.getOrDefault(dni, 0);
        int nuevoTotal = anteriores + calorias;
        caloriasTotales.put(dni, nuevoTotal);

        System.out.println("\n🔥 [MONITOR] Datos actualizados para: " + dni);
        System.out.println("   👟 Actividad: " + actividad);
        System.out.println("   ✨ Calorías sesión: " + calorias + " | Total acumulado: " + nuevoTotal);
        System.out.println("-------------------------------------------------");
    }
}