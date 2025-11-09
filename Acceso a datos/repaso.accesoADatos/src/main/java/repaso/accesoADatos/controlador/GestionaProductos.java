package repaso.accesoADatos.controlador;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repaso.accesoADatos.modelo.Producto;
import repaso.accesoADatos.servicio.ServicioProducto;
import repaso.accesoADatos.utiles.UtilJSON;
import repaso.accesoADatos.utiles.XMLDomProductos;

public class GestionaProductos {
	 
		 private static final Logger logger = LogManager.getLogger(GestionaProductos.class);

		    public static void main(String[] args) {
		    try {

	        ServicioProducto servicio = new ServicioProducto();
	        XMLDomProductos xmlDom = new XMLDomProductos();
	        UtilJSON json = new UtilJSON();
	        
	       


	        // Leer productos desde XML
	        logger.info("Leyendo productos desde XML...");
	        List<Producto> lista = xmlDom.leerDesdeXML("Productos.xml");

	        // Agregar productos al repositorio
	        logger.info("Agregando productos al repositorio...");
	        for (Producto p : lista) {
	            servicio.agregarProducto(p);
	            logger.info("Producto agregado: {}", p.getNombre());
	        }

	        // Filtrar productos con stock < 5
	        logger.info("Filtrando productos con stock < 7...");
	        List<Producto> stockBajo = servicio.productosConStockMenorA(7);

	        // Retirar de venta productos con stock < 5
	        logger.info("Retirando de venta productos con stock < 5...");
	        servicio.retiraDeVentaProductos(stockBajo);

	        // Generar JSON de productos NO en venta
	        logger.info("Generando JSON de productos NO en venta...");
	        json.escribirEnJSON("src/main/resources/productosNOenVenta.json", stockBajo);
	     // Generar el mismo archivo pero en formato XML
	        logger.info("Generando XML de productos NO en venta...");
	        json.escribirEnXML("src/main/resources/productosNOenVenta.xml", stockBajo);

            logger.info("Proceso completado correctamente.");

        } catch (Exception e) {
            logger.error("Ocurrió un error: ", e);
        }
    }

	        
}


