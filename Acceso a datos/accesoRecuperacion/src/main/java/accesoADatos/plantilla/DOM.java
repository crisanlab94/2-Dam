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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utiles.DomFormula1;

/**
 * DOM genérico para leer y escribir XML
 * Adaptar etiquetas y clase Modelo según  proyecto
 */
public class DOM {

	 // 🎯 Logger para trazabilidad y depuración
    private static final Logger logger = LogManager.getLogger(DOM.class);

    // 📁 Ruta base donde se guardan los archivos XML
    public final static String rutaResources = "src/main/resources/";

    //Paso 1.getDocumentFromXML transformar el arfivo fisico en un objeto
    // 📥 Carga un archivo XML y devuelve su objeto Document
    private Document getDocumentFromXML(String nombrefichero) {
        File file = new File("src/main/resources/" + nombrefichero);
        Document documento = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            documento = dBuilder.parse(file); // 🧱 Parseo del XML
            documento.getDocumentElement().normalize();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return documento;
    }

    //Paso 2. leerDesdeXML
    // Leer lista de objetos desde XML
   // hacemos uno x etiqueta(ejemplo piloto y equipo
    	    //etiqueta en singular
    //llama a getPilotoFromElement o el que sea
    public List<Modelo> leerDesdeXML(String nombreArchivo) throws Exception {
        List<Modelo> lista = new ArrayList<>();
        Document doc = getDocumentFromXML(nombreArchivo);

        NodeList nodos = doc.getElementsByTagName("objeto"); // Etiqueta que se repite y quieres usar

        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nodo;
                // tu clase(en este caso Modelo) llamamos al getLoQueSea
                Modelo obj = getFromElement(elemento);
                lista.add(obj);
            }
        }

        return lista;
    }

    //Paso 3
    // Convertir un Element en un objeto Modelo
    //miramos directamente dentro de cada etiqueta (piloto,equipo)ejemplo nombrePiloto,puntos...
    // .trim() al final de cada atributo
    public Modelo getFromElement(Element elemento) {
        // Cambiar los nombres de etiquetas según tu XML
    	//mira de que tipo es cada una
        String atributo1 = elemento.getElementsByTagName("atributo1").item(0).getTextContent().trim();
        int atributo2 = Integer.parseInt(elemento.getElementsByTagName("atributo2").item(0).getTextContent().trim());

        /*Esto solo si la misma etiqueta se repite 2 o mas veces 
         ejemplo patrocinador,patrocinador
        List<String> listaAtributos = new ArrayList<>();
        NodeList nodosLista = elemento.getElementsByTagName("listaAtributos"); 
        for (int j = 0; j < nodosLista.getLength(); j++) {
            listaAtributos.add(nodosLista.item(j).getTextContent());
        }
        
        //Si hay algun valor vacio
        ///String pais = "Desconocido"; // Valor por defecto
		NodeList nPais = elemento.getElementsByTagName("pais");
		
		if (nPais.getLength() > 0) {
		    pais = nPais.item(0).getTextContent().trim();
		    }

    int puntos = Integer.parseInt(elemento.getElementsByTagName("puntos").item(0).getTextContent().trim());

    return new Equipo(nombre, puntos);
}

		*/
        // Crear objeto (cambiar Modelo por tu clase concreta)
        return new Modelo(atributo1, atributo2, listaAtributos);
    }
    
    

    // Escribir lista de objetos en XML
    public void escribirClasificacionXML(String nombreArchivo, List<Piloto> listaPilotos, List<Equipo> listaEquipos) throws Exception {
        // 1. CONFIGURACIÓN INICIAL (Crear el lienzo vacío)
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument(); // Creamos un documento nuevo en memoria

        // 2. CREACIÓN DE LA RAÍZ PRINCIPAL
        // <clasificaciónGeneral>
        Element raiz = doc.createElement("clasificaciónGeneral");
        doc.appendChild(raiz);

        // --- BLOQUE DE PILOTOS ---
        // 3. Crear el contenedor <pilotos> y colgarlo de la raíz
        Element contenedorPilotos = doc.createElement("pilotos");
        raiz.appendChild(contenedorPilotos);

        for (Piloto p : listaPilotos) {
            // <piloto>
            Element ePiloto = doc.createElement("piloto");
            contenedorPilotos.appendChild(ePiloto);

            // <nombrePiloto>Texto</nombrePiloto>
            Element nombre = doc.createElement("nombrePiloto");
            nombre.appendChild(doc.createTextNode(p.getNombre()));
            ePiloto.appendChild(nombre);

            // <puntos>Número</puntos> (Convertimos int a String con String.valueOf)
            Element puntos = doc.createElement("puntos");
            puntos.appendChild(doc.createTextNode(String.valueOf(p.getPuntos())));
            ePiloto.appendChild(puntos);

            // <pais>Texto</pais>
            Element pais = doc.createElement("pais");
            pais.appendChild(doc.createTextNode(p.getPais()));
            ePiloto.appendChild(pais);
        }

        // --- BLOQUE DE EQUIPOS ---
        // 4. Crear el contenedor <equipos> y colgarlo de la raíz
        Element contenedorEquipos = doc.createElement("equipos");
        raiz.appendChild(contenedorEquipos);

        for (Equipo eq : listaEquipos) {
            // <equipo>
            Element eEquipo = doc.createElement("equipo");
            contenedorEquipos.appendChild(eEquipo);

            // <nombreEquipo>
            Element nEq = doc.createElement("nombreEquipo");
            nEq.appendChild(doc.createTextNode(eq.getNombre()));
            eEquipo.appendChild(nEq);

            // <puntos>
            Element pEq = doc.createElement("puntos");
            pEq.appendChild(doc.createTextNode(String.valueOf(eq.getPuntos())));
            eEquipo.appendChild(pEq);
        }

        // 5. EL TRANSFORMER (Pasar de la memoria RAM al archivo físico .xml)
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        // Configuración para que el XML no salga en una sola línea (Indentación)
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(rutaResources + nombreArchivo));
        
        // Aquí es donde se guarda el archivo realmente
        transformer.transform(source, result);
    }
}
