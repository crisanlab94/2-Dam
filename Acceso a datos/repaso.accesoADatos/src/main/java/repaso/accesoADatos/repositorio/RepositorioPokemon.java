package repaso.accesoADatos.repositorio;

import java.util.ArrayList;
import java.util.List;

import repaso.accesoADatos.modelo.Pokemon;

public class RepositorioPokemon {
	// Lista que almacena todos los pokemons
    private List<Pokemon> listaPokemons;

    // Constructor: inicializa la lista vacía
    public RepositorioPokemon() {
        this.listaPokemons = new ArrayList<>();
    }

    // Agrega un nuevo pokemon a la lista si no es nulo
    public void agregarPokemon(Pokemon pokemon) {
        if (pokemon != null) {
            listaPokemons.add(pokemon);
        }
    }

    // Devuelve la lista completa de pokemons
    public List<Pokemon> getListaPokemons() {
        return listaPokemons;
    }

    // Actualiza un pokemon existente según su índice
    public boolean actualizarPokemon(int indice, Pokemon nuevoPokemon) {
        boolean actualizado = false;
        if (indice >= 0 && indice < listaPokemons.size() && nuevoPokemon != null) {
            listaPokemons.set(indice, nuevoPokemon);
            actualizado = true;
        }
        return actualizado;
    }

    // Elimina un pokemon según su índice en la lista
    public boolean eliminarPokemon(int indice) {
        boolean eliminado = false;
        if (indice >= 0 && indice < listaPokemons.size()) {
            listaPokemons.remove(indice);
            eliminado = true;
        }
        return eliminado;
    }

    // Comprueba si un pokemon existe en la lista usando equals
    public boolean contienePokemon(Pokemon pokemon) {
        boolean encontrado = false;
        if (listaPokemons.contains(pokemon)) {
            encontrado = true;
        }
        return encontrado;
    }

}
