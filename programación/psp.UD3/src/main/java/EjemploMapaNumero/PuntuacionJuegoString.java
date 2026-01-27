package EjemploMapaNumero;

import java.util.HashMap;
import java.util.Map;

public class PuntuacionJuegoString {
    // Almacén: Nombre (String) -> Puntos (Integer)
    private Map<String, Integer> mapaPuntos;

    public PuntuacionJuegoString() {
        this.mapaPuntos = new HashMap<>();
    }

    /**
     * ACTUALIZAR PUNTOS (Sobrescribe el valor anterior)
     */
    public synchronized void actualizarPuntos(String nombre, int puntosNuevos) {
        mapaPuntos.put(nombre, puntosNuevos);
        System.out.println("Actualizado: " + nombre + " ahora tiene " + puntosNuevos);
    }

    /**
     * OBTENER PUNTOS (Solo lectura)
     */
    
 // 2. SOLO SUMAR (No devuelve nada, solo actualiza el mapa)
    public synchronized void soloSumar(String nombre, int puntosASumar) {
        int actuales = mapaPuntos.getOrDefault(nombre, 0);
        mapaPuntos.put(nombre, actuales + puntosASumar);
    }
    
    public synchronized int obtenerPuntos(String nombre) {
        return mapaPuntos.getOrDefault(nombre, 0);
    }

    /**
     * SUMAR PUNTOS (Lógica: Obtiene y Suma)
     * Este es el método acumulador que pide tu examen.
     */
    public synchronized int sumarPuntos(String nombre, int puntosASumar) {
        // 1. OBTIENE: Buscamos cuánto tiene ya (si no existe, devuelve 0)
        int puntosActuales = mapaPuntos.getOrDefault(nombre, 0);
        
        // 2. SUMA: Calculamos el nuevo total
        int total = puntosActuales + puntosASumar;
        
        // 3. GUARDA: Actualizamos el mapa con el nuevo acumulado
        mapaPuntos.put(nombre, total);
        
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
        
        String ganador = "";
        int max = -1;
        for (Map.Entry<String, Integer> e : mapaPuntos.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                ganador = e.getKey();
            }
        }
        return ganador + " (" + max + " pts)";
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
        for (Map.Entry<String, Integer> e : mapaPuntos.entrySet()) {
            if (e.getValue() % 2 == 0) {
                resultado.append(e.getKey()).append("(").append(e.getValue()).append(") ");
            }
        }
        return resultado.toString();
    }

    // Busca jugadores con puntuación IMPAR
    public synchronized String obtenerImpares() {
        StringBuilder resultado = new StringBuilder("Impares: ");
        for (Map.Entry<String, Integer> e : mapaPuntos.entrySet()) {
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