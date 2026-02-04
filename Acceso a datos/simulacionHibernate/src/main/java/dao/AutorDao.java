package dao;

import java.util.List;

import org.hibernate.Session;

import modelos.Autor;
import modelos.Revista;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class AutorDao extends AbstractDao<Autor> {

	public AutorDao() {
		setClase(Autor.class);
	}

	//Autores que han publicado en una revista concreta 
		public List<Autor> getAutoresPorRevista(String nombreRevista) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    try {
		        // Usamos DISTINCT para que si un autor tiene 2 artículos en la misma revista, no salga repetido
		        String hql = "SELECT DISTINCT au FROM Autor au " +
		                     "JOIN au.articulos ar " +
		                     "WHERE ar.revista.nombreRevista = :nomRevista";
		        return sesion.createQuery(hql, Autor.class)
		                     .setParameter("nomRevista", nombreRevista)
		                     .getResultList();
		    } finally {
		        sesion.close();
		    }
		}
		
		//Ranking autores por articulos
		public List<Object[]> getRankingAutores() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    try {
		        // Agrupamos por el nombre del autor y contamos sus artículos
		        String hql = "SELECT au.nombre, COUNT(ar) FROM Autor au " +
		                     "LEFT JOIN au.articulos ar " +
		                     "GROUP BY au.nombre " +
		                     "ORDER BY COUNT(ar) DESC";
		        return sesion.createQuery(hql, Object[].class).getResultList();
		    } finally {
		        sesion.close();
		    }
		}
		
}