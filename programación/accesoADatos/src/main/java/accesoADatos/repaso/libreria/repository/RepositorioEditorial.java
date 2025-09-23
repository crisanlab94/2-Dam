package accesoADatos.repaso.libreria.repository;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import accesoADatos.repaso.libreria.models.Editorial;
import accesoADatos.repaso.libreria.models.Libro;
import accesoADatos.repaso.libreria.models.LibroException;


public class RepositorioEditorial {
	private Set<Editorial> RepositorioEditorial;

	public RepositorioEditorial(Set<Editorial> repositorioEditorial) {
		super();
		RepositorioEditorial = new TreeSet<Editorial>();
	}

	public Set<Editorial> getRepositorioEditorial() {
		return RepositorioEditorial;
	}

	public void setRepositorioEditorial(Set<Editorial> repositorioEditorial) {
		RepositorioEditorial = repositorioEditorial;
	}
	
	void agregarEditorial(Editorial e) {
		RepositorioEditorial.add(e);
		
	}
	
	void actualizarEditorial(String cif) throws LibroException {
		Editorial editorialBuscada = this.consultarEditorial(cif);
		 if (editorialBuscada== null) 
		    {
		    	throw new LibroException("La editorial a actualizar no existe");
		    
		    }
		    RepositorioEditorial.remove(editorialBuscada);
	
	}

	
	
	Editorial consultarEditorial(String cif) {

		Iterator<Editorial> iterador = RepositorioEditorial.iterator();

		boolean encontrado = false;

		Editorial resultado = null;

		while (iterador.hasNext() && !encontrado) {

			Editorial editorial = iterador.next();

			if (editorial.getCif().equals(cif)) {

				resultado = editorial;

				encontrado = true;

			}

		}

		return resultado;

	}

	
	void eliminarEditorial(String cif) throws LibroException {
		  Editorial e = this.consultarEditorial(cif);
		    if (e == null) {
		    	throw new LibroException("La editorial a borrar no existe");
		        
		    }
		    RepositorioEditorial.remove(e);
		}

	
}
