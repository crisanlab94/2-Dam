package Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import modeloExamen.CentroLogistico;
import modeloExamen.Tipo;
import modeloExamen.Trabajador;


public class DOMbanco {
	
	public final static String rutaResources = "src/main/resources/";

    /**
     * Lee todo el Banco de Alimentos desde el XML.
     * Al ser una estructura anidada, este método ya devuelve los centros
     * con sus listas de trabajadores rellenas.
     */
    public List<CentroLogistico> leerBancoDesdeXML(String nombreArchivo) throws Exception {
        List<CentroLogistico> listaCentros = new ArrayList<>();
        
        // 1. Cargar el documento XML
        File archivo = new File(rutaResources + nombreArchivo);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo);
        doc.getDocumentElement().normalize();

        // 2. Obtener todos los nodos <CentroLogistico>
        NodeList nodosCentros = doc.getElementsByTagName("CentroLogistico");

        for (int i = 0; i < nodosCentros.getLength(); i++) {
            Element elCentro = (Element) nodosCentros.item(i);

            // Extraer datos del Centro
            String idC = elCentro.getElementsByTagName("ID").item(0).getTextContent().trim();
            String nombreC = elCentro.getElementsByTagName("Nombre").item(0).getTextContent().trim();
            String ciudadC = elCentro.getElementsByTagName("Ciudad").item(0).getTextContent().trim();
            int comedores = Integer.parseInt(elCentro.getElementsByTagName("ComedoresAbastecidos").item(0).getTextContent().trim());

            // Crear el objeto CentroLogistico
            CentroLogistico centro = new CentroLogistico(idC, nombreC, ciudadC, comedores);

            // -------------------------------------------------------------------------
            // 3. BUCLE ANIDADO: Leer los trabajadores que están DENTRO de este centro
            // -------------------------------------------------------------------------
            NodeList nodosTrabajadores = elCentro.getElementsByTagName("Trabajador");
            
            for (int j = 0; j < nodosTrabajadores.getLength(); j++) {
                Element elTrab = (Element) nodosTrabajadores.item(j);

                String nombreT = elTrab.getElementsByTagName("Nombre").item(0).getTextContent().trim();
                String dniT = elTrab.getElementsByTagName("DNI").item(0).getTextContent().trim();
                String fechaT = elTrab.getElementsByTagName("FechaNacimiento").item(0).getTextContent().trim();
                
                // Convertir el texto del XML al Enum (ASALARIADO / VOLUNTARIO)
                String tipoStr = elTrab.getElementsByTagName("Tipo").item(0).getTextContent().toUpperCase().trim();
                Tipo tipoT = Tipo.valueOf(tipoStr);

                // Crear el objeto Trabajador (le pasamos el idC como String)
                Trabajador t = new Trabajador(nombreT, dniT, fechaT, tipoT, idC);

                // Añadir el trabajador a la lista del centro actual
                centro.getListaTrabajadores().add(t);
            }

            // Añadir el centro (ya con sus trabajadores) a la lista final
            listaCentros.add(centro);
        }

        return listaCentros;
    }
	    
	    
	    

}
