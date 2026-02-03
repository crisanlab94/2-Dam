package servicio;

import java.util.ArrayList;
import java.util.List;

import dao.ArticuloDao;
import dao.AutorDao;
import dao.RevistaDao;
import modelos.Articulo;



public class ServicioDao {
	private  ArticuloDao articuloDao;;
    private AutorDao autorDao;
    private RevistaDao revistaDao;
    
	public ServicioDao() {
		super();
		this.articuloDao = articuloDao;
		this.autorDao = autorDao;
		this.revistaDao = revistaDao;
	}
    

	public List<Articulo> buscarArticulosDeAutor(String nombre) {
        
        if (nombre == null || nombre.isEmpty()) {
            return new ArrayList<>();
        }
        return articuloDao.getArticulosPorNombreAutor(nombre);
    }
}



