package repaso.accesoADatos.utiles;


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

import repaso.accesoADatos.modelo.Producto;





public class DOMProductosDani {

	private static final Logger logger = LogManager.getLogger(DOMProductosDani.class);

	public final static String rutaResources = "src/main/resources/";



	//



	public List<Producto> leerProductosDesdeXML(String rutaFichero) throws Exception {

		List<Producto> productos = new ArrayList<Producto>();

		// 1. Calcula el dom 

		Document doc = getDocumentFromXML(rutaFichero);

		// 2. Obtener todos los nodos con etiqueta empleados

		NodeList nodoProductos = doc.getElementsByTagName("producto");

		// 3. Recorro la lista de los nodos empleado

		for (int j = 0; j < nodoProductos.getLength(); j++) {

			Node modeloNodo = nodoProductos.item(j);

			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {

				Producto e = this.getProductoFromXML((Element) modeloNodo);

				productos.add(e);

			} 

		}

		return productos;

	}

 

	private void agregaProductoAdocumento(Document documento, Element padre, Producto e) {

		// Para cada una de los atributos de persona, creo un elemento hijo

		Element producto = documento.createElement("producto");

		producto.setAttribute("id", e.getId());

		producto.setAttribute("enVenta", String.valueOf(e.isEnVenta()));

		creaElemento("Nombre", e.getNombre(), producto, documento);

		creaElemento("Precio", Double.toString(e.getPrecio()), producto, documento);

		creaElemento("Stock", Integer.toString(e.getStock()), producto, documento);

		padre.appendChild(producto);

	}



	private Producto getProductoFromXML(Element elemento) {

		String id = elemento.getAttribute("id");

		boolean enVenta = Boolean.parseBoolean(elemento.getAttribute("enVenta"));

		String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();

		double precio = Double.parseDouble(elemento.getElementsByTagName("Precio").item(0).getTextContent());

		int stock = Integer.parseInt(elemento.getElementsByTagName("Stock").item(0).getTextContent());

		return new Producto();

	}



	//



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

	

	

	public void escribeProductosXML(List<Producto> productos, String nombrefichero) {

	    try { 

	        // 1. Crear documento con raíz <ListaProductos>

	        Document documento = construyoObjetoDocumento("ListaProductos");

	        Element raiz = documento.getDocumentElement();



	        // 2. Agregar cada producto como hijo del nodo raíz

	        for (Producto producto : productos) {

	            agregaProductoAdocumento(documento, raiz, producto);

	        }



	        // 3. Escribir el documento en fichero

	        escribeDocumentoEnFichero(documento, nombrefichero);

	        logger.info("📤 XML generado correctamente: " + nombrefichero);



	    } catch (ParserConfigurationException | TransformerException e) {

	        logger.error("❌ Error al escribir productos en XML: " + e.getMessage(), e);

	    }

	}





	private void escribeDocumentoEnFichero(Document documento, String nombreFichero) throws TransformerException {

		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		Transformer transformer = transformerFactory.newTransformer();

		DOMSource source = new DOMSource(documento);

		StreamResult resultado = new StreamResult(new File(rutaResources + nombreFichero));

		transformer.transform(source, resultado);

	}

}

