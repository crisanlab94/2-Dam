package mongoDB.Servicio;

import java.util.List;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection; 
import com.mongodb.client.result.UpdateResult;

import mongoDB.Modelo.Hotel;
import mongoDB.Modelo.Habitaciones; 
import mongoDB.Modelo.Tipo; 
import mongoDB.Repositorio.HotelRepositorio;

import org.bson.Document; 


public class HotelServicio {
	
	private final HotelRepositorio hotelRepositorio;
	
	public HotelServicio(MongoDatabase db) {
		super();
		this.hotelRepositorio = new HotelRepositorio(db);
	}
    
    // -----------------------------------------------------------------------
    // --- MÉTODOS CRUD BÁSICOS ---
    // -----------------------------------------------------------------------
	
	public void save(Hotel h){
	   hotelRepositorio.save(h);
	}
	
	public List<Hotel> read() {
		return hotelRepositorio.read();
	}
		
  
	public Hotel filterById(String idHotel) { 
			return hotelRepositorio.filterById(idHotel);
	}
		
	public void update(Hotel h) {
		hotelRepositorio.update(h);
	}
    
	public void delete(String idHotel) {
		hotelRepositorio.delete(idHotel);
	}
    
    // -----------------------------------------------------------------------
    // --- MÉTODOS AVANZADOS (Filtros y Agregaciones) ---
    // -----------------------------------------------------------------------
    
    /**
     * Recupera hoteles que cumplen: (5 estrellas OR mascotas) AND Ciudad=Madrid.
     * (Filtro de la profesora que puede dar 0 si los datos no coinciden exactamente).
     */
    public List<Hotel> recuperarHotelesMadridFiltro() {
        return hotelRepositorio.recuperarHotelesMadridFiltro();
    }
    
    public long contarHotelesSuiteJunior() {
        return hotelRepositorio.contarHotelesSuiteJunior();
    }
    
    public double calcularMediaEstrellasPorCiudad(String ciudad) {
        return hotelRepositorio.calcularMediaEstrellasPorCiudad(ciudad);
    }
    
    // -----------------------------------------------------------------------
    // --- NUEVOS MÉTODOS DE FILTRO (Añadidos para funcionalidad y prueba) ---
    // -----------------------------------------------------------------------
    
    /**
     * Filtro complejo de tres condiciones (que sí existen en los datos):
     * Estrellas = 4 AND (Mascotas = true OR Precio Doble Estándar <= 115.0).
     */
    public List<Hotel> buscarHotelesFiltroComplejo() {
        return hotelRepositorio.buscarHotelesFiltroComplejo();
    }
    
    /**
     * Recupera hoteles que tengan 3 estrellas O 4 estrellas.
     * (Filtro simple y robusto que usa datos numéricos que sí existen).
     */
    public List<Hotel> buscarHotelesPor3O4Estrellas() {
        return hotelRepositorio.buscarHotelesPor3O4Estrellas();
    }
    
    // -----------------------------------------------------------------------
    // --- MÉTODOS DE ACTUALIZACIÓN ---
    // -----------------------------------------------------------------------
    
    public UpdateResult actualizarDatosBasicos(String idHotel, String nuevoNombre, int nuevasEstrellas) {
        return hotelRepositorio.actualizarDatosBasicos(idHotel, nuevoNombre, nuevasEstrellas);
    }
    
    public UpdateResult actualizarUbicacionCompleta(
    	    String idHotel, 
    	    String newCalle, 
    	    int newNumero, 
    	    String newCP, 
    	    double newLat, 
    	    double newLon) {
        return hotelRepositorio.actualizarUbicacionCompleta(idHotel, newCalle, newNumero, newCP, newLat, newLon);
    }
    
    public UpdateResult anadirHabitacion(String idHotel, Habitaciones nuevaHab) {
        return hotelRepositorio.anadirHabitacion(idHotel, nuevaHab);
    }
    
    public UpdateResult actualizarCPGranVia() {
        return hotelRepositorio.actualizarCPGranVia();
    }
    
    public UpdateResult actualizarPrecioHabitacionIndividual(String idHotel, double nuevoPrecio) {
        return hotelRepositorio.actualizarPrecioHabitacionIndividual(idHotel, nuevoPrecio);
    }
    
    public UpdateResult eliminarHabitacionesCaras(String nombreHotel, double precioLimite) {
        return hotelRepositorio.eliminarHabitacionesCaras(nombreHotel, precioLimite);
    }

    // -----------------------------------------------------------------------
    // --- GETTERS AÑADIDOS PARA COINCIDIR CON EL REPOSITORIO ---
    // -----------------------------------------------------------------------

    /** Devuelve la lista local (caché) de hoteles. */
    public List<Hotel> getHoteles() {
        return hotelRepositorio.getHoteles();
    }
    
    /** Devuelve la colección de MongoDB. */
    public MongoCollection<Document> getColeccion() {
        return hotelRepositorio.getColeccion();
    }
  
    /** Getter para el Repositorio (ya existía). */
	public HotelRepositorio getHotelRepositorio() {
		return hotelRepositorio;
	}
}