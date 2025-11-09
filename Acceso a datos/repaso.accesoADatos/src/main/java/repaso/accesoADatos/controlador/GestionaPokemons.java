package repaso.accesoADatos.controlador;

import java.util.List;
import java.util.logging.Logger;

import repaso.accesoADatos.modelo.Pokemon;
import repaso.accesoADatos.utiles.UtilidadesJsonPokemons;
import repaso.accesoADatos.utiles.UtilidadesPokemonCsv;

/*
Clase controladora que:
 - Apartado 1: lee pokemon.json y registra el objeto leido
 - Apartado 2: escribe un CSV de un Pokemon individual
 - Apartado 3: lee pokemons.csv y devuelve lista de Pokemons
 - Apartado 4: escribe pokemons.json con la lista leida
*/
public class GestionaPokemons {

    private static final Logger logger = Logger.getLogger(GestionaPokemons.class.getName());

    public static void main(String[] args) {
        // Apartado 1: leer un único Pokemon desde pokemon.json
        String rutaPokemonJson = "src/main/resources/pokemon.json";
        Pokemon p1 = UtilidadesJsonPokemons.leePokemon(rutaPokemonJson);
        if (p1 != null) {
            logger.info("Apartado 1 - Pokémon leído: " + p1.toString());
        }

        // Apartado 2: escribir ese pokemon en CSV
        String rutaPokemonCsvSalida = "src/main/resources/pokemon_salida.csv";
        if (p1 != null) {
            UtilidadesPokemonCsv.escribePokemonCsv(rutaPokemonCsvSalida, p1);
            logger.info("Apartado 2 - CSV escrito en: " + rutaPokemonCsvSalida);
        }

        // Apartado 3: leer la lista grande desde pokemons.csv
        String rutaPokemonsCsv = "src/main/resources/pokemons.csv";
        List<Pokemon> lista = UtilidadesPokemonCsv.leeListaPokemonsDesdeCsv(rutaPokemonsCsv);
        if (lista != null) {
            logger.info("Apartado 3 - Número de pokemons leídos: " + lista.size());
        }

        // Apartado 4: escribir la lista leída en pokemons_salida.json
        String rutaPokemonsSalidaJson = "src/main/resources/pokemons_salida.json";
        if (lista != null) {
            UtilidadesJsonPokemons.escribeListaPokemons(rutaPokemonsSalidaJson, lista);
            logger.info("Apartado 4 - JSON con la lista creado en: " + rutaPokemonsSalidaJson);
        }
    }
}

