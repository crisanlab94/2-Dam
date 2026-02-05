package servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import dao.ArticuloDao;
import dao.AutorDao;
import dao.RevistaDao;
import modelos.Articulo;
import modelos.Autor;
import modelos.Revista;
import utiles.HibernateUtil;



public class ServicioDao {
	private  ArticuloDao articuloDao;;
    private AutorDao autorDao;
    private RevistaDao revistaDao;
    
	public ServicioDao() {
		super();
		this.articuloDao = new ArticuloDao();
		this.autorDao = new AutorDao();
		this.revistaDao = new RevistaDao();
	}
    
	// CONSULTA 1: Artículos por autor
	public List<Articulo> buscarArticulosDeAutor(String nombre) {
        
        if (nombre == null || nombre.isEmpty()) {
            return new ArrayList<>();
        }
        return articuloDao.getArticulosPorNombreAutor(nombre);
    }
	
	// CONSULTA 2: Artículos con más de 6 páginas
	public List<Articulo[]> consultaArticulosLargos() {
	    return articuloDao.getArticulosMasDe6Paginas();
	}
	
	// CONSULTA 3: Artículos largos con info de revista
	public List<Object[]> consultaArticulosLargosConRevista() {
	    return articuloDao.getArticulosMasDe6PaginasConRevista();
	}
	// CONSULTA 4: Número de artículos por revista (llama al RevistaDao)
    public List<Object[]> obtenerEstadisticasRevistas() {
        return revistaDao.getRecuentoArticulosPorRevista();
    }

    // CONSULTA 5: Revistas anteriores a una fecha (llama al RevistaDao)
    public List<Object[]> obtenerRevistasAntiguas(LocalDate fecha) {
        return revistaDao.getRevistasPreviasAFecha(fecha);
    }
    
 // EXTRA 1: Ranking de autores (Llama al AutorDao)
    public List<Object[]> obtenerRankingAutores() {
        return autorDao.getRankingAutores();
    }

    // EXTRA 2: Lista de objetos Autor por revista 
    public List<Autor> obtenerAutoresDeRevista(String nombreRevista) {
        if (nombreRevista == null || nombreRevista.isEmpty()) return new ArrayList<>();
        return autorDao.getAutoresPorRevista(nombreRevista);
    }

    // EXTRA 3: Revistas filtradas por email de autor (
    public List<Revista> obtenerRevistasPorEmail(String dominio) {
        if (dominio == null || dominio.isEmpty()) return new ArrayList<>();
        return revistaDao.getRevistasPorDominioEmail(dominio);
    }

    // EXTRA 4: Revistas con mínimo X autores (Llama al RevistaDao)
    public List<Object[]> obtenerRevistasConMuchosAutores(int minimo) {
        return revistaDao.getRevistasConMuchosAutores(minimo);
    }
	
}



