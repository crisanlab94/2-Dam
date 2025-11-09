package repaso.accesoADatos.repositorio;

import java.util.ArrayList;
import java.util.List;

import repaso.accesoADatos.modelo.Producto;

public class RepositorioProducto {
	   private List<Producto> listaProductos;

	    public RepositorioProducto() {
	        this.listaProductos = new ArrayList<Producto>();
	    }

	    public List<Producto> leerLista() {
	        return listaProductos;
	    }

	    public void agregarProducto(Producto p) {
	        listaProductos.add(p);
	    }

	    public void eliminarProducto(Producto p) {
	        listaProductos.remove(p);
	    }

	    public void actualizarProductoPorId(int id, Producto pNuevo) {
	        for (int i = 0; i < listaProductos.size(); i++) {
	            if (listaProductos.get(i).getId() == id) {
	                listaProductos.set(i, pNuevo);
	            }
	        }
	    }
}
