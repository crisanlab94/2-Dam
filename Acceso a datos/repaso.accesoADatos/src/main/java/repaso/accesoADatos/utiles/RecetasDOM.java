package repaso.accesoADatos.utiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import repaso.accesoADatos.modelo.Ingrediente;
import repaso.accesoADatos.modelo.Receta;

public class RecetasDOM {
	 public final static String rutaResources = "src/main/resources/";

	    // Leer recetas desde un XML
	    public List<Receta> leerDesdeXML(String nombreArchivo) throws Exception {
	        List<Receta> lista = new ArrayList<Receta>();
	        Document doc = getDocumentFromXML(nombreArchivo);

	        NodeList nodosReceta = doc.getElementsByTagName("Receta");

	        for (int i = 0; i < nodosReceta.getLength(); i++) {
	            Node nodo = nodosReceta.item(i);
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Element elemento = (Element) nodo;
	                Receta receta = getFromElement(elemento);
	                lista.add(receta);
	            }
	        }
	        return lista;
	    }

	    // Convierte un elemento XML en un objeto Receta
	    public Receta getFromElement(Element elemento) {
	        String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
	        String procedimiento = elemento.getElementsByTagName("procedimiento").item(0).getTextContent();
	        String tiempo = elemento.getElementsByTagName("tiempo").item(0).getTextContent();

	        List<Ingrediente> ingredientes = new ArrayList<>();
	        NodeList nodosIngredientes = elemento.getElementsByTagName("ingrediente");
	        for (int i = 0; i < nodosIngredientes.getLength(); i++) {
	            Element ing = (Element) nodosIngredientes.item(i);
	            String nombre = ing.getTextContent();
	            String cantidad = ing.getAttribute("cantidad");
	            ingredientes.add(new Ingrediente(nombre, cantidad));
	        }

	        return new Receta(titulo, ingredientes, procedimiento, tiempo);
	    }

	    // Crear Document desde XML
	    public Document getDocumentFromXML(String nombreArchivo) throws Exception {
	        File file = new File(rutaResources + nombreArchivo);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(file);
	        doc.getDocumentElement().normalize();
	        return doc;
	    }

	    // Escribir lista de recetas a XML
	    public void escribirEnXML(String nombreArchivo, List<Receta> recetas) {
	        try {
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.newDocument();

	            // Nodo raíz
	            Element root = doc.createElement("Recetas");
	            doc.appendChild(root);

	            for (Receta r : recetas) {
	                Element recetaElem = doc.createElement("Receta");
	                root.appendChild(recetaElem);

	                Element titulo = doc.createElement("titulo");
	                titulo.setTextContent(r.getTitulo());
	                recetaElem.appendChild(titulo);

	                Element ingredientesElem = doc.createElement("ingredientes");
	                recetaElem.appendChild(ingredientesElem);

	                for (Ingrediente ing : r.getIngredientes()) {
	                    Element ingElem = doc.createElement("ingrediente");
	                    ingElem.setAttribute("cantidad", ing.getCantidad());
	                    ingElem.setTextContent(ing.getNombre());
	                    ingredientesElem.appendChild(ingElem);
	                }

	                Element procedimiento = doc.createElement("procedimiento");
	                procedimiento.setTextContent(r.getProcedimiento());
	                recetaElem.appendChild(procedimiento);

	                Element tiempo = doc.createElement("tiempo");
	                tiempo.setTextContent(r.getTiempo());
	                recetaElem.appendChild(tiempo);
	            }

	            // Transformar a archivo
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(rutaResources + nombreArchivo));
	            transformer.transform(source, result);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

		

}
