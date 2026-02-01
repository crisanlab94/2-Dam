package dosmapasLibreria;



import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LibreriaMonitor {
    private Map<String, String> mapaCategorias;
    private Map<String, Integer> mapaStock;

    public LibreriaMonitor() {
        // Inicializamos en el constructor
        this.mapaCategorias = new HashMap<>();
        this.mapaStock = new HashMap<>();
    }

    public synchronized void actualizarStock(String isbn, String categoria, int cantidad) {
        // 1. Guardamos/Actualizamos categoría
        mapaCategorias.put(isbn, categoria);

        // 2. Lógica de suma de stock
        int stockActual = mapaStock.getOrDefault(isbn, 0);
        mapaStock.put(isbn, stockActual + cantidad);

        System.out.println("📦 Stock actualizado: " + isbn + " (Total: " + (stockActual + cantidad) + ")");
        
        
        mostrarEstadoMapas();
        // 3. Escribimos informe
        generarInforme();
    }

    private synchronized void generarInforme() {
        String ruta = "src/main/resources/informe_stock.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))) {
            writer.println("=== LISTADO DE EXISTENCIAS LIBRERÍA ===");
            
            for (String isbn : mapaStock.keySet()) {
                String cat = mapaCategorias.get(isbn);
                int unidades = mapaStock.get(isbn);
                writer.println("ISBN: " + isbn + " | CATEGORÍA: " + cat + " | UNIDADES: " + unidades);
            }
        } catch (Exception e) {
            System.err.println("Error en fichero: " + e.getMessage());
        }
    }
    
    //ver por consola
 // Método para mostrar todo el mapa por la consola del servidor
    public synchronized void mostrarEstadoMapas() {
        System.out.println("\n--- ESTADO ACTUAL DEL ALMACÉN ---");
        for (String isbn : mapaStock.keySet()) {
            String cat = mapaCategorias.get(isbn);
            int unidades = mapaStock.get(isbn);
            System.out.println("ID: " + isbn + " | Categoría: " + cat + " | Stock: " + unidades);
        }
        System.out.println("----------------------------------\n");
    }
}