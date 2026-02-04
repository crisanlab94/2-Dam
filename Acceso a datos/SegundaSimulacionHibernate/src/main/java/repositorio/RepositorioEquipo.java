package repositorio;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import modelo.Equipo;
import util.AbstractDao;
import util.HibernateUtil;

public class RepositorioEquipo extends AbstractDao<Equipo> {

	public RepositorioEquipo() {
		setClase(Equipo.class);
	}
	
	//Fase final
	public List<Equipo> buscarDosMejores() {
	    Session session = HibernateUtil.getFactoriaSession().openSession();
	    try {
	    	// Añadimos "LEFT JOIN FETCH e.fases" para que traiga la lista aunque la sesión se cierre
	        String hql = "SELECT DISTINCT e FROM Equipo e LEFT JOIN FETCH e.fases ORDER BY e.puntosAcumulados DESC";
	        Query<Equipo> query = session.createQuery(hql, Equipo.class);
	        
	        // Limitamos el resultado a solo 2 registros
	        query.setMaxResults(2);
	        
	        return query.list();
	    } finally {
	        session.close();
	    }
	}

}
