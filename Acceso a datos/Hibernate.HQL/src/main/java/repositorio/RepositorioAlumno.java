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
		//Obtener los alumnos mayores de 18 años.
		
		//Obtener los alumnos cuyo nombre sea "Ana".
		
		//Obtener los alumnos ordenados por edad de forma descendente.
		
		//Obtener los alumnos con una edad mayor que un valor pasado por parámetro.
		//Obtener el alumno cuyo email se pasa como parámetro.
		//Obtener los alumnos cuyo nombre contenga una cadena introducida por el usuario. 
		//Obtener todos los alumnos del curso llamado "DAM".
		//Obtener el nombre de los alumnos y el nombre de su curso. 
		//Obtener los cursos que tengan al menos un alumno.
		//Obtener los alumnos cuyo curso sea de nivel "Superior".
		//Contar el número total de alumnos.
		//Obtener la edad media de los alumnos.
		//Obtener el número de alumnos por curso.
		//Obtener el curso con más alumnos.
		//Obtener los alumnos que no estén asignados a ningún curso. 
		//Obtener los cursos que no tengan alumnos. 
		//Obtener los alumnos cuya edad sea mayor que la edad media. 
		//Obtener los nombres de los cursos que tengan alumnos mayores de 25 años.
		//¿Por qué en HQL no se usan nombres de tablas?
		//¿Qué ventaja tiene HQL frente a SQL en una aplicación Java?


}
