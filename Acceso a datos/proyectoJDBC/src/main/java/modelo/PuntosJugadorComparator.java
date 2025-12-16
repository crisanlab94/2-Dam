package modelo;

import java.util.Comparator;

public class PuntosJugadorComparator implements Comparator<SandovalCristinaJugador> {

	@Override
    public int compare(SandovalCristinaJugador j1, SandovalCristinaJugador j2) {
        
        // Retorna positivo si j2 es mayor que j1 (Orden descendente)
        return j2.getPuntosTotales() - j1.getPuntosTotales();
    }
}
