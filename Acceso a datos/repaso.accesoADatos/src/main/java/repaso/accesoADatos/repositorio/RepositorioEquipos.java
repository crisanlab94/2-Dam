package repaso.accesoADatos.repositorio;

import java.util.ArrayList;
import java.util.List;

import repaso.accesoADatos.modelo.Equipo;



public class RepositorioEquipos {
	 private List<Equipo> listaEquipos;

	 public RepositorioEquipos(List<Equipo> listaEquipos) {
		super();
		this.listaEquipos = new ArrayList<Equipo>();
	 }
	 
	    public List<Equipo> leerLista() {
	        return listaEquipos;
	    }

	    public void agregarProducto(Equipo e) {
	        listaEquipos.add(e);
	    }

	    public void eliminarProducto(Equipo e) {
	        listaEquipos.remove(e);
	    }

	    public void actualizarProductoPorId(int id, Equipo eqNuevo) {
	        for (int i = 0; i < listaEquipos.size(); i++) {
	            if (listaEquipos.get(i).getIdentificadorEquipo() == id) {
	                listaEquipos.set(i, eqNuevo);
	            }
	        }
	    }
	 

}
