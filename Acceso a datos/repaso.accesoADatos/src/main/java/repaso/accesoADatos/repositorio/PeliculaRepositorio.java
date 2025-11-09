package repaso.accesoADatos.repositorio;

import java.util.ArrayList;
import java.util.List;

import repaso.accesoADatos.modelo.Pelicula;

public class PeliculaRepositorio {
	
	 private List<Pelicula> listaPeliculas = new ArrayList<>();

	   

	    public void agregarPelicula(Pelicula pelicula) {
	        listaPeliculas.add(pelicula);
	    }

	    public List<Pelicula> obtenerTodas() {
	        return listaPeliculas;
	    }

	    // Actualiza una película según su índice
	    public boolean actualizarPelicula(int indice, Pelicula pelicula) {
	        boolean actualizado = false;
	        if (indice >= 0 && indice < listaPeliculas.size()) {
	            listaPeliculas.set(indice, pelicula);
	            actualizado = true;
	        }
	        return actualizado;  
	    }

	    // Elimina una película según su índice
	    public boolean eliminarPelicula(int indice) {
	        boolean eliminado = false;
	        if (indice >= 0 && indice < listaPeliculas.size()) {
	            listaPeliculas.remove(indice);
	            eliminado = true;
	        }
	        return eliminado;  
	    }

}
