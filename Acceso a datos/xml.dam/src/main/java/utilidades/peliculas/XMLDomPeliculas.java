package utilidades.peliculas;



import java.io.File;

import java.util.ArrayList;

import java.util.List;



import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;

import javax.xml.transform.TransformerFactory;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;



import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.w3c.dom.DOMImplementation;

import org.w3c.dom.Document;

import org.w3c.dom.Element;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

import org.w3c.dom.Text;

import modelo.peliculas.Pelicula;
import xml.dam.modelo.Empleado;

public class XMLDomPeliculas {

	public final static String rutaResources = "src/main/resources/";

	private static final Logger logger = LogManager.getLogger(XMLDomPeliculas.class);



	public static void main(String[] args) {

		logger.info("hello");

	}



	/*

	 * public List<Empleado> getEmpleadosFromXML(String nombreFichero, String

	 * nombreRaiz) { List<Empleado> modelos = new ArrayList<Empleado>(); Document

	 * documento = getDocumentFromXML(nombreFichero); NodeList nodoRaiz =

	 * documento.getElementsByTagName(nombreRaiz); Node raiz = nodoRaiz.item(0); //

	 * obtengo el nodo raíz if (raiz.getNodeType() == Node.ELEMENT_NODE) { Element

	 * elemento = (Element) raiz; if (elemento.hasChildNodes()) { // cada hijo es un

	 * empleado NodeList nodosHijos = elemento.getChildNodes(); for (int j = 0; j <

	 * nodosHijos.getLength(); j++) { Node modeloNodo = nodosHijos.item(j); if

	 * (modeloNodo.getNodeType() == Node.ELEMENT_NODE) { T e =

	 * this.getModeloFromElement((Element) modeloNodo); modelos.add(e); } } } }

	 * return modelos; }

	 */



	public List<Pelicula> leerPelissDesdeXML(String rutaFichero) throws Exception {

		List<Pelicula> listaPeliculas = new ArrayList<Pelicula>();

		// 1. Calcula el dom

		Document doc = getDocumentFromXML(rutaFichero);

		// 2. Obtener todos los nodos con etiqueta empleados

		NodeList nodosPeliculas = doc.getElementsByTagName("Pelicula");



		// 3. Recorro la lista de los nodos empleado

		for (int j = 0; j < nodosPeliculas.getLength(); j++) {

			Node modeloNodo = nodosPeliculas.item(j);

			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {

				Pelicula e = this.getPeliculasFromElement((Element) modeloNodo);

				listaPeliculas.add(e);

			}

		}



		return listaPeliculas;

	}



	private List<String> leerActoresDesdeElemento(Element elemento) {

		List<String> listaActores = new ArrayList<String>();



		// 1. Obtener todos los nodos con etiqueta Actor dentro del elemento Pelicula

		NodeList nodosActores = elemento.getElementsByTagName("Actor");



		// 2. Recorro la lista de los nodos Actor

		for (int j = 0; j < nodosActores.getLength(); j++) {

			Node modeloNodo = nodosActores.item(j);

			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {

				Element hijo = (Element) modeloNodo;

				listaActores.add(hijo.getTextContent());

			}

		}



		return listaActores;

	}



	// Único método a modificar

	private Pelicula getPeliculasFromElement(Element elemento) {

		Pelicula e = new Pelicula();

		List<String> actoresLista = leerActoresDesdeElemento(elemento);



		String titulo = elemento.getElementsByTagName("Titulo").item(0).getTextContent();

		int anyo = Integer.parseInt(elemento.getElementsByTagName("Fecha").item(0).getTextContent());

		String director = elemento.getElementsByTagName("Director").item(0).getTextContent();



		e.setTitulo(titulo);

		e.setAnyo(anyo);

		e.setDirector(director);

		e.setListaActores(actoresLista);



		return e;

	}



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



	// Único método a modificar

	public Pelicula leerPeliculaDesdeXML(String rutaFichero) throws Exception {

		Document doc = getDocumentFromXML(rutaFichero);

		Element elementoEmpleado = doc.getDocumentElement();

		return getPeliculasFromElement(elementoEmpleado);

	}



	private Document construyoObjetoDocumento(String nombreRaiz) throws ParserConfigurationException {

		Document documento = null;

		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factoria.newDocumentBuilder();

		DOMImplementation implementacion = builder.getDOMImplementation();

		documento = implementacion.createDocument(null, nombreRaiz, null);

		// Primer parámetro uri: si null no está validado por ninguna ruta

		// segundo parámetro: nombre fichero

		// tercer parámetro: document type Por defecto null

		return documento;

	}



	private Element creaElemento(String nombreElemento, String valorElemento, Element padre, Document documento) {

		Element elemento = documento.createElement(nombreElemento);

		Text texto = documento.createTextNode(valorElemento);

		padre.appendChild(elemento);// Se lo asigno a su padre como Hijo

		elemento.appendChild(texto);// Cargo el elemento con el valor

		return elemento;

	}



	private void agregaEmpleadoADocumento(Document documento, Element padre, Empleado e) {

		// Para cada una de los atributos de persona, creo un elemento hijo

		Element nombre = this.creaElemento("nombreApellido", e.getNombreApellido(), padre, documento);

		Element edad = this.creaElemento("edad", Integer.toString(e.getEdad()), padre, documento);

		Element empresa = this.creaElemento("empresa", e.getEmpresa(), padre, documento);

		// El identificador lo vamos a crear como un atributo de la etiqueta empleado

		padre.setAttribute("identificador", e.getIdentificador());

	}



	public void escribeEmpleadoEnXML(String nombreFichero, Empleado e) {

		try {

			Document documento = this.construyoObjetoDocumento("empleado");

			// Recupero la raíz del documento

			Element raiz = documento.getDocumentElement();

			agregaEmpleadoADocumento(documento, raiz, e);

			escribeDocumentoEnFichero(documento, nombreFichero);

		} catch (ParserConfigurationException e1) {

			logger.error(e1.getMessage());

		} catch (TransformerException e1) {

			logger.error(e1.getMessage());

		}

	}



	private void escribeDocumentoEnFichero(Document documento, String nombreFichero) throws TransformerException {

		// clases necesarias finalizar la creación del archivo XML

		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		Transformer transformer;

		transformer = transformerFactory.newTransformer();

		DOMSource source = new DOMSource(documento);

		StreamResult resultado = new StreamResult(new File(this.rutaResources + nombreFichero));

		transformer.transform(source, resultado);

	}



}