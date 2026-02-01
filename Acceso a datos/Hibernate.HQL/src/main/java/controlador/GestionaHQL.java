package controlador;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.Alumno;
import servicio.ServicioAlumno;



public class GestionaHQL {
	
	private static final Logger logger = LogManager.getLogger(GestionaHQL.class);
	public static void main(String[] args) {
		ServicioAlumno servicioAlumno = new ServicioAlumno();
		
		// 1 y 2. Consultas básicas
				logger.info("1. Todos los alumnos: " + servicioAlumno.devolverTodos());
				logger.info("2. Nombres y Emails: " + servicioAlumno.devolverNombreEmail());
				
				// 3, 4 y 5. Filtros y Ordenación
				logger.info("3. Mayores de 18: " + servicioAlumno.devolverMayores18());
				logger.info("4. Alumnos llamados Ana: " + servicioAlumno.devolverAlumnosAna());
				logger.info("5. Ordenados por edad (Desc): " + servicioAlumno.devolverOrdenadosEdad());
				
				// 6, 7 y 8. Parámetros y LIKE
				logger.info("6. Mayores de 20 (Parámetro): " + servicioAlumno.devolverMayoresQue(20));
				logger.info("7. Alumno por email: " + servicioAlumno.devolverPorEmail("ana@email.com"));
				logger.info("8. Nombre contiene 'Juan': " + servicioAlumno.devolverNombreContiene("Juan"));
				
				// 9 y 10. Relaciones Alumno-Curso
				logger.info("9. Alumnos de DAM: " + servicioAlumno.devolverAlumnosDAM());
				
				List<Object[]> lista10 = servicioAlumno.devolverNombreAlumnoYCurso();
				logger.info("--- 10. Detalle de Alumnos y sus Cursos ---");
				for (Object[] fila : lista10) {
				    String nombreAlumno = (String) fila[0];
				    String nombreCurso = (String) fila[1];
				    logger.info("Alumno: " + nombreAlumno + " | está en el curso: " + nombreCurso);
				}
				
				// 11 y 12. Consultas sobre Cursos
				logger.info("11. Cursos con alumnos: " + servicioAlumno.devolverCursosConAlumnos());
				logger.info("12. Alumnos de nivel Superior: " + servicioAlumno.devolverAlumnosNivelSuperior());
				
				// 13 y 14. Agregación (Cálculos)
				logger.info("13. Total de alumnos registrados: " + servicioAlumno.devolverContarAlumnos());
				logger.info("14. Edad media de la clase: " + servicioAlumno.devolverEdadMedia());
				
				// 15 y 16. Agrupación y Máximos
				// Reemplaza el logger del punto 15 por esto:
				List<Object[]> lista15 = servicioAlumno.devolverNumeroAlumnosPorCurso();
				logger.info("--- 15. Estadísticas de Alumnos por Curso ---");
				for (Object[] fila : lista15) {
				    String nombreCurso = (String) fila[0];
				    Long cantidad = (Long) fila[1];
				    logger.info("Curso: " + nombreCurso + " -> Total alumnos: " + cantidad);
				}
				
				logger.info("16. Curso con más alumnos: " + servicioAlumno.devolverCursoConMasAlumnos());
				
				// 17 y 18. Estados NULL o Vacíos
				logger.info("17. Alumnos sin curso asignado: " + servicioAlumno.devolverAlumnosSinCurso());
				logger.info("18. Cursos que no tienen alumnos: " + servicioAlumno.devolverCursosSinAlumnos());
				
				// 19 y 20. Subconsultas y Distinct
				logger.info("19. Alumnos con edad sobre la media: " + servicioAlumno.devolverAlumnosEdadMayorMedia());
				logger.info("20. Cursos con alumnos veteranos (>25): " + servicioAlumno.devolverNombresCursosMayores25());
			
				// 21 y 22. Máximos y Mínimos (Pág. 97)
				logger.info("21. Edad máxima entre alumnos: " + servicioAlumno.devolverEdadMaxima());
				logger.info("22. Alumno más joven de la clase: " + servicioAlumno.devolverAlumnoMasJoven());
				
				// 23. Optimización JOIN FETCH (Pág. 101)
				logger.info("23. Carga optimizada (FETCH): " + servicioAlumno.devolverAlumnosConCursosFetch());
				
				// 24. Uso de BETWEEN (Pág. 98)
				logger.info("24. Alumnos en DAM entre 18 y 25 años: " + servicioAlumno.devolverAlumnosCursoEdadRango("DAM", 18, 25));
				
				// 25. LIKE y navegación de objetos (Pág. 99)
				logger.info("25. Alumnos de Gmail en nivel Superior: " + servicioAlumno.devolverAlumnosEmailYNivel("gmail", "Superior"));
				
				// 26. Operador IN (Pág. 98) con bucle para Object[]
				List<String> cursosABuscar = List.of("DAM", "DAW");
				List<Object[]> lista26 = servicioAlumno.devolverNombreEdadDeVariosCursos(cursosABuscar);
				logger.info("--- 26. Nombres y edades en cursos DAM/DAW ---");
				for (Object[] fila : lista26) {
				    logger.info("Nombre: " + fila[0] + " | Edad: " + fila[1]);
				}
				
				// 27 y 28. Filtros complejos y Paginación (Pág. 91-92)
				logger.info("27. Alumnos con curso (Excepto Ana): " + servicioAlumno.devolverAlumnosFiltradoEspecial("Ana", 18));
				logger.info("28. Top 3 más jóvenes de DAM: " + servicioAlumno.devolverTop3JovenesPorCurso("DAM"));
				
				// 29. Operador MEMBER OF (Pág. 98)
				Alumno primerAlumno = servicioAlumno.devolverTodos().get(0);
				logger.info("29. Cursos donde aparece " + primerAlumno.getNombre() + ": " + servicioAlumno.devolverCursosDondeEstaElAlumno(primerAlumno));
				
				// 30. Paginación avanzada
				logger.info("30. Paginación (Página 2, de 5 en 5): " + servicioAlumno.devolverAlumnosPaginados(5, 5));
				
				// 31 y 32. Agregación y Grupos (Pág. 97 y 113)
				logger.info("31. Suma total de edades: " + servicioAlumno.devolverSumaEdades());
				
				List<Object[]> lista32 = servicioAlumno.devolverCursosConMediaSuperiorA(20.0);
				logger.info("--- 32. Cursos con media de edad > 20 (HAVING) ---");
				for (Object[] fila : lista32) {
				    logger.info("Curso: " + fila[0] + " | Media de edad: " + fila[1]);
				}
				
				// 33 y 34. Funciones de cadena y Distinct (Pág. 97-98)
				logger.info("33. Búsqueda insensible (ana): " + servicioAlumno.devolverAlumnosNombreMinusculas("ana"));
				logger.info("34. Listado de niveles únicos: " + servicioAlumno.devolverNivelesDistintos());
				
				// 35. Operaciones masivas (Pág. 114-115)
				logger.info("35. Actualización masiva DAM (Filas): " + servicioAlumno.subirEdadCurso("DAM"));
				// logger.info("35. Limpieza alumnos sin email (Filas): " + servicioAlumno.eliminarAlumnosSinEmail());
				// 35 (Verificación). Comprobamos cuántos se han borrado (debería ser 0 si todos tienen email)
				logger.info("35. Limpieza alumnos sin email (Filas): " + servicioAlumno.eliminarAlumnosSinEmail());

				// 36, 37 y 38. Funciones de tus apuntes
				logger.info("36. Contactos concatenados: " + servicioAlumno.devolverContactosUnidos());
				logger.info("37. Alumnos con nombre de > 5 letras: " + servicioAlumno.devolverNombresLargos());
				logger.info("38. Cursos con alumnos en DAW: " + servicioAlumno.devolverCursosConDAW());

				// 39. Verificación de la actualización masiva (Punto 35 de tu log anterior)
				// Como subiste la edad en el log anterior, si buscas a Ana (id=1), su edad debería ser 21 ahora
				logger.info("39. Verificación edad Ana (después de update): " + servicioAlumno.devolverPorEmail("ana@email.com"));
	
	}
		}
