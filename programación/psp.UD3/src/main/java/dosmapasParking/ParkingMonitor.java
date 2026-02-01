package dosmapasParking;



import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ParkingMonitor {
    private Map<String, String> mapaPropietarios;
    private Map<String, Integer> mapaMinutosTotales;

    public ParkingMonitor() {
        // Inicialización en el constructor
        this.mapaPropietarios = new HashMap<>();
        this.mapaMinutosTotales = new HashMap<>();
    }

    public synchronized void registrarSalida(String matricula, String nombre, int minutos) {
        mapaPropietarios.put(matricula, nombre);

        int acumulado = mapaMinutosTotales.getOrDefault(matricula, 0);
        int nuevoTotal = acumulado + minutos;
        mapaMinutosTotales.put(matricula, nuevoTotal);

        // AÑADE ESTOS MENSAJES:
        System.out.println("-------------------------------------------");
        System.out.println("📥 [SERVIDOR] Procesando matrícula: " + matricula);
        System.out.println("👤 Propietario: " + nombre);
        System.out.println("⏱️ Minutos sesión: " + minutos + " | Total acumulado: " + nuevoTotal);
        System.out.println("-------------------------------------------");
    
        escribirFichero();
    }

    private synchronized void escribirFichero() {
        String ruta = "src/main/resources/registro_parking.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))) {
            writer.println("--- REGISTRO HISTÓRICO DE OCUPACIÓN ---");
            
            for (String matricula : mapaMinutosTotales.keySet()) {
                String dueño = mapaPropietarios.get(matricula);
                int total = mapaMinutosTotales.get(matricula);
                writer.println("MATRÍCULA: " + matricula + " | PROPIETARIO: " + dueño + " | TOTAL MINUTOS: " + total);
            }
        } catch (Exception e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }
}
