package repaso.accesoADatos.utiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import repaso.accesoADatos.modelo.Equipo;
import repaso.accesoADatos.modelo.Piloto;
import repaso.accesoADatos.modelo.Producto;

public class DOMXMLPilotos {
	 public final static String rutaResources = "src/main/resources/";
		
	 
	 

	    public DOMXMLPilotos() {
		super();
	}

		public List<Equipo> leerDesdeXML(String nombreArchivo) throws Exception {
	        List<Equipo> lista = new ArrayList<>();
	        Document doc = getDocumentFromXML(nombreArchivo);

	        //nombre del nodo, importante que si esta en minusculas se escriba asi
	        NodeList nodos = doc.getElementsByTagName("equipo"); 

	        for (int i = 0; i < nodos.getLength(); i++) {
	            Node nodo = nodos.item(i);
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Element elemento = (Element) nodo;
	                Equipo e = getFromElement(elemento);
	                lista.add(e);
	            }
	        }
	        return lista;
	    }

	    public Equipo getFromElement(Element elemento) {
	    	//IdentificadorPiloto es atributo, pero identificadorEquipo es hijo
	    	int id = Integer.parseInt(elemento.getAttribute("identificadorEquipo").trim());
	        String nombreEquipo = elemento.getElementsByTagName("nombreEquipo").item(0).getTextContent().trim();
	        int puntos = Integer.parseInt(elemento.getElementsByTagName("puntos").item(0).getTextContent().trim());

	        List<Piloto> pilotos = new ArrayList<>();
	        NodeList listaPilotos = elemento.getElementsByTagName("piloto");

	        for (int i = 0; i < listaPilotos.getLength(); i++) {
	            Element pilotoElem = (Element) listaPilotos.item(i);

	            int idPiloto = Integer.parseInt(pilotoElem.getAttribute("identificadorPiloto").trim());
	            String nombrePiloto = pilotoElem.getElementsByTagName("nombrePiloto").item(0).getTextContent().trim();
	            int puntosPiloto = Integer.parseInt(pilotoElem.getElementsByTagName("puntos").item(0).getTextContent().trim());
	            int idEquipoPiloto = Integer.parseInt(pilotoElem.getElementsByTagName("identificadorEquipo").item(0).getTextContent().trim());
	            String pais = pilotoElem.getElementsByTagName("pais").item(0).getTextContent().trim();

	            Piloto piloto = new Piloto(idPiloto, nombrePiloto, puntosPiloto, idEquipoPiloto, pais);
	            pilotos.add(piloto);
	        }

	        return new Equipo(id, nombreEquipo, puntos, pilotos);
	    }

	    public Document getDocumentFromXML(String nombreArchivo) throws Exception {
	        File file = new File(rutaResources + nombreArchivo);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(file);
	        doc.getDocumentElement().normalize();
	        return doc;
	    }

}


