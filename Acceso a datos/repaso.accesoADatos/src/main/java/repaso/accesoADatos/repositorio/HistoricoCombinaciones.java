package repaso.accesoADatos.repositorio;

import java.util.ArrayList;
import java.util.List;

import repaso.accesoADatos.modelo.Combinaciones;


public class HistoricoCombinaciones {

    // Lista que almacena todas las combinaciones históricas
    private List<Combinaciones> listaHistorico;

    // Constructor: inicializa la lista vacía
    public HistoricoCombinaciones() {
        this.listaHistorico = new ArrayList<>();
    }

    // Agrega una nueva combinación al histórico si no es nula
    public void agregarCombinacion(Combinaciones combinacion) {
        if (combinacion != null) {
            listaHistorico.add(combinacion);
        }
    }

    // Devuelve la lista completa del histórico
    public List<Combinaciones> getListaHistorico() {
        return listaHistorico;
    }

    // Actualiza una combinación existente según su índice
    public boolean actualizarCombinacion(int indice, Combinaciones nuevaCombinacion) {
        boolean actualizado = false;
        if (indice >= 0 && indice < listaHistorico.size() && nuevaCombinacion != null) {
            listaHistorico.set(indice, nuevaCombinacion);
            actualizado = true;
        }
        return actualizado;
    }

    // Elimina una combinación según su índice en la lista
    public boolean eliminarCombinacion(int indice) {
        boolean eliminado = false;
        if (indice >= 0 && indice < listaHistorico.size()) {
            listaHistorico.remove(indice);
            eliminado = true;
        }
        return eliminado;
    }

    // Comprueba si una combinación existe en el histórico usando equals
    public boolean contieneCombinacion(Combinaciones combinacion) {
        boolean encontrado = false;
        if (listaHistorico.contains(combinacion)) {
            encontrado = true;
        }
        return encontrado;
    }
}
