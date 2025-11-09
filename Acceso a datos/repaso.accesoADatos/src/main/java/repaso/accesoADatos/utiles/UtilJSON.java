

package repaso.accesoADatos.utiles;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import repaso.accesoADatos.modelo.Producto;

/**
 * Maneja lectura/escritura de JSON
 * Cambia el nombre de la clase modelo y archivo según tu proyecto
 */
public class UtilJSON {

   

    public List<Producto> leerDesdeJSON(String archivo) {
        try {
            Gson gson = new Gson();
            FileReader fr = new FileReader("src/main/resources/" + archivo);

            // Leer el archivo JSON y convertirlo a un arreglo de Producto
            Producto[] productosArray = gson.fromJson(fr, Producto[].class);

            fr.close();

            // Convertir el arreglo a lista
            List<Producto> lista = Arrays.asList(productosArray);

            return lista;

        } catch (Exception e) {
            System.out.println("Error al leer productos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public void escribirEnJSON(String ruta, List<Producto> productos) {
        try (FileWriter fw = new FileWriter(ruta)) {
            fw.write("[\n");
            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                fw.write("  {\n");
                fw.write("    \"id\": " + p.getId() + ",\n");
                fw.write("    \"nombre\": \"" + p.getNombre() + "\",\n");
                fw.write("    \"stock\": " + p.getStock() + ",\n");
                fw.write("    \"enVenta\": " + p.isEnVenta() + "\n");
                fw.write("  }" + (i < productos.size() - 1 ? "," : "") + "\n");
            }
            fw.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void escribirEnXML(String ruta, List<Producto> productos) {
        try (FileWriter fw = new FileWriter(ruta)) {
            fw.write("<ListaProductos>\n");
            for (Producto p : productos) {
                fw.write("  <Producto id=\"" + p.getId() + "\" enVenta=\"" + p.isEnVenta() + "\">\n");
                fw.write("    <Nombre>" + p.getNombre() + "</Nombre>\n");
                fw.write("    <Stock>" + p.getStock() + "</Stock>\n");
                fw.write("  </Producto>\n");
            }
            fw.write("</ListaProductos>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



