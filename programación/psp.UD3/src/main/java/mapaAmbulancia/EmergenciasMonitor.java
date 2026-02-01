package mapaAmbulancia;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class EmergenciasMonitor {
	private Map<Integer, Prioridad> mapaPrioridades;
    private Map<Integer, Boolean> mapaTraslados;

    public EmergenciasMonitor() {
        // Inicialización obligatoria en constructor
        this.mapaPrioridades = new HashMap<>();
        this.mapaTraslados = new HashMap<>();
    }

    public synchronized void registrarEmergencia(int id, Prioridad p, boolean enTraslado) {
        mapaPrioridades.put(id, p);
        mapaTraslados.put(id, enTraslado);
        System.out.println(" Monitor: Paciente " + id + " actualizado correctamente.");
        
        // Generamos el fichero tras cada cambio
        generarFichero();
    }

    private synchronized void generarFichero() {
        String ruta = "src/main/resources/resumen_emergencias.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))) {
            writer.println("=== ESTADO DE EMERGENCIAS SGUH ===");
            
            for (Integer id : mapaPrioridades.keySet()) {
                Prioridad prio = mapaPrioridades.get(id);
                boolean tras = mapaTraslados.getOrDefault(id, false);
                String estadoStr = tras ? "EN CAMINO" : "EN SITIO";
                
                writer.println("ID: " + id + " | PRIORIDAD: " + prio + " | ESTADO: " + estadoStr);
            }
            System.out.println("📄 Fichero actualizado en: " + ruta);
            
        } catch (Exception e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
}


