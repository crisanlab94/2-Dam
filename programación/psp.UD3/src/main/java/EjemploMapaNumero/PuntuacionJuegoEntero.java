package EjemploMapaNumero;

import java.util.HashMap;
import java.util.Map;

public class PuntuacionJuegoEntero {
	// Almacén: ID (Integer) -> Puntos (Integer)
    private Map<Integer, Integer> mapaPuntos;

    public PuntuacionJuegoEntero() {
        this.mapaPuntos = new HashMap<>();
    }

    /**
     * ACTUALIZAR PUNTOS (Sobrescribe el valor anterior)
     */
    public synchronized void actualizarPuntos(int id, int puntosNuevos) {
        mapaPuntos.put(id, puntosNuevos);
        System.out.println("Actualizado: El ID " + id + " ahora tiene " + puntosNuevos);
    }

    /**
     * SOLO SUMAR (No devuelve nada, solo actualiza el mapa)
     */
    public synchronized void soloSumar(int id, int puntosASumar) {
        int actuales = mapaPuntos.getOrDefault(id, 0);
        mapaPuntos.put(id, actuales + puntosASumar);
    }

    /**
     * OBTENER PUNTOS (Solo lectura)
     */
    public synchronized int obtenerPuntos(int id) {
        return mapaPuntos.getOrDefault(id, 0);
    }

    /**
     * SUMAR PUNTOS (Lógica: Obtiene y Suma)
     * Este es el método acumulador que pide tu examen.
     */
    public synchronized int sumarPuntos(int id, int puntosASumar) {
        // 1. OBTIENE: Buscamos cuánto tiene ya (si no existe, devuelve 0)
        int puntosActuales = mapaPuntos.getOrDefault(id, 0);
        
        // 2. SUMA: Calculamos el nuevo total
        int total = puntosActuales + puntosASumar;
        
        // 3. GUARDA: Actualizamos el mapa con el nuevo acumulado
        mapaPuntos.put(id, total);
        
        return total; // Devolvemos el total para que el hilo lo envíe al cliente
    }
    
    //Eliminar un jugador
    public synchronized void eliminarJugador(String nombre) {
        if (mapaPuntos.containsKey(nombre)) {
            mapaPuntos.remove(nombre);
            System.out.println("El jugador " + nombre + " ha sido eliminado del sistema.");
        }
    }
    
    //Borrar todas las puntuaciones
    public synchronized void vaciarRanking() {
        mapaPuntos.clear();
        System.out.println("El ranking ha sido reseteado por completo.");
    }
    
    //Obtener ganador
    public synchronized String obtenerGanador() {
        if (mapaPuntos.isEmpty()) return "Nadie todavía";
        
        // Cambiamos ganador a int o Integer porque la clave es numérica
        int ganadorId = -1; 
        int max = -1;

        // EL CAMBIO CLAVE: Map.Entry<Integer, Integer>
        for (Map.Entry<Integer, Integer> e : mapaPuntos.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                ganadorId = e.getKey();
            }
        }
        return "ID " + ganadorId + " (" + max + " pts)";
    }
    
 // Cuenta cuántos jugadores hay registrados
    public synchronized int totalJugadores() {
        return mapaPuntos.size();
    }

    // Suma todos los puntos de todos los jugadores
    public synchronized int sumaTotalPuntos() {
        int total = 0;
        for (int p : mapaPuntos.values()) {
            total += p;
        }
        return total;
    }
    
    public synchronized boolean existeJugador(String nombre) {
        return mapaPuntos.containsKey(nombre);
    }

    
 // STATS: Resumen rápido
    public synchronized String obtenerEstadisticas() {
        return "Jugadores: " + mapaPuntos.size();
    }
    
 // Busca jugadores con puntuación PAR
    public synchronized String obtenerPares() {
        StringBuilder resultado = new StringBuilder("Pares: ");
        for (Map.Entry<Integer, Integer> e : mapaPuntos.entrySet()) {
            if (e.getValue() % 2 == 0) {
                resultado.append(e.getKey()).append("(").append(e.getValue()).append(") ");
            }
        }
        return resultado.toString();
    }

    // Busca jugadores con puntuación IMPAR
    public synchronized String obtenerImpares() {
        StringBuilder resultado = new StringBuilder("Impares: ");
        for (Map.Entry<Integer, Integer> e : mapaPuntos.entrySet()) {
            if (e.getValue() % 2 != 0) {
                resultado.append(e.getKey()).append("(").append(e.getValue()).append(") ");
            }
        }
        return resultado.toString();
    }
    
    //Media
    public synchronized double obtenerMedia() {
        if (mapaPuntos.isEmpty()) return 0;
        int suma = 0;
        for (int p : mapaPuntos.values()) {
            suma += p;
        }
        return (double) suma / mapaPuntos.size();
    }
}
