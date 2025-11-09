package accesoADatos.plantilla;

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

/**
 * DOM genérico para leer y escribir XML
 * Adaptar etiquetas y clase Modelo según tu proyecto
 */
public class DOM {

    public final static String rutaResources = "src/main/resources/";

    // Leer lista de objetos desde XML
    public List<Modelo> leerDesdeXML(String nombreArchivo) throws Exception {
        List<Modelo> lista = new ArrayList<>();
        Document doc = getDocumentFromXML(nombreArchivo);

        NodeList nodos = doc.getElementsByTagName("objeto"); // Cambiar "objeto" según XML

        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nodo;
                Modelo obj = getFromElement(elemento);
                lista.add(obj);
            }
        }

        return lista;
    }

    // Convertir un Element en un objeto Modelo
    public Modelo getFromElement(Element elemento) {
        // Cambiar los nombres de etiquetas según tu XML
        String atributo1 = elemento.getElementsByTagName("atributo1").item(0).getTextContent();
        int atributo2 = Integer.parseInt(elemento.getElementsByTagName("atributo2").item(0).getTextContent());

        List<String> listaAtributos = new ArrayList<>();
        NodeList nodosLista = elemento.getElementsByTagName("listaAtributos"); 
        for (int j = 0; j < nodosLista.getLength(); j++) {
            listaAtributos.add(nodosLista.item(j).getTextContent());
        }

        // Crear objeto (cambiar Modelo por tu clase concreta)
        return new Modelo(atributo1, atributo2, listaAtributos);
    }

    // Obtener Document desde XML
    public Document getDocumentFromXML(String nombreArchivo) throws Exception {
        File archivo = new File(rutaResources + nombreArchivo);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(archivo);
        doc.getDocumentElement().normalize();
        return doc;
    }

    // Escribir lista de objetos en XML
    public void escribirEnXML(String nombreArchivo, List<Modelo> lista) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        // Raíz del XML
        Element raiz = doc.createElement("objetos"); // Cambiar nombre raíz
        doc.appendChild(raiz);

        for (Modelo obj : lista) {
            Element elemento = doc.createElement("objeto"); // Cambiar etiqueta objeto

            Element a1 = doc.createElement("atributo1");
            a1.appendChild(doc.createTextNode(obj.getAtributo1()));
            elemento.appendChild(a1);

            Element a2 = doc.createElement("atributo2");
            a2.appendChild(doc.createTextNode(String.valueOf(obj.getAtributo2())));
            elemento.appendChild(a2);

            for (String valor : obj.getListaAtributos()) {
                Element item = doc.createElement("listaAtributos"); // Cambiar nombre etiqueta lista
                item.appendChild(doc.createTextNode(valor));
                elemento.appendChild(item);
            }

            raiz.appendChild(elemento);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(rutaResources + nombreArchivo));
        transformer.transform(source, result);
    }
}
