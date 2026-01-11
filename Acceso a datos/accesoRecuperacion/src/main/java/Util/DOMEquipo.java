package Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import modeloExamen.Equipo;
import modeloExamen.Piloto;



public class DOMEquipo {
    public final static String rutaResources = "src/main/resources/";

    // 1. LEER EQUIPOS (PASO 1 DEL MAIN)
    public List<Equipo> leerEquiposDesdeXML(String nombreArchivo) throws Exception {
        List<Equipo> lista = new ArrayList<>();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(rutaResources + nombreArchivo));
        
        NodeList nodos = doc.getElementsByTagName("equipo");
        for (int i = 0; i < nodos.getLength(); i++) {
            Element el = (Element) nodos.item(i);
            // El enunciado dice que inicialmente no tienen pilotos, el constructor de Equipo ya crea la lista vacía
            lista.add(new Equipo(
                el.getAttribute("identificadorEquipo").trim(),
                el.getElementsByTagName("nombreEquipo").item(0).getTextContent().trim(),
                Integer.parseInt(el.getElementsByTagName("puntos").item(0).getTextContent().trim())
            ));
        }
        return lista;
    }

    // 2. LEER PILOTOS (PASO 2 DEL MAIN)
    public List<Piloto> leerPilotosDesdeXML(String nombreArchivo) throws Exception {
        List<Piloto> lista = new ArrayList<>();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(rutaResources + nombreArchivo));
        
        NodeList nodos = doc.getElementsByTagName("piloto");
        for (int i = 0; i < nodos.getLength(); i++) {
            Element el = (Element) nodos.item(i);
            lista.add(new Piloto(
                el.getAttribute("identificadorPiloto").trim(),
                el.getElementsByTagName("nombrePiloto").item(0).getTextContent().trim(),
                Integer.parseInt(el.getElementsByTagName("puntos").item(0).getTextContent().trim()),
                el.getElementsByTagName("identificadorEquipo").item(0).getTextContent().trim(),
                el.getElementsByTagName("pais").item(0).getTextContent().trim()
            ));
        }
        return lista;
    }

    // 3. ESCRIBIR RESULTADO (PARA COMPROBAR EL PASO 3)
    public void escribirFormula1XML(String nombreArchivo, List<Equipo> listaEquipos) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element raiz = doc.createElement("clasificacionGeneral");
        doc.appendChild(raiz);

        Element contenedorEq = doc.createElement("equipos");
        raiz.appendChild(contenedorEq);

        for (Equipo eq : listaEquipos) {
            Element eEquipo = doc.createElement("equipo");
            eEquipo.setAttribute("identificadorEquipo", eq.getIdentificadorEquipo());
            contenedorEq.appendChild(eEquipo);

            Element nom = doc.createElement("nombreEquipo");
            nom.appendChild(doc.createTextNode(eq.getNombreEquipo()));
            eEquipo.appendChild(nom);

            Element puntosEq = doc.createElement("puntosEquipo");
            puntosEq.appendChild(doc.createTextNode(String.valueOf(eq.getPuntos())));
            eEquipo.appendChild(puntosEq);

            // --- AQUÍ SE DEMUESTRA QUE EL PASO 3 FUNCIONÓ ---
            if (!eq.getListaPilotos().isEmpty()) {
                Element contenedorPilotos = doc.createElement("pilotos");
                eEquipo.appendChild(contenedorPilotos);

                for (Piloto p : eq.getListaPilotos()) {
                    Element ePiloto = doc.createElement("piloto");
                    ePiloto.setAttribute("id", p.getIdentificadorPiloto());
                    contenedorPilotos.appendChild(ePiloto);

                    Element nomP = doc.createElement("nombre");
                    nomP.appendChild(doc.createTextNode(p.getNombrePiloto()));
                    ePiloto.appendChild(nomP);
                }
            }
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(doc), new StreamResult(new File(rutaResources + nombreArchivo)));
    }
    
    
    public void escribirFormula1XMLpaso5(String nombreArchivo, List<Equipo> listaEquipos) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        
        // Raíz: <equipos>
        Element raiz = doc.createElement("equipos");
        doc.appendChild(raiz);

        for (Equipo eq : listaEquipos) {
            // <equipo identificadorEquipo="11000179">
            Element eEquipo = doc.createElement("equipo");
            eEquipo.setAttribute("identificadorEquipo", eq.getIdentificadorEquipo());
            raiz.appendChild(eEquipo);

            // <nombreEquipo>MERCEDES AMG F1</nombreEquipo>
            Element nomEq = doc.createElement("nombreEquipo");
            nomEq.appendChild(doc.createTextNode(eq.getNombreEquipo()));
            eEquipo.appendChild(nomEq);

            // <puntos>199</puntos>
            Element puntosEq = doc.createElement("puntos");
            puntosEq.appendChild(doc.createTextNode(String.valueOf(eq.getPuntos())));
            eEquipo.appendChild(puntosEq);

            // <pilotos>
            Element contenedorPilotos = doc.createElement("pilotos");
            eEquipo.appendChild(contenedorPilotos);

            for (Piloto p : eq.getListaPilotos()) {
                // <piloto identificadorPiloto="11000822">
                Element ePiloto = doc.createElement("piloto");
                ePiloto.setAttribute("identificadorPiloto", p.getIdentificadorPiloto());
                contenedorPilotos.appendChild(ePiloto);

                // <nombrePiloto>Lewis Hamilton</nombrePiloto>
                Element nomP = doc.createElement("nombrePiloto");
                nomP.appendChild(doc.createTextNode(p.getNombrePiloto()));
                ePiloto.appendChild(nomP);

                // <puntos>1</puntos>
                Element puntosP = doc.createElement("puntos");
                puntosP.appendChild(doc.createTextNode(String.valueOf(p.getPuntos())));
                ePiloto.appendChild(puntosP);

                // <identificadorEquipo>11000179</identificadorEquipo>
                Element idEqP = doc.createElement("identificadorEquipo");
                idEqP.appendChild(doc.createTextNode(p.getIdentificadorEquipo()));
                ePiloto.appendChild(idEqP);

                // <pais>GBR</pais>
                Element paisP = doc.createElement("pais");
                paisP.appendChild(doc.createTextNode(p.getPais()));
                ePiloto.appendChild(paisP);
            }
        }

        // Configuración del Transformer para que el archivo sea idéntico al ejemplo
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(rutaResources + nombreArchivo));
        transformer.transform(source, result);
    }
    
}