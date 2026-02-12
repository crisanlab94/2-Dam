package dao;



import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.TypedQuery;

import modelos.Participante;

import utiles.AbstractDao;
import utiles.HibernateUtil;


public class ParticipanteDao extends AbstractDao<Participante> {

	public ParticipanteDao() {
		setClase(Participante.class);
	}
	
	public List<Participante> getNombreYEmail() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		  String queryString = " FROM " + Participante.class.getName() +
                  
	                         "ORDER BY a.apellido ASC, a.nombre ASC";
		
		TypedQuery<Participante> query = sesion.createQuery(queryString, Participante.class);

		List<Participante> resultadosNombreApellidos= query.getResultList();
		return resultadosNombreApellidos;
		
	}
	
	

	

	

}