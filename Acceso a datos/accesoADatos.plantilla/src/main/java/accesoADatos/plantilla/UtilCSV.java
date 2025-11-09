package accesoADatos.plantilla;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import Modelo;

/**
 * Maneja lectura/escritura de CSV
 * Cambia el separador y columnas según tu CSV
 */
public class UtilCSV {

    private final String separador = ",";

    public List<Modelo> leerDesdeCSV(String archivo) throws Exception {
        List<Modelo> lista = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(separador);
            Modelo obj = new Modelo();
            obj.setAtributo1(partes[0]);
            obj.setAtributo2(Integer.parseInt(partes[1]));
            obj.setAtributo3(partes[2]);
            // listaAtributos se puede parsear si está en una columna separada por ;
            lista.add(obj);
        }
        br.close();
        return lista;
    }

    public void escribirEnCSV(String archivo, List<Modelo> lista) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/" + archivo));
        for (Modelo obj : lista) {
            bw.write(obj.getAtributo1() + separador + obj.getAtributo2() + separador + obj.getAtributo3());
            bw.newLine();
        }
        bw.close();
    }
}


}
