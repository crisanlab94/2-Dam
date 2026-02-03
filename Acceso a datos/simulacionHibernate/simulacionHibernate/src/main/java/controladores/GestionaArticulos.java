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

public class GestionaArticulos {
	private static final Logger logger = LogManager.getLogger(GestionaArticulos.class);

	public static void main(String[] args) {
		AutorDao autorDao = new AutorDao();
		ArticuloDao articuloDao = new ArticuloDao();
		RevistaDao revistaDao = new RevistaDao();
		Autor a1 = new Autor("12345678C", "Pepa Flores", "flores@gmail.com");
		autorDao.create(a1);
		Autor a2 = new Autor("12345679E", "Ruperta Florero", "florero@gmail.com");
		autorDao.create(a2);
		Autor a3 = new Autor("1222679E", "Ramon Florito", "florito@gmail.com");
		autorDao.create(a3);

		Articulo ar1 = new Articulo("Seguridad en los datos", 10, 15);
		Articulo ar2 = new Articulo("Seguridad en la web", 5, 1);
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

	}

}
