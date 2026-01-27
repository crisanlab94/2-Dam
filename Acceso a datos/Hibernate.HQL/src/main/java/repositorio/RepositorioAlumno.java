package repositorio;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import controlador.GestionaHQL;
import jakarta.persistence.TypedQuery;
import modelo.Alumno;
import util.AbstractDao;
import util.HibernateUtil;

public class RepositorioAlumno extends AbstractDao <Alumno> {
	private static final Logger logger = LogManager.getLogger(RepositorioAlumno.class);

	
		public RepositorioAlumno() {
			setClase(Alumno.class);
		} 

		
		//Obtener todos los alumnos (select *)
		public List<Alumno> todosLosAlumno() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		String queryString = "FROM "+ Alumno.class.getName();
		
		TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		List<Alumno> listaAlumnos = query.getResultList();
		return listaAlumnos;
		}

		//Obtener el nombre y email de todos los alumnos.
		public List<Alumno> getNombreYEmail() {
			Session sesion = HibernateUtil.getFactoriaSession().openSession();
			String queryString = "SELECT nombre, email FROM "+ Alumno.class.getName();	
			
			TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);

			List<Alumno> resultadosNombreEmail= query.getResultList();
			return resultadosNombreEmail;
			
		}
}
