package controladores;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ArticuloDao;
import dao.AutorDao;
import dao.RevistaDao;
import modelos.Articulo;
import modelos.Autor;
import modelos.Revista;
import servicio.ServicioDao;


public class GestionaArticulos {
	private static final Logger logger = LogManager.getLogger(GestionaArticulos.class);

	public static void main(String[] args) {
		AutorDao autorDao = new AutorDao();
		ArticuloDao articuloDao = new ArticuloDao();
		RevistaDao revistaDao = new RevistaDao();
		ServicioDao servicioDao = new ServicioDao();
		Autor a1 = new Autor("12345678C", "Pepa Flores", "flores@gmail.com");
		autorDao.create(a1);
		Autor a2 = new Autor("12345679E", "Ruperta Florero", "florero@gmail.com");
		autorDao.create(a2);
		Autor a3 = new Autor("1222679E", "Ramon Florito", "florito@gmail.com");
		autorDao.create(a3);

		Articulo ar1 = new Articulo("Seguridad en los datos", 1, 15);
		Articulo ar2 = new Articulo("Seguridad en la web", 5, 20);
		ar1.addAutor(a2);
		ar1.addAutor(a1);
		ar2.addAutor(a1);
		ar2.addAutor(a3);

		Revista r = new Revista("Revista 1", 1, LocalDate.now(), 179);
		revistaDao.create(r);
		r.addArticulo(ar2);
		r.addArticulo(ar1);
		revistaDao.mergeaObjeto(r);

		List<Autor> autores = autorDao.getAll();
		for (Autor a : autores) {
			logger.debug(a);
		}

		List<Articulo> articulos = articuloDao.getAll();
		for (Articulo a : articulos) {
			logger.debug(a);
		}
		//Consulta 1
		logger.info("--- C1: Artículos de Ramon Florito ---");
	    List<Articulo> resC1 = servicioDao.buscarArticulosDeAutor("Ramon Florito");
	    for (int i = 0; i < resC1.size(); i++) {
	        logger.info("- " + resC1.get(i).getTitulo());
	    }

		// --- CONSULTA 2: Título y número de páginas (si páginas > 6) ---
		logger.info("--- C2: Artículos con más de 6 páginas ---");
		List<Object[]> listaC2 = servicioDao.consultaArticulosLargos();
		for (int i = 0; i < listaC2.size(); i++) {
			Object[] fila = listaC2.get(i);
			// fila[0] = Título | fila[1] = Número de páginas (calculado en el DAO)
			logger.info("Título: " + fila[0] + " | Páginas: " + fila[1]);
		}

		// --- CONSULTA 3: Informe completo (Título, Páginas, Revista y Fecha) ---
		logger.info("--- C3: Informe completo de artículos largos ---");
		List<Object[]> listaC3 = servicioDao.consultaArticulosLargosConRevista();
		for (int i = 0; i < listaC3.size(); i++) {
			Object[] fila = listaC3.get(i);
			// fila[0]=Título, fila[1]=Págs, fila[2]=Nombre Revista, fila[3]=Fecha
			logger.info("Título: " + fila[0] + " | Págs: " + fila[1] + 
					    " | Revista: " + fila[2] + " | Fecha: " + fila[3]);
		}

		// --- CONSULTA 4: Número de artículos por revista ---
		logger.info("--- C4: Recuento de artículos por revista ---");
		List<Object[]> listaC4 = servicioDao.obtenerEstadisticasRevistas();
		for (int i = 0; i < listaC4.size(); i++) {
			Object[] fila = listaC4.get(i);
			// fila[0] = Nombre Revista | fila[1] = Conteo (Long)
			logger.info("Revista: " + fila[0] + " -> Total artículos: " + fila[1]);
		}

		// --- CONSULTA 5: Revistas publicadas antes de una fecha (ej: hoy) ---
		logger.info("--- C5: Revistas publicadas antes de hoy ---");
		List<Object[]> listaC5 = servicioDao.obtenerRevistasAntiguas(LocalDate.now());
		for (int i = 0; i < listaC5.size(); i++) {
			Object[] fila = listaC5.get(i);
			// fila[0] = Nombre | fila[1] = Fecha | fila[2] = Número revista
			logger.info("Revista: " + fila[0] + " | Fecha: " + fila[1] + " | Edición nº: " + fila[2]);
		}
		
	
	
	// --- C. EXTRA 1: Ranking de Autores (Agregación) ---
	// Muestra qué autores han escrito más artículos en total
	logger.info("--- EXTRA 1: RANKING DE PRODUCTIVIDAD POR AUTOR ---");
	List<Object[]> rankingAutores = servicioDao.obtenerRankingAutores();
	for (int i = 0; i < rankingAutores.size(); i++) {
	    Object[] fila = rankingAutores.get(i);
	    // fila[0] = Nombre del autor | fila[1] = Cantidad de artículos
	    logger.info("Autor: " + fila[0] + " | Total artículos: " + fila[1]);
	}
	
	// --- C. EXTRA 2: Autores por Revista (N:M y 1:N) ---
	// Recupera la lista de objetos Autor que han participado en "Revista 1"
	logger.info("--- EXTRA 2: AUTORES QUE HAN PUBLICADO EN 'Revista 1' ---");
	List<Autor> autoresRevista = servicioDao.obtenerAutoresDeRevista("Revista 1");
	for (int i = 0; i < autoresRevista.size(); i++) {
	    Autor au = autoresRevista.get(i);
	    logger.info("- Autor: " + au.getNombre() + " (Email: " + au.getEmail() + ")");
	}

	// --- C. EXTRA 3: Revistas por dominio de Email ---
	// Busca revistas que tengan colaboradores con correos de "gmail.com"
	logger.info("--- EXTRA 3: REVISTAS CON COLABORADORES DE GMAIL ---");
	List<Revista> revistasGmail = servicioDao.obtenerRevistasPorEmail("gmail.com");
	for (int i = 0; i < revistasGmail.size(); i++) {
	    Revista rev = revistasGmail.get(i);
	    logger.info("- Revista encontrada: " + rev.getNombreRevista() + " (Nº " + rev.getNumeroRevista() + ")");
	}
	
	// --- C. EXTRA 4: Filtro de grupos con HAVING ---
	// Revistas que tienen 2 o más autores diferentes (mucha colaboración)
	logger.info("--- EXTRA 4: REVISTAS CON ALTA COLABORACIÓN (2+ AUTORES) ---");
	List<Object[]> colaboracion = servicioDao.obtenerRevistasConMuchosAutores(2);
	for (int i = 0; i < colaboracion.size(); i++) {
	    Object[] fila = colaboracion.get(i);
	    // fila[0] = Nombre Revista | fila[1] = Número de autores distintos
	    logger.info("Revista: " + fila[0] + " | Autores participantes: " + fila[1]);
	}
	}
}
