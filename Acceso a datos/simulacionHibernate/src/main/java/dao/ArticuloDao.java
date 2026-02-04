package dao;



import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;
import modelos.Articulo;
import utiles.AbstractDao;
import utiles.HibernateUtil;


public class ArticuloDao extends AbstractDao<Articulo> {

	public ArticuloDao() {
		setClase(Articulo.class);
	}
	
	//Diseña y codifica una consulta que devuelva, ordenada alfabéticamente por nombre de artículos, 
	//los artículos de un autor del que sabemos su nombre

	public List<Articulo> getArticulosPorNombreAutor(String nombreAutor) {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    List<Articulo> articulos = null;

	    try {
	        // Diseñamos la consulta HQL
	        String hql = "SELECT a FROM Articulo a " +
	                     "JOIN a.autores au " +
	                     "WHERE au.nombre = :nombre " +
	                     "ORDER BY a.titulo ASC";

	        // Creamos la consulta tipada
	        TypedQuery<Articulo> query = sesion.createQuery(hql, Articulo.class);
	        
	        // Asignamos el parámetro del nombre del autor
	        query.setParameter("nombre", nombreAutor);

	        // Obtenemos la lista de resultados
	        articulos = query.getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        sesion.close();
	    }

	    return articulos;
	}
	
	
	// Artículos con más de 6 páginas incluyendo datos de la revista
	// Devolvemos List<Object[]> porque pedimos campos específicos de distintas tablas
	public List<Object[]> getArticulosMasDe6Paginas() {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    try {
	        String hql = "SELECT a.titulo, (a.numPagFin - a.numPaginaInicio) " +
	                     "FROM Articulo a " +
	                     "WHERE (a.numPagFin - a.numPaginaInicio) > 6";
	        return sesion.createQuery(hql, Object[].class).getResultList();
	    } finally {
	        sesion.close();
	    }
	}
	
	//como el anterior y  además, el nombre de la revista y la fecha de publicación.
	public List<Object[]> getArticulosMasDe6PaginasConRevista() {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    try {
	        String hql = "SELECT a.titulo, (a.numPagFin - a.numPaginaInicio), " +
	                     "a.revista.nombreRevista, a.revista.fecha " +
	                     "FROM Articulo a " +
	                     "WHERE (a.numPagFin - a.numPaginaInicio) > 6";
	        return sesion.createQuery(hql, Object[].class).getResultList();
	    } finally {
	        sesion.close();
	    }
	}
	
	
	
}