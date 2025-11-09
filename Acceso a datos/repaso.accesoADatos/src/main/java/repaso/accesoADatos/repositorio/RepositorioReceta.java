package repaso.accesoADatos.repositorio;

import java.util.ArrayList;
import java.util.List;

import repaso.accesoADatos.modelo.Receta;

public class RepositorioReceta {
	 private List<Receta> listaRecetas;

	    public RepositorioReceta() {
	        this.listaRecetas = new ArrayList<Receta>();
	    }

	    // Devuelve todas las recetas
	    public List<Receta> leerLista() {
	        return listaRecetas;
	    }

	    // Agrega una nueva receta
	    public void agregarReceta(Receta receta) {
	        listaRecetas.add(receta);
	    }

	    // Elimina una receta
	    public void eliminarReceta(Receta receta) {
	        listaRecetas.remove(receta);
	    }

	   //Actualizar receta 
	    public void actualizarReceta(Receta recetaVieja, Receta recetaNueva) {
	        for (int i = 0; i < listaRecetas.size(); i++) {
	            Receta actual = listaRecetas.get(i);
	            if (actual.getTitulo().equals(recetaVieja.getTitulo()) &&
	                actual.getProcedimiento().equals(recetaVieja.getProcedimiento()) &&
	                actual.getTiempo().equals(recetaVieja.getTiempo())) {
	                
	                listaRecetas.set(i, recetaNueva);
	            }
	        }
	    }

		

}
