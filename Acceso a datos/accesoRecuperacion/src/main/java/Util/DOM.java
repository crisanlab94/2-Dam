package Util;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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

import modeloExamen.Enfrentamiento;
import modeloExamen.EquipoExamen;
import modeloExamen.Videojuego;
import repositorioExamen.EquipoRepositorio;

public class DOM {
	//  Logger para trazabilidad y depuración
    private static final Logger logger = LogManager.getLogger(DOM.class);

    // Ruta base donde se guardan los archivos XML
    public final static String rutaResources = "src/main/resources/";

    //Paso 1.getDocumentFromXML transformar el arfivo fisico en un objeto
    // 📥 Carga un archivo XML y devuelve su objeto Document
    private Document getDocumentFromXML(String nombrefichero) {
        File file = new File(rutaResources + nombrefichero);
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
    // hacemos uno x etiqueta (ejemplo equipo y enfrentamiento)
    // etiqueta en singular
    // llama a getEquipoFromElement o el que sea
    public List<EquipoExamen> leerEquiposDesdeXML(String nombreArchivo) throws Exception {
        List<EquipoExamen> lista = new ArrayList<>();
        Document doc = getDocumentFromXML(nombreArchivo);

        NodeList nodos = doc.getElementsByTagName("equipo"); // Etiqueta que se repite y quieres usar

        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nodo;
                // tu clase(en este caso Equipo) llamamos al getLoQueSea
                EquipoExamen obj = getEquipoFromElement(elemento);
                lista.add(obj);
            }
        }
        return lista;
    }

    //Paso 3
    // Convertir un Element en un objeto Modelo
    // miramos directamente dentro de cada etiqueta (equipo) ejemplo nombre, email...
    // .trim() al final de cada atributo
    public EquipoExamen getEquipoFromElement(Element elemento) {
        // Cambiar los nombres de etiquetas según tu XML
        // mira de que tipo es cada una
        String codigo = elemento.getAttribute("codigo").trim(); // Es un atributo
        String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent().trim();
        String email = elemento.getElementsByTagName("email").item(0).getTextContent().trim();
        int numJugadores = Integer.parseInt(elemento.getElementsByTagName("numJugadores").item(0).getTextContent().trim());

        // Crear objeto (cambiar Modelo por tu clase concreta)
        //Tiene que seguir el orden del constructor
        return new EquipoExamen( codigo,nombre, email,numJugadores);
    }

    // --- Lectura de Enfrentamientos ---
    public List<Enfrentamiento> leerEnfrentamientosDesdeXML(String nombreArchivo, EquipoRepositorio repoEq) throws Exception {
        List<Enfrentamiento> lista = new ArrayList<>();
        Document doc = getDocumentFromXML(nombreArchivo);

        NodeList nodos = doc.getElementsByTagName("enfrentamiento"); // Etiqueta en singular

        for (int i = 0; i < nodos.getLength(); i++) {
            Element elemento = (Element) nodos.item(i);
            // Llamamos al método pasándole el repositorio para buscar el equipo real
            Enfrentamiento enf = getEnfrentamientoFromElement(elemento, repoEq);
            lista.add(enf);
        }
        return lista;
    }
    
    

    public Enfrentamiento getEnfrentamientoFromElement(Element elemento, EquipoRepositorio repoEq) {
    	int id = Integer.parseInt(elemento.getAttribute("id").trim());
        String fecha = elemento.getElementsByTagName("fecha").item(0).getTextContent().trim();
        String desc = elemento.getElementsByTagName("descripcion").item(0).getTextContent().trim();
        
        // Convertimos el String del XML al ENUM Videojuego
        String vjTexto = elemento.getElementsByTagName("videojuego").item(0).getTextContent().trim();
        Videojuego videoJuego = Videojuego.valueOf(vjTexto.toUpperCase());

        // Buscamos el objeto EQUIPO real usando el codigoRef (Para que no sea String)
        Element ganadorTag = (Element) elemento.getElementsByTagName("ganador").item(0);
        String codigoRef = ganadorTag.getAttribute("codigoRef").trim();
        EquipoExamen equipoGanador = repoEq.obtenerEquipoPorCodigo(codigoRef);

        
     // ✅ VALIDACIÓN DE SEGURIDAD (Para evitar el Error Crítico: null)
        if (equipoGanador == null) {
            logger.warn("Cuidado: El equipo con código " + codigoRef + " no existe en el repositorio. Usando equipo genérico.");
            // Opcional: Crear un equipo "Desconocido" para que no pete
            equipoGanador = new EquipoExamen("Desconocido", codigoRef, "n/a",0);
        }
        return new Enfrentamiento(id, fecha, desc, videoJuego, equipoGanador);
    }

    //Paso 4. Escribir lista de objetos en XML
    public void escribirTorneoXML(String nombreArchivo, List<EquipoExamen> listaEquipos, List<Enfrentamiento> listaEnfrentamientos) throws Exception {
        // 1. CONFIGURACIÓN INICIAL (Crear el lienzo vacío)
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument(); // Creamos un documento nuevo en memoria

        // 2. CREACIÓN DE LA RAÍZ PRINCIPAL
        // <torneoData>
        Element raiz = doc.createElement("torneoData");
        doc.appendChild(raiz);

        // --- BLOQUE DE EQUIPOS ---
        // 3. Crear el contenedor <listaEquipos> y colgarlo de la raíz
        Element contenedorEq = doc.createElement("listaEquipos");
        raiz.appendChild(contenedorEq);

        for (EquipoExamen eq : listaEquipos) {
            // <equipo codigo="G2">
            Element eEquipo = doc.createElement("equipo");
            eEquipo.setAttribute("codigo", eq.getCodigo());
            contenedorEq.appendChild(eEquipo);

            // <nombre>Texto</nombre>
            Element nom = doc.createElement("nombre");
            nom.appendChild(doc.createTextNode(eq.getNombre()));
            eEquipo.appendChild(nom);

            // <email>Texto</email>
            Element mail = doc.createElement("email");
            mail.appendChild(doc.createTextNode(eq.getEmail()));
            eEquipo.appendChild(mail);

            // <numJugadores>Número</numJugadores>
            Element num = doc.createElement("numJugadores");
            num.appendChild(doc.createTextNode(String.valueOf(eq.getNumJugadores())));
            eEquipo.appendChild(num);
        }

        // --- BLOQUE DE ENFRENTAMIENTOS ---
        Element contenedorEnf = doc.createElement("listaEnfrentamientos");
        raiz.appendChild(contenedorEnf);

        for (Enfrentamiento enf : listaEnfrentamientos) {
            Element eEnf = doc.createElement("enfrentamiento");
            eEnf.setAttribute("id", String.valueOf(enf.getId()));
            contenedorEnf.appendChild(eEnf);

            // <ganador codigoRef="..."/>
            Element ganador = doc.createElement("ganador");
            // Sacamos el código desde el objeto equipo que tiene el enfrentamiento
            ganador.setAttribute("codigoRef", enf.getEquipoGanador().getCodigo());
            eEnf.appendChild(ganador);
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
    
 // Generar JSON
    
    public void generarJson(List<Enfrentamiento> lista, String nombreArchivo) {
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
        try (java.io.FileWriter writer = new java.io.FileWriter(rutaResources + nombreArchivo)) {
            gson.toJson(lista, writer);
            logger.info("Archivo JSON generado con éxito: {}", nombreArchivo);
        } catch (Exception e) {
            logger.error("Error al generar el JSON: {}", e.getMessage());
        }
    }
    
    //Generar CSV
    
    public void generarCSV(List<Enfrentamiento> lista, String nombreArchivo) {
        String ruta = rutaResources + nombreArchivo;

        // Usamos try-with-resources para asegurar que el archivo se cierra solo
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            
            // 1. Escribimos la cabecera (opcional, pero profesional)
            pw.println("ID;FECHA;VIDEOJUEGO;GANADOR;CONTACTO");

            // 2. Recorremos los datos
            for (Enfrentamiento enf : lista) {
                pw.println(String.format("%d;%s;%s;%s;%s", 
                    enf.getId(),
                    enf.getFecha(),
                    enf.getVideojuego(),
                    enf.getEquipoGanador().getNombre(), // Demostramos la relación de objetos
                    enf.getEquipoGanador().getEmail()
                ));
            }
            
            logger.info(" CSV generado correctamente en: {}", nombreArchivo);

        } catch (Exception e) {
            logger.error("❌ Error al crear el CSV: {}", e.getMessage());
        }
    }

}
