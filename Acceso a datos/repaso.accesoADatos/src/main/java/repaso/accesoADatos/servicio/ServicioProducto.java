package repaso.accesoADatos.servicio;

import java.util.ArrayList;
import java.util.List;

import repaso.accesoADatos.modelo.Producto;
import repaso.accesoADatos.repositorio.RepositorioProducto;

public class ServicioProducto {

	 private RepositorioProducto repositorio;

	    public ServicioProducto() {
	        repositorio = new RepositorioProducto(); 
	    }

	    //Devuelve los productos cuyo stock es menor que la cantidad 
	     
	    public List<Producto> productosConStockMenorA(int cantidad) {
	        List<Producto> resultado = new ArrayList<Producto>();
	        for (Producto p : repositorio.leerLista()) {
	            if (p.getStock() < cantidad) {
	                resultado.add(p);
	            }
	        }
	        return resultado;
	    }

	    //Retira de venta los productos que tengan stock menor a 5
	     
	    public void retiraDeVentaProductos(List<Producto> productos) {
	        for (Producto p : productos) {
	            if (p.getStock() < 5) {
	                p.setEnVenta(false);
	            }
	        }
	    }

	    
	    public void agregarProducto(Producto p) {
	        repositorio.agregarProducto(p);
	    }

	   
	     
	    public List<Producto> getTodos() {
	        return repositorio.leerLista();
	    }
	}



