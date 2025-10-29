package xml.dam;

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

import org.w3c.dom.Text;



public class XMLDomEmpleados {
	
	public final static String rutaResources = "src/main/resources/";

	private static final Logger logger = LogManager.getLogger(XMLDomEmpleados.class);



	public static void main(String[] args) {

		logger.info("hello");

	}

/*

	public List<Empleado> getEmpleadosFromXML(String nombreFichero, String nombreRaiz) {

		List<Empleado> modelos = new ArrayList<Empleado>();

			Document documento = getDocumentFromXML(nombreFichero);

			NodeList nodoRaiz = documento.getElementsByTagName(nombreRaiz);

			Node raiz = nodoRaiz.item(0); // obtengo el nodo raíz

			if (raiz.getNodeType() == Node.ELEMENT_NODE) {

				Element elemento = (Element) raiz;

				if (elemento.hasChildNodes()) { // cada hijo es un empleado

					NodeList nodosHijos = elemento.getChildNodes();

					for (int j = 0; j < nodosHijos.getLength(); j++) {

						Node modeloNodo = nodosHijos.item(j);

						if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {

							T e = this.getModeloFromElement((Element) modeloNodo);

							modelos.add(e);

						}	}	}			}

		return modelos;  }

*/

	

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



	public Empleado leerEmpleadoDesdeXML(String rutaFichero) throws Exception {

		Document doc = getDocumentFromXML(rutaFichero);

		Element elementoEmpleado = doc.getDocumentElement();

		return getEmpleadoFromElement(elementoEmpleado);

	}



	private Empleado getEmpleadoFromElement(Element elemento) {

		Empleado e = new Empleado();

		String nombre = elemento.getElementsByTagName("nombreApellido").item(0).getTextContent();

		int edad = Integer.parseInt(elemento.getElementsByTagName("edad").item(0).getTextContent());

		String empresa = elemento.getElementsByTagName("empresa").item(0).getTextContent();

		String id = elemento.getAttribute("identificador"); // La etiqueta empleado tiene el atributo identificador

		e.setEdad(edad);

		e.setNombreApellido(nombre);

		e.setIdentificador(id);

		e.setEmpresa(empresa);

		return e;

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

