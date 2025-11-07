package repositorio.stock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.stock.Producto;

public class RepositorioProducto {
	List<Producto> listaProducto;

	public RepositorioProducto(List<Producto> listaProducto) {
		super();
		this.listaProducto = new ArrayList<Producto>();
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}
	
	
	public void agregarProducto(Producto p) {
		Iterator<Producto> iterador= this.getListaProducto().iterator();
		
		
	}
	public void buscarProducto(Producto p) {
		
	}
	public void actualizarProducto(int id,Producto p) {
		
	}

	public void borrarProducto(Producto p) {
		
	}
}
