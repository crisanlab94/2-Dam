package mapaMotor;



import java.util.HashMap;
import java.util.Map;

public class MotoresMonitor {
    // Clave: Número de Serie (int) | Valor: Presión Aceite (int)
    private Map<Integer, Integer> mapaPresiones;

    public MotoresMonitor() {
        this.mapaPresiones = new HashMap<>();
    }

    public synchronized void registrarPrueba(int serie, int presion) {
        // El .put() se encarga de todo: si existe lo cambia, si no lo crea.
        mapaPresiones.put(serie, presion);

        // Mensaje de consola para el examen
        System.out.println("⚙️ [MOTOR " + serie + "] Prueba registrada -> Presión: " + presion + " PSI");
    }
}