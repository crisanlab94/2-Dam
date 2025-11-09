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
import repaso.accesoADatos.modelo.Producto;

public class XMLDomProductos {
	 public final static String rutaResources = "src/main/resources/";
	
	 
	 

	    public XMLDomProductos() {
		super();
	}

		public List<Producto> leerDesdeXML(String nombreArchivo) throws Exception {
	        List<Producto> lista = new ArrayList<>();
	        Document doc = getDocumentFromXML(nombreArchivo);

	        NodeList nodos = doc.getElementsByTagName("Producto");

	        for (int i = 0; i < nodos.getLength(); i++) {
	            Node nodo = nodos.item(i);
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Element elemento = (Element) nodo;
	                Producto p = getFromElement(elemento);
	                lista.add(p);
	            }
	        }
	        return lista;
	    }

	    public Producto getFromElement(Element elemento) {
	    	int id = Integer.parseInt(elemento.getAttribute("id").trim());
	        boolean enVenta = Boolean.parseBoolean(elemento.getAttribute("enVenta").trim());
	        String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent().trim();
	        double precio = Double.parseDouble(elemento.getElementsByTagName("Precio").item(0).getTextContent().trim());
	        int stock = Integer.parseInt(elemento.getElementsByTagName("Stock").item(0).getTextContent().trim());
	        return new Producto(id, enVenta, nombre, precio, stock);
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
