package dao;

import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

import modelos.Evento;

import utiles.AbstractDao;
import utiles.HibernateUtil;


public class EventoDao extends AbstractDao<Evento> {

	public EventoDao() {
		setClase(Evento.class);
	}
	
	public List<Evento> getEventoMasMinutos() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        
   
        String queryString = " FROM " + Evento.class.getName() + " a WHERE a.duracion > 91";
        
        TypedQuery<Evento> query = sesion.createQuery(queryString, Evento.class);
        List<Evento> listaEventosMas = query.getResultList();
        return listaEventosMas;
    }
 
	
	public List<Evento> getEventoUbicaion(String nombreUbicaion) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        // Navegamos por el objeto: a.curso.nivel
        String queryString = "FROM " + Evento.class.getName() + " a WHERE a.ubicacion.nombre = :nombreUbicacion";
        TypedQuery<Evento> query = sesion.createQuery(queryString, Evento.class);
        query.setParameter("nombreUbicacion", nombreUbicaion);
        return query.getResultList();
    }
	
	
	
	
}