package accesoADatos.plantilla;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;



/**
 * Maneja lectura/escritura de JSON
 * Cambia el nombre de la clase modelo y archivo según tu proyecto
 */
public class UtilJSON {

    private Gson gson = new Gson();

    public List<Modelo> leerDesdeJSON(String archivo) {
        List<Modelo> lista = new ArrayList<>();
        try (FileReader fr = new FileReader("src/main/resources/" + archivo)) {
            Modelo[] array = new Gson().fromJson(fr, Modelo[].class);
            lista = Arrays.asList(array);
        } catch (Exception e) {
            System.out.println("Error al leer JSON: " + e.getMessage());
        }
        return lista;
    }

    public void escribirEnJSON(String archivo, List<Modelo> lista) throws Exception {
        FileWriter fw = new FileWriter("src/main/resources/" + archivo);
        gson.toJson(lista, fw);
        fw.close();
    }
}



