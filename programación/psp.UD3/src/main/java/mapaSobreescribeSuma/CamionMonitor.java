package mapaSobreescribeSuma;


import java.util.HashMap;
import java.util.Map;

public class CamionMonitor {
    // Mapa de Strings: ID -> Conductor Actual
    private Map<String, String> conductores;
    // Mapa de Integers: ID -> Kilometraje Total
    private Map<String, Integer> kmTotales;

    public CamionMonitor() {
        this.conductores = new HashMap<>();
        this.kmTotales = new HashMap<>();
    }

    public synchronized void actualizarCamion(String id, String conductor, int kmNuevos) {
        // Lógica A: Sobrescribir el conductor
        conductores.put(id, conductor);

        // Lógica B: Sumar los kilómetros
        int kmAnteriores = kmTotales.getOrDefault(id, 0);
        int total = kmAnteriores + kmNuevos;
        kmTotales.put(id, total);

        // Mensaje de control
        System.out.println("🚛 [CAMIÓN " + id + "] Actualizado.");
        System.out.println("   👤 Conductor: " + conductor);
        System.out.println("   📈 Tramo: " + kmNuevos + " km | Acumulado: " + total + " km");
        System.out.println("-------------------------------------------------");
    }
}