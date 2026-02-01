package mapaActualizar;


import java.util.HashMap;
import java.util.Map;

public class EnviosMonitor {
    private Map<String, String> mapaUbicaciones;

    public EnviosMonitor() {
        this.mapaUbicaciones = new HashMap<>();
    }

    public synchronized void actualizarUbicacion(String idPaquete, String ciudad) {
        // LÓGICA DE ACTUALIZAR STRING: El .put() machaca el valor anterior
        mapaUbicaciones.put(idPaquete, ciudad);

        System.out.println("🚚 [LOGÍSTICA] Paquete: " + idPaquete + " | Localizado en: " + ciudad);
    }
}