package accesoADatos.plantilla;

import java.util.List;

public class PasarFormato {
	public static void main(String[] args) {
	try {
		UtilJSON UtilJSON = new UtilJSON();
        UtilCSV UtilCSV = new UtilCSV();
        DOM DOM = new DOM();
	
	// JSON → CSV
	List<Modelo> lista = UtilJSON.leerDesdeJSON("datos.json");
	UtilCSV.escribirEnCSV("datos.csv", lista);

	// JSON → XML
	List<Modelo> listaJson = UtilJSON.leerDesdeJSON("datos.json");
	DOM.escribirEnXML("datos.xml", lista);

	// CSV → JSON
	List<Modelo> listaCSV = UtilCSV.leerDesdeCSV("datos.csv");
	UtilJSON.escribirEnJSON("datos.json", lista);

	// CSV → XML
	List<Modelo> listaCSV2 = UtilCSV.leerDesdeCSV("datos.csv");
	DOM.escribirEnXML("datos.xml", lista);

	// XML → JSON
	List<Modelo> listaXML = DOM.leerDesdeXML("datos.xml");
	UtilJSON.escribirEnJSON("datos.json", lista);

	// XML → CSV
	List<Modelo> listaXML2 = DOM.leerDesdeXML("datos.xml");
	UtilCSV.escribirEnCSV("datos.csv", lista);
	 } catch (Exception e) {
         System.err.println("Error en la conversión de formatos: " + e.getMessage());
         e.printStackTrace();
     }
 }
}
