package repaso.accesoADatos.utiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import repaso.accesoADatos.modelo.Pelicula;

public class PeliculasDOMXml {
	 // Ruta base donde se encuentran los recursos XML
    public final static String rutaResources = "src/main/resources/";

    // Logger para mostrar información o errores
    private static final Logger logger = LogManager.getLogger(PeliculasDOMXml.class);

    // Constructor vacío
    public PeliculasDOMXml() {
    }

    // Método principal para probar la lectura del XML
    public static void main(String[] args) {
        logger.info("Prueba de lectura de XML");
    }

    /*
     * Método que lee todas las películas desde un archivo XML
     * Recibe la ruta del fichero y devuelve una lista de objetos Pelicula
     */
    public List<Pelicula> leerPeliculasDesdeXML(String rutaFichero) throws Exception {
        List<Pelicula> listaPeliculas = new ArrayList<>();

        // 1. Obtener el Document del XML
        Document doc = getDocumentFromXML(rutaFichero);

        // 2. Obtener todos los nodos <Pelicula>
        NodeList nodosPeliculas = doc.getElementsByTagName("Pelicula");

        // 3. Recorrer cada nodo <Pelicula> y crear objeto Pelicula
        for (int i = 0; i < nodosPeliculas.getLength(); i++) {
            Node nodo = nodosPeliculas.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Pelicula p = getPeliculasFromElement((Element) nodo);
                listaPeliculas.add(p);
            }
        }

        // 4. Devolver la lista completa
        return listaPeliculas;
    }

    /*
     * Método auxiliar para leer los actores de una película
     * Recibe el elemento <Pelicula> y devuelve la lista de nombres de actores
     */
    private List<String> leerActoresDesdeElemento(Element elemento) {
        List<String> actores = new ArrayList<>();

        // Obtener todos los nodos <Actor> dentro de la película
        NodeList nodosActores = elemento.getElementsByTagName("Actor");

        // Recorrer cada actor y agregarlo a la lista
        for (int i = 0; i < nodosActores.getLength(); i++) {
            Node nodo = nodosActores.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                actores.add(nodo.getTextContent());
            }
        }

        return actores;
    }

    /*
     * Método que transforma un Element <Pelicula> en un objeto Pelicula
     * Solo obtiene título, año, director y lista de actores
     */
    private Pelicula getPeliculasFromElement(Element elemento) {
        Pelicula p = new Pelicula();

        // Leer título
        String titulo = elemento.getElementsByTagName("Titulo").item(0).getTextContent();

        // Leer año y convertir a int
        int anyo = Integer.parseInt(elemento.getElementsByTagName("Fecha").item(0).getTextContent());

        // Leer director
        String director = elemento.getElementsByTagName("Director").item(0).getTextContent();

        // Leer lista de actores
        List<String> actores = leerActoresDesdeElemento(elemento);

        // Asignar valores al objeto Pelicula
        p.setTitulo(titulo);
        p.setFecha(anyo);
        p.setDirector(director);
        p.setActores(actores);

        return p;
    }

    /*
     * Método que obtiene un Document desde un archivo XML
     * Devuelve null si ocurre algún error
     */
    private Document getDocumentFromXML(String nombreFichero) {
        File file = new File(rutaResources + nombreFichero);
        Document documento = null;

        try {
            // Crear DocumentBuilder para parsear XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parsear el archivo
            documento = dBuilder.parse(file);

            // Normalizar el documento (importante para nodos de texto)
            documento.getDocumentElement().normalize();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return documento;
    }

    /*
     * Método opcional para leer solo una película desde XML
     * (por si el XML tuviera un único nodo <Pelicula>)
     */
    public Pelicula leerPeliculaDesdeXML(String rutaFichero) throws Exception {
        Document doc = getDocumentFromXML(rutaFichero);
        Element elementoPelicula = doc.getDocumentElement();
        return getPeliculasFromElement(elementoPelicula);
    }
}