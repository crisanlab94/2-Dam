package repaso.accesoADatos.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import repaso.accesoADatos.modelo.Pokemon;
import repaso.accesoADatos.repositorio.RepositorioPokemon;

public class ServicioPokemon {
	 // Repositorio donde se guardan todos los Pokémon
    private RepositorioPokemon repo;

    // Constructor que recibe el repositorio
    public ServicioPokemon(RepositorioPokemon repo) {
        this.repo = repo;
    }

    /**
     * Devuelve el tipo de Pokémon más frecuente (Agua, Fuego, Planta, etc.).
     */
    public String tipoMasFrecuente() {
        Map<String, Integer> contador = new HashMap<>();
        List<Pokemon> lista = repo.getListaPokemons();

        for (Pokemon p : lista) {
            String tipo = p.getTipo();
            contador.put(tipo, contador.getOrDefault(tipo, 0) + 1);
        }

        int max = -1;
        String tipoMas = null;
        for (Map.Entry<String, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                tipoMas = entry.getKey();
            }
        }
        return tipoMas;
    }
    
    /** Devuelve el Pokémon más pesado de la lista **/
    public Pokemon pokemonMasPesado() {
        List<Pokemon> lista = repo.getListaPokemons();
        Pokemon masPesado = null;
        double maxPeso = -1;

        for (Pokemon p : lista) {
            if (p.getPeso_kg() > maxPeso) {
                maxPeso = p.getPeso_kg();
                masPesado = p;
            }
        }
        return masPesado;
    }
    
    /**Devuelve el Pokémon más alto **/
    public Pokemon pokemonMasAlto() {
        List<Pokemon> lista = repo.getListaPokemons();
        Pokemon masAlto = null;
        double maxAltura = -1;

        for (Pokemon p : lista) {
            if (p.getAltura_m() > maxAltura) {
                maxAltura = p.getAltura_m();
                masAlto = p;
            }
        }
        return masAlto;
    }



    /**
     * Calcula cuántos Pokémon hay por cada tipo y los devuelve en un mapa.
     */
    public Map<String, Integer> cantidadPorTipo() {
        Map<String, Integer> contador = new HashMap<>();
        List<Pokemon> lista = repo.getListaPokemons();

        for (Pokemon p : lista) {
            String tipo = p.getTipo();
            contador.put(tipo, contador.getOrDefault(tipo, 0) + 1);
        }

        return contador;
    }

   
    /**
     * Devuelve el Pokémon más frecuente (que se repite más veces en el registro).
     */
    public Pokemon pokemonMasRepetido() {
        Map<Pokemon, Integer> contador = new HashMap<>();
        List<Pokemon> lista = repo.getListaPokemons();

        for (Pokemon p : lista) {
            contador.put(p, contador.getOrDefault(p, 0) + 1);
        }

        Pokemon masRepetido = null;
        int max = -1;
        for (Map.Entry<Pokemon, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                masRepetido = entry.getKey();
            }
        }
        return masRepetido;
    }
    
 

}
