package repaso7;

public class Yacimiento {
    private int totalPiezas = 0;

    // SYNCHRONIZED: Solo un arqueólogo puede anotar a la vez.
    // No usamos semáforo porque no hay límite de personas excavando.
    public synchronized void anotarPieza(String nombre) {
        totalPiezas++; // Operación crítica (Suma)
        System.out.println("💎 Arqueólogo " + nombre + " ha encontrado una pieza. Total: " + totalPiezas);
    }
}