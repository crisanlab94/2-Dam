package repositorio;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;


import jakarta.persistence.TypedQuery;
import modelo.Alumno;
import modelo.Curso;
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
		
		public List<Alumno> getMayores18() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.edad > 18";
	        
	        TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
	        List<Alumno> listaMayores = query.getResultList();
	        return listaMayores;
	    }
		//Obtener los alumnos cuyo nombre sea "Ana".
		
		public List<Alumno> getAlumnosAna() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.nombre = 'Ana'";
	        
	        TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
	        List<Alumno> listaAna = query.getResultList();
	        return listaAna;
	    }
		
		//Obtener los alumnos ordenados por edad de forma descendente.
		public List<Alumno> getAlumnosOrdenadosEdad() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "FROM " + Alumno.class.getName() + " a ORDER BY a.edad DESC";
	        
	        TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
	        List<Alumno> listaOrdenada = query.getResultList();
	        return listaOrdenada;
	    }
		
		//Obtener los alumnos con una edad mayor que un valor pasado por parámetro.
		public List<Alumno> getAlumnosMayoresQue(int edadLimite) {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.edad > :edadParam";
	        
	        TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
	        query.setParameter("edadParam", edadLimite);
	        
	        List<Alumno> listaFiltrada = query.getResultList();
	        return listaFiltrada;
	    }
		//Obtener el alumno cuyo email se pasa como parámetro.
		public Alumno getAlumnoPorEmail(String emailBusqueda) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.email = :emailParam";
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    query.setParameter("emailParam", emailBusqueda);

		    // En lugar de getSingleResult, usamos una lista y comprobamos
		    List<Alumno> resultados = query.getResultList();
		    return resultados.isEmpty() ? null : resultados.get(0);
		}
		
		//Obtener los alumnos cuyo nombre contenga una cadena introducida por el usuario. 
		public List<Alumno> getAlumnosNombreContiene(String cadena) {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.nombre LIKE :patron";
	        
	        TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
	        query.setParameter("patron", "%" + cadena + "%");
	        
	        List<Alumno> listaResultados = query.getResultList();
	        return listaResultados;
	    }
		
		//Obtener todos los alumnos del curso llamado "DAM".
		
		public List<Alumno> getAlumnosCursoDAM() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.curso.nombre = 'DAM'";
	        
	        TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
	        List<Alumno> listaDAM = query.getResultList();
	        return listaDAM;
	    }
		
		//Obtener el nombre de los alumnos y el nombre de su curso. 
		public List<Object[]> getNombreAlumnoYCurso() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "SELECT a.nombre, a.curso.nombre FROM " + Alumno.class.getName() + " a";
	        
	        TypedQuery<Object[]> query = sesion.createQuery(queryString, Object[].class);
	        List<Object[]> resultados = query.getResultList();
	        return resultados;
	    }
		
		//Obtener los cursos que tengan al menos un alumno.
		public List<Curso> getCursosConAlumnos() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        // Usamos IS NOT EMPTY para verificar la colección de alumnos en la clase Curso
	        String queryString = "SELECT c FROM " + Curso.class.getName() + " c WHERE c.alumnos IS NOT EMPTY";
	        
	        TypedQuery<Curso> query = sesion.createQuery(queryString, Curso.class);
	        List<Curso> listaCursos = query.getResultList();
	        return listaCursos;
	    }
		
		//Obtener los alumnos cuyo curso sea de nivel "Superior".
		public List<Alumno> getAlumnosNivelSuperior() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        // Navegamos por el objeto: a.curso.nivel
	        String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.curso.nivel = 'Superior'";
	        
	        TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
	        List<Alumno> listaSuperior = query.getResultList();
	        return listaSuperior;
	    }
		
		//Contar el número total de alumnos.
		public Long contarAlumnos() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "SELECT COUNT(a) FROM " + Alumno.class.getName() + " a";
	        
	        TypedQuery<Long> query = sesion.createQuery(queryString, Long.class);
	        Long total = query.getSingleResult();
	        return total;
	    }
		//Obtener la edad media de los alumnos.
		public Double getEdadMedia() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "SELECT AVG(a.edad) FROM " + Alumno.class.getName() + " a";
	        
	        TypedQuery<Double> query = sesion.createQuery(queryString, Double.class);
	        Double media = query.getSingleResult();
	        return media;
	    }
		//Obtener el número de alumnos por curso.
		public List<Object[]> getNumeroAlumnosPorCurso() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        // SELECT nombre del curso y COUNT de alumnos, agrupando por curso
	        String queryString = "SELECT a.curso.nombre, COUNT(a) FROM " + Alumno.class.getName() + " a GROUP BY a.curso.nombre";
	        
	        TypedQuery<Object[]> query = sesion.createQuery(queryString, Object[].class);
	        List<Object[]> resultados = query.getResultList();
	        return resultados;
	    }
		//Obtener el curso con más alumnos.
		public Curso getCursoConMasAlumnos() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        // Ordenamos por el tamaño (SIZE) de la lista de alumnos
	        String queryString = "SELECT c FROM " + Curso.class.getName() + " c ORDER BY SIZE(c.alumnos) DESC";
	        
	        TypedQuery<Curso> query = sesion.createQuery(queryString, Curso.class);
	        query.setMaxResults(1); // Para quedarnos solo con el primero (el que más tiene)
	        
	        Curso cursoMasPoblado = query.getSingleResult();
	        return cursoMasPoblado;
	    }
		
		//Obtener los alumnos que no estén asignados a ningún curso. 
		public List<Alumno> getAlumnosSinCurso() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.curso IS NULL";
	        
	        TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
	        List<Alumno> listaAlumnos = query.getResultList();
	        return listaAlumnos;
	    }
		//Obtener los cursos que no tengan alumnos. 
		public List<Curso> getCursosSinAlumnos() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String queryString = "SELECT c FROM " + Curso.class.getName() + " c WHERE c.alumnos IS EMPTY";
	        
	        TypedQuery<Curso> query = sesion.createQuery(queryString, Curso.class);
	        List<Curso> listaCursosVacios = query.getResultList();
	        return listaCursosVacios;
	    }
		//Obtener los alumnos cuya edad sea mayor que la edad media.
		public List<Alumno> getAlumnosEdadMayorMedia() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        // Subconsulta: comparamos edad con el resultado de otra query que saca el AVG
	        String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.edad > (SELECT AVG(a2.edad) FROM " + Alumno.class.getName() + " a2)";
	        
	        TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
	        List<Alumno> listaSobreMedia = query.getResultList();
	        return listaSobreMedia;
	    }
		//Obtener los nombres de los cursos que tengan alumnos mayores de 25 años.
		public List<String> getNombresCursosMayores25() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        // SELECT DISTINCT para no repetir el nombre del curso si hay varios alumnos de >25 en el mismo
	        String queryString = "SELECT DISTINCT a.curso.nombre FROM " + Alumno.class.getName() + " a WHERE a.edad > 25";
	        
	        TypedQuery<String> query = sesion.createQuery(queryString, String.class);
	        List<String> nombresCursos = query.getResultList();
	        return nombresCursos;
	    }
		//¿Por qué en HQL no se usan nombres de tablas?
		/*Porque HQL es un lenguaje orientado a objetos que interactúa con las entidades de Java, no con el esquema físico.*/
		//¿Qué ventaja tiene HQL frente a SQL en una aplicación Java?
		/*La principal ventaja es que es independiente del motor de base de datos  y que recuperas directamente objetos Java, evitando tener que mapear los datos a mano desde un ResultSet.*/

		
		// Obtener la edad del alumno más viejo.
		public Integer getEdadMaxima() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "SELECT MAX(a.edad) FROM " + Alumno.class.getName() + " a";
		    
		    TypedQuery<Integer> query = sesion.createQuery(queryString, Integer.class);
		    return query.getSingleResult();
		}
		
		// Obtener el alumno más joven (el objeto completo).
		public Alumno getAlumnoMasJoven() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // Usamos una subconsulta para encontrar al que tiene la edad mínima
		    String queryString = "FROM " + Alumno.class.getName() + " a WHERE a.edad = (SELECT MIN(a2.edad) FROM " + Alumno.class.getName() + " a2)";
		    
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    List<Alumno> resultados = query.getResultList();
		    return resultados.isEmpty() ? null : resultados.get(0);
		}
		
		// Obtener todos los alumnos cargando su curso de forma inmediata (Eager).
		public List<Alumno> getAlumnosConCursosOptimizados() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // El FETCH le dice a Hibernate: "trae los datos del curso ahora mismo"
		    String queryString = "SELECT a FROM " + Alumno.class.getName() + " a JOIN FETCH a.curso";
		    
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    return query.getResultList();
		}
		
		// Subir un año de edad a todos los alumnos de un curso específico.
		public int incrementarEdadCurso(String nombreCurso) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    sesion.beginTransaction();
		    
		    String hqlUpdate = "UPDATE " + Alumno.class.getName() + " a SET a.edad = a.edad + 1 WHERE a.curso.nombre = :nombreCurso";
		    
		    int filasAfectadas = sesion.createMutationQuery(hqlUpdate)
		                               .setParameter("nombreCurso", nombreCurso)
		                               .executeUpdate();
		                               
		    sesion.getTransaction().commit();
		    sesion.close();
		    return filasAfectadas;
		}

		// Borrar todos los alumnos que no tengan email asignado.
		public int borrarAlumnosSinEmail() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    sesion.beginTransaction();
		    
		    String hqlDelete = "DELETE FROM " + Alumno.class.getName() + " a WHERE a.email IS NULL";
		    
		    int filasBorradas = sesion.createMutationQuery(hqlDelete)
		                              .executeUpdate();
		                              
		    sesion.getTransaction().commit();
		    sesion.close();
		    return filasBorradas;
		}
		
		// Obtener alumnos de un curso concreto (por nombre) con edad entre min y max, ordenados por nombre ASC.
		public List<Alumno> getAlumnosCursoEdadRango(String nombreCurso, int edadMin, int edadMax) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // Combinamos AND, BETWEEN y ORDER BY
		    String queryString = "FROM " + Alumno.class.getName() + " a " +
		                         "WHERE a.curso.nombre = :cursoParam " +
		                         "AND a.edad BETWEEN :minParam AND :maxParam " +
		                         "ORDER BY a.nombre ASC";
		    
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    query.setParameter("cursoParam", nombreCurso);
		    query.setParameter("minParam", edadMin);
		    query.setParameter("maxParam", edadMax);
		    
		    return query.getResultList();
		}
		
		// Alumnos con email que contiene una cadena y nivel de curso específico, ordenados por edad DESC.
		public List<Alumno> getAlumnosEmailYNivel(String patronEmail, String nivel) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // Usamos el punto (.) para navegar al nivel del curso: a.curso.nivel
		    String queryString = "FROM " + Alumno.class.getName() + " a " +
		                         "WHERE a.email LIKE :emailParam " +
		                         "AND a.curso.nivel = :nivelParam " +
		                         "ORDER BY a.edad DESC";
		    
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    query.setParameter("emailParam", "%" + patronEmail + "%");
		    query.setParameter("nivelParam", nivel);
		    
		    return query.getResultList();
		}
		
		// Obtener SOLO nombre y edad de alumnos que pertenezcan a una lista de cursos.
		public List<Object[]> getNombreEdadDeVariosCursos(List<String> nombresCursos) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // Usamos SELECT para campos específicos y el operador IN para la lista
		    String queryString = "SELECT a.nombre, a.edad FROM " + Alumno.class.getName() + " a " +
		                         "WHERE a.curso.nombre IN (:listaCursos) " +
		                         "ORDER BY a.curso.nombre ASC, a.nombre ASC";
		    
		    TypedQuery<Object[]> query = sesion.createQuery(queryString, Object[].class);
		    query.setParameter("listaCursos", nombresCursos);
		    
		    return query.getResultList();
		}
		
		// Alumnos con curso asignado, que no tengan un nombre prohibido y sean mayores de edad.
		public List<Alumno> getAlumnosFiltradoEspecial(String nombreProhibido, int edadMinima) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // Combinamos IS NOT NULL con el operador NOT EQUAL (!= o <>)
		    String queryString = "FROM " + Alumno.class.getName() + " a " +
		                         "WHERE a.curso IS NOT NULL " +
		                         "AND a.nombre <> :nombreParam " +
		                         "AND a.edad >= :edadParam " +
		                         "ORDER BY a.edad ASC";
		    
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    query.setParameter("nombreParam", nombreProhibido);
		    query.setParameter("edadParam", edadMinima);
		    
		    return query.getResultList();
		}
		
		// Obtener los 3 alumnos más jóvenes de un curso concreto.
		public List<Alumno> getTop3JovenesPorCurso(String nombreCurso) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "FROM " + Alumno.class.getName() + " a " +
		                         "WHERE a.curso.nombre = :cursoParam " +
		                         "ORDER BY a.edad ASC";
		    
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    query.setParameter("cursoParam", nombreCurso);
		    query.setMaxResults(3); // Solo los 3 primeros
		    
		    return query.getResultList();
		}
		
		// Obtener todos los alumnos y sus cursos en una sola consulta (Eager Loading)
		public List<Alumno> getAlumnosConCursosFetch() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // JOIN FETCH le indica a Hibernate que traiga los datos del objeto Curso de golpe
		    String queryString = "SELECT a FROM " + Alumno.class.getName() + " a JOIN FETCH a.curso";
		    
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    return query.getResultList();
		}
		
		// Obtener los cursos donde un alumno específico es miembro de la lista de alumnos
		public List<Curso> getCursosDondeEstaElAlumno(Alumno alumno) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "SELECT c FROM " + Curso.class.getName() + " c WHERE :alumnoParam MEMBER OF c.alumnos";
		    
		    TypedQuery<Curso> query = sesion.createQuery(queryString, Curso.class);
		    query.setParameter("alumnoParam", alumno);
		    return query.getResultList();
		}
		// Obtener los alumnos ordenados por nombre, saltando los 10 primeros (Paginación)
		public List<Alumno> getAlumnosPaginados(int inicio, int cantidad) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "FROM " + Alumno.class.getName() + " a ORDER BY a.nombre ASC";
		    
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    query.setFirstResult(inicio); // Desde qué registro empieza (Pág. 91)
		    query.setMaxResults(cantidad); // Cuántos registros trae (Pág. 92)
		    
		    return query.getResultList();
		}
		
		// Obtener la suma total de todas las edades (Agregación - Pág. 97)
		public Long getSumaEdades() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "SELECT SUM(a.edad) FROM " + Alumno.class.getName() + " a";
		    
		    TypedQuery<Long> query = sesion.createQuery(queryString, Long.class);
		    return query.getSingleResult();
		}
		
		// Obtener cursos con un promedio de edad superior a un valor (HAVING - Pág. 113)
		public List<Object[]> getCursosConMediaSuperiorA(Double mediaLimite) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // Agrupamos por curso y filtramos el grupo con HAVING
		    String queryString = "SELECT a.curso.nombre, AVG(a.edad) FROM " + Alumno.class.getName() + 
		                         " a GROUP BY a.curso.nombre HAVING AVG(a.edad) > :mediaParam";
		    
		    TypedQuery<Object[]> query = sesion.createQuery(queryString, Object[].class);
		    query.setParameter("mediaParam", mediaLimite);
		    return query.getResultList();
		}
		
		// Buscar alumnos por nombre ignorando mayúsculas (LOWER - Pág. 98)
		public List<Alumno> getAlumnosNombreMinusculas(String nombreBusqueda) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // Convertimos ambos lados a minúsculas para una comparación insensible
		    String queryString = "FROM " + Alumno.class.getName() + " a WHERE LOWER(a.nombre) = LOWER(:nombreParam)";
		    
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    query.setParameter("nombreParam", nombreBusqueda);
		    return query.getResultList();
		}
		
		// Obtener niveles de curso distintos presentes entre los alumnos (DISTINCT - Pág. 97)
		public List<String> getNivelesDistintos() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "SELECT DISTINCT a.curso.nivel FROM " + Alumno.class.getName() + " a";
		    
		    TypedQuery<String> query = sesion.createQuery(queryString, String.class);
		    return query.getResultList();
		}
		
		// 36. Uso de CONCAT (Pág. 98): Unir nombre y email en una sola cadena
		public List<String> getNombresYEmailsUnidos() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "SELECT CONCAT(a.nombre, ' (', a.email, ')') FROM " + Alumno.class.getName() + " a";
		    TypedQuery<String> query = sesion.createQuery(queryString, String.class);
		    return query.getResultList();
		}

		// 37. Uso de LENGTH (Pág. 98): Alumnos con nombres de más de 5 letras
		public List<Alumno> getAlumnosNombreLargo() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "FROM " + Alumno.class.getName() + " a WHERE LENGTH(a.nombre) > 5";
		    TypedQuery<Alumno> query = sesion.createQuery(queryString, Alumno.class);
		    return query.getResultList();
		}

		// 38. Uso de EXISTS (Pág. 101): Cursos que tienen algún alumno de DAW
		public List<Curso> getCursosConAlumnosDeDAW() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    String queryString = "SELECT c FROM " + Curso.class.getName() + " c " +
		                         "WHERE EXISTS (SELECT a FROM " + Alumno.class.getName() + " a WHERE a.curso = c AND c.nombre = 'DAW')";
		    TypedQuery<Curso> query = sesion.createQuery(queryString, Curso.class);
		    return query.getResultList();
		}
		
		
		
}
