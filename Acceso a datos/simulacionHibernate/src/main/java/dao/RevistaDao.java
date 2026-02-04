package dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import modelos.Revista;
import utiles.AbstractDao;
import utiles.HibernateUtil;

public class RevistaDao extends AbstractDao<Revista> {

	public RevistaDao() {
		setClase(Revista.class);
	}
	
	// Número de artículos por revista
	public List<Object[]> getRecuentoArticulosPorRevista() {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    try {
	        // Usamos LEFT JOIN para que salgan revistas aunque no tengan artículos todavía
	        String hql = "SELECT r.nombreRevista, COUNT(a) FROM Revista r " +
	                     "LEFT JOIN r.listaArticulos a GROUP BY r.nombreRevista"; 
	        return sesion.createQuery(hql, Object[].class).getResultList();
	    } finally {
	        sesion.close();
	    }
	}

	// Revistas publicadas antes de una fecha específica
	public List<Object[]> getRevistasPreviasAFecha(LocalDate fechaLimite) {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    try {
	        String hql = "SELECT r.nombreRevista, r.fecha, r.numeroRevista FROM Revista r " +
	                     "WHERE r.fecha < :limite"; 
	        return sesion.createQuery(hql, Object[].class)
	                     .setParameter("limite", fechaLimite)
	                     .getResultList();
	    } finally {
	        sesion.close();
	    }
	}
	
	//Revistas que tienen autores con gmail
	//Se buscan revistan donde algun articulo tengo autor con gmal
	public List<Revista> getRevistasPorDominioEmail(String dominio) {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    try {
	        // Buscamos revistas donde alguno de sus artículos tenga un autor con cierto email
	        String hql = "SELECT DISTINCT r FROM Revista r " +
	                     "JOIN r.listaArticulos a " +
	                     "JOIN a.autores au " +
	                     "WHERE au.email LIKE :correo";
	        return sesion.createQuery(hql, Revista.class)
	                     .setParameter("correo", "%" + dominio + "%")
	                     .getResultList();
	    } finally {
	        sesion.close();
	    }
	}

	
	//Revista con mas de x autores diferentes
	public List<Object[]> getRevistasConMuchosAutores(long minimoAutores) {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    try {
	        String hql = "SELECT r.nombreRevista, COUNT(DISTINCT au) FROM Revista r " +
	                     "JOIN r.listaArticulos a " +
	                     "JOIN a.autores au " +
	                     "GROUP BY r.nombreRevista " +
	                     "HAVING COUNT(DISTINCT au) >= :min";
	        return sesion.createQuery(hql, Object[].class)
	                     .setParameter("min", minimoAutores)
	                     .getResultList();
	    } finally {
	        sesion.close();
	    }
	}
}
