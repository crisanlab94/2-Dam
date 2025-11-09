package repaso.accesoADatos.utiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import repaso.accesoADatos.modelo.Pokemon;

/**
Apartado 1:
  - leePokemon: recibe la ruta a un fichero JSON que contiene un objeto Pokemon
    y devuelve un objeto Pokemon.
Apartado 4:
  - escribeListaPokemons: escribe una lista de Pokemons en un fichero JSON.
**/
public class UtilidadesJsonPokemons {

	 private static final Logger logger = Logger.getLogger(UtilidadesJsonPokemons.class.getName());
	    private static final Gson gson = new Gson();

	    // Lee un único Pokemon desde un fichero JSON y lo devuelve
	    public static Pokemon leePokemon(String ruta) {
	        try (FileReader reader = new FileReader(ruta)) {
	            return gson.fromJson(reader, Pokemon.class);
	        } catch (FileNotFoundException e) {
	            logger.severe("No encontrado: " + ruta + " - " + e.getMessage());
	            return null;
	        } catch (IOException e) {
	            logger.severe("Error leyendo JSON: " + ruta + " - " + e.getMessage());
	            return null;
	        }
	    }

	    // Lee una lista de Pokemons desde un fichero JSON y la devuelve
	    public static List<Pokemon> leeListaPokemonsDesdeJson(String ruta) {
	        try (FileReader reader = new FileReader(ruta)) {
	            Type listType = new TypeToken<List<Pokemon>>(){}.getType();
	            return gson.fromJson(reader, listType);
	        } catch (IOException e) {
	            logger.severe("Error leyendo lista JSON: " + ruta + " - " + e.getMessage());
	            return null;
	        }
	    }

	    // Escribe una lista de Pokemons en un fichero JSON (pretty print)
	    public static void escribeListaPokemons(String ruta, List<Pokemon> pokemons) {
	        try (FileWriter writer = new FileWriter(ruta)) {
	            Gson gsonPretty = new Gson().newBuilder().setPrettyPrinting().create();
	            gsonPretty.toJson(pokemons, writer);
	            logger.info("Fichero JSON creado correctamente en: " + ruta);
	        } catch (IOException e) {
	            logger.severe("Error escribiendo JSON: " + ruta + " - " + e.getMessage());
	        }
	    }
}
