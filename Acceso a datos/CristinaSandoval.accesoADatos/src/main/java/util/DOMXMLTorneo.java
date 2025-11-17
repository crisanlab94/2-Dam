package util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import modelo.Enfrentamiento;
import modelo.Equipo;
import modelo.Videojuego;





public class DOMXMLTorneo {
	private static final Logger logger = LogManager.getLogger(DOMXMLTorneo.class);

	public final static String rutaResources = "src/main/resources/";



	// 📥 Carga un archivo XML y devuelve su objeto Document

	private Document getDocumentFromXML(String nombrefichero) {

	File file = new File("src/main/resources/" + nombrefichero);

	Document documento = null;

	try {

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	documento = dBuilder.parse(file); 

	} catch (Exception e) {

	logger.error(e.getMessage());

	}

	return documento;

	}
	
	
	public Set<Enfrentamiento> leerEnfrentamientoDesdeXML(String rutaFichero) throws Exception {

		Set<Enfrentamiento> centrosLogis = new HashSet<Enfrentamiento>();

		Document doc = getDocumentFromXML(rutaFichero);

		NodeList nodosCentros = doc.getElementsByTagName("torneoData");

		for (int j = 0; j < nodosCentros.getLength(); j++) {

		Node modeloNodo = nodosCentros.item(j);

		if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {

		Enfrentamiento e = this.getEnfrentamientoFromElement((Element) modeloNodo);

		centrosLogis.add(e);

		}

		}

		return centrosLogis;

		}





	
	private Enfrentamiento getEnfrentamientoFromElement(Element elemento) { 
		
		String id = elemento.getAttribute("id").trim();

		String fecha = elemento.getElementsByTagName("fecha").item(0).getTextContent();

		String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
		
		
		Videojuego videoJuego = Videojuego.valueOf((elemento.getElementsByTagName("videojuego").item(0).getNodeName()));
		
		
		String codigoRef = elemento.getElementsByTagName("descripcion").item(0).getTextContent();
	
		NodeList EquiposNodo = elemento.getElementsByTagName("listaEquipos");

	

		  
	    NodeList listaEquiposEnfrentamiento = elemento.getElementsByTagName("listaEquipos");
	    List<Equipo> equipos = new ArrayList<>();

	    if (listaEquiposEnfrentamiento.getLength() > 0) {
	        Element contenedor = (Element) listaEquiposEnfrentamiento.item(0);
	        NodeList listaEquipos = contenedor.getElementsByTagName("equipo");

	        for (int i = 0; i < listaEquipos.getLength(); i++) {
	            Node nodo = listaEquipos.item(i);
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Equipo e =getEquipoFromElement((Element) nodo, id);
	                equipos.add(e);
	            }
	        }
	    }
		

		return new Enfrentamiento(id, fecha, descripcion,videoJuego,codigoRef); 

		} 



		private List<Equipo> getEquiposFromElement(Element modeloNodo2, String id) {

		List<Equipo> equipos = new ArrayList<Equipo>();

		for (int j = 0; j < ((NodeList) modeloNodo2).getLength(); j++) {

		Node modeloNodo = ((NodeList) modeloNodo2).item(j);

		if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {

		Equipo e = (Equipo) this.getEquiposFromElement((Element) modeloNodo, id);

		equipos.add(e);

		}

		}

		return equipos;

		}

		
		private Equipo getEquipoFromElement(Element elemento, String idCentro) {
			
			String id = elemento.getAttribute("codigo").trim();

			String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
			
			String email = elemento.getElementsByTagName("email").item(0).getTextContent();
		
			 int numJugadores = Integer.parseInt(elemento.getElementsByTagName("numJugadores").item(0).getTextContent().trim());

		
			return new Equipo(nombre,id,numJugadores, email);

		}
		
}


