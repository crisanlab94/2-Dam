package MapaServidor;

import java.util.HashMap;
import java.util.Map;

public class AsignadorIP {
    // Mapa: ID del Cliente -> Dirección IP
    private Map<Integer, String> tablaIPs;

    public AsignadorIP() {
        this.tablaIPs = new HashMap<>();
    }

    // ASIGNAR: Guarda o actualiza la IP de un ID
    public synchronized void asignarIP(int id, String ip) {
        tablaIPs.put(id, ip);
        System.out.println("LOG: ID " + id + " vinculado a IP " + ip);
    }

    // CONSULTAR: Devuelve la IP de un ID concreto
    public synchronized String obtenerIP(int id) {
        return tablaIPs.getOrDefault(id, "IP no asignada");
    }

    // LISTADO: Devuelve todo el mapa para el administrador
    public synchronized String obtenerTodas() {
        if (tablaIPs.isEmpty()) return "Tabla vacía.";
        return tablaIPs.toString();
    }
}
