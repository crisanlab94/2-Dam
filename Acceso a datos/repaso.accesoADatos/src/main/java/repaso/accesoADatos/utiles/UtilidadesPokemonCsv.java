package repaso.accesoADatos.utiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import repaso.accesoADatos.modelo.Pokemon;

/**
Apartado 2:
  - escribePokemonCsv: recibe un objeto Pokemon y escribe un CSV con cabecera
    y una línea con el formato requerido.
  Notas:
  - Habilidades se escriben como "A;B" dentro de comillas para respetar la coma como separador.
**/
public class UtilidadesPokemonCsv {
	
	 private static final Logger logger = Logger.getLogger(UtilidadesPokemonCsv.class.getName());

	    public static void escribePokemonCsv(String ruta, Pokemon pokemon) {
	        try (FileWriter writer = new FileWriter(ruta)) {
	            // Escribimos la cabecera
	            writer.write("id,nombre,tipo,altura_m,peso_kg,habilidades,evoluciona_a\n");

	            // Preparamos la cadena de habilidades separada por ; y entre comillas
	            String habilidades = "";
	            if (pokemon.getHabilidades() != null && !pokemon.getHabilidades().isEmpty()) {
	                habilidades = String.join(";", pokemon.getHabilidades());
	            }

	            // Escribimos la línea con los campos, poniendo las habilidades entre comillas
	            writer.write(pokemon.getId() + "," +
	                    pokemon.getNombre() + "," +
	                    pokemon.getTipo() + "," +
	                    pokemon.getAltura_m() + "," +
	                    pokemon.getPeso_kg() + ",\"" +
	                    habilidades + "\"," +
	                    pokemon.getEvolucionaA() + "\n");

	            logger.info("CSV generado correctamente en: " + ruta);
	        } catch (IOException e) {
	            logger.severe("Error escribiendo CSV: " + ruta + " - " + e.getMessage());
	        }
	    }
	    
	    /**
	    Apartado 3:
	      - leeListaPokemonsDesdeCsv: lee el fichero CSV (cabecera + filas) y devuelve
	        una lista de objetos Pokemon.
	      Notas:
	      - Asume formato de cabecera: id,nombre,tipo,altura_m,peso_kg,habilidades,evoluciona_a
	      - Habilidades vienen como "A;B" (entre comillas o no); el método elimina las comillas antes de split.
	   * */ 
	    
	    public static List<Pokemon> leeListaPokemonsDesdeCsv(String rutaCsv) {
	        List<Pokemon> lista = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(rutaCsv))) {
	            String linea;
	            boolean primera = true;
	            while ((linea = br.readLine()) != null) {
	                // Saltar cabecera
	                if (primera) {
	                    primera = false;
	                    continue;
	                }

	                // Si la línea está vacía la saltamos
	                if (linea.trim().isEmpty()) continue;

	                // Partimos por comas pero hay un campo "habilidades" que puede estar entre comillas
	                // Estrategia simple: usar split(",") y luego limpiar las comillas del campo de habilidades
	                // Dado que el fichero está bien formado según el enunciado, esto funciona.
	                String[] partes = splitCsvLinePreservingQuotes(linea);

	                // Esperamos al menos 7 columnas
	                if (partes.length < 7) {
	                    logger.warning("Línea malformada (se ignora): " + linea);
	                    continue;
	                }

	                int id = Integer.parseInt(partes[0]);
	                String nombre = partes[1];
	                String tipo = partes[2];
	                double altura = Double.parseDouble(partes[3]);
	                double peso = Double.parseDouble(partes[4]);

	                // Limpiamos comillas y luego separamos por ;
	                String habilidadesRaw = partes[5].trim();
	                if (habilidadesRaw.startsWith("\"") && habilidadesRaw.endsWith("\"")) {
	                    habilidadesRaw = habilidadesRaw.substring(1, habilidadesRaw.length() - 1);
	                }
	                List<String> habilidades = new ArrayList<>();
	                if (!habilidadesRaw.isEmpty()) {
	                    habilidades = Arrays.asList(habilidadesRaw.split(";"));
	                }

	                String evoluciona_a = partes[6];

	                Pokemon p = new Pokemon(id, nombre, tipo, altura, peso, habilidades, evoluciona_a);
	                lista.add(p);
	            }
	        } catch (IOException e) {
	            logger.severe("Error leyendo CSV: " + rutaCsv + " - " + e.getMessage());
	        }

	        return lista;
	    }

	    // Método auxiliar que divide la línea CSV respetando comillas simples en el campo
	    private static String[] splitCsvLinePreservingQuotes(String line) {
	        // Implementación simple: si la línea contiene " hay que tratar el campo de habilidades como uno.
	        // Dado el formato conocido (7 columnas), una forma segura es:
	        //  - dividir por comas en máximo 7 partes.
	        //  - Java split con límite:
	        String[] parts = line.split(",", 7);
	        // parts[5] corresponderá a habilidades posiblemente con comillas
	        return parts;
	    }

}
