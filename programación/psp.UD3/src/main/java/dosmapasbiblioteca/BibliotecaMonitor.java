package dosmapasbiblioteca;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaMonitor {
    // Mapa 1: DNI -> Último Libro cogido
    private Map<String, String> ultimoLibro;
    // Mapa 2: DNI -> Cantidad total de libros
    private Map<String, Integer> totalLibros;

    public BibliotecaMonitor() {
        this.ultimoLibro = new HashMap<>();
        this.totalLibros = new HashMap<>();
    }

    public synchronized void registrarPrestamo(String dni, String nombre, String titulo) {
        // 1. Actualizamos mapas
        ultimoLibro.put(dni, titulo);
        int nuevoTotal = totalLibros.getOrDefault(dni, 0) + 1;
        totalLibros.put(dni, nuevoTotal);

        // 2. SALIDA POR CONSOLA EXACTA:
        System.out.println(nombre + " (" + dni + ") coge " + titulo + " -> Total: " + nuevoTotal);
    }
}