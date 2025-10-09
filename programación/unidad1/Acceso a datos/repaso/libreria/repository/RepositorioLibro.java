package accesoADatos.repaso.libreria.repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import accesoADatos.repaso.libreria.models.Libro;
import accesoADatos.repaso.libreria.models.LibroException;

public class RepositorioLibro {

	private Set<Libro> RepositorioLibro;

	public RepositorioLibro() {
		super();
		RepositorioLibro = new HashSet<Libro>();
	}

	public Set<Libro> getRepositorioLibro() {
		return RepositorioLibro;
	}

	public void setRepositorioLibro(Set<Libro> repositorioLibro) {
		RepositorioLibro = repositorioLibro;
	}
	
	void agregarLibro(Libro l) {
		RepositorioLibro.add(l);
		
	}
	
	void actualizarLibro(Libro libro) {
		
		Libro l = this.consultarLibro(libro.getId());
		if (l !=  null) {
			RepositorioLibro.remove(l);
			RepositorioLibro.add(libro);
		} 
		}

	
	
	Libro consultarLibro(String id) {

		Iterator<Libro> iterador = RepositorioLibro.iterator();

		boolean encontrado = false;

		Libro resultado = null;

		while (iterador.hasNext() && !encontrado) {

			Libro libro = iterador.next();

			if (libro.getId().equals(id)) {

				resultado = libro;

				encontrado = true;

			}

		}

		return resultado;

	}

	

	void eliminarLibro(String id) throws LibroException {
	    Libro libroBuscado = this.consultarLibro(id);
	    if (libroBuscado== null) 
	    {
	    	throw new LibroException("El libro a borrar no existe");
	    
	    }
	    RepositorioLibro.remove(libroBuscado);
	}
	}

