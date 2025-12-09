package mongoDB.Repositorio;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import mongoDB.Modelo.Coordenadas;
import mongoDB.Modelo.Estudiante;
import mongoDB.Modelo.Habitaciones;
import mongoDB.Modelo.Hotel;
import mongoDB.Modelo.Tipo;
import mongoDB.Modelo.Ubicacion;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



public class HotelRepositorio {
	
	
private static final Logger logger = LogManager.getLogger(HotelRepositorio.class);
	
    private static final String NOMBRE_COLECCION = "hoteles";
    private final MongoCollection<Document> coleccion;
    
    // ✨ CACHE LOCAL: Lista en memoria que guarda una copia de los datos.
    private List<Hotel> hoteles; 

    public HotelRepositorio(MongoDatabase db) {
        // ✨ CONEXIÓN: Inicializa la colección de MongoDB
        this.coleccion = db.getCollection(NOMBRE_COLECCION);
        // ✨ CARGA INICIAL: Llenamos la cache local con todos los datos al iniciar el programa
        //this.estudiantes = this.read(); 
    }

    
	

	// Metodo auxiliar para convertir Document a Hotel
	private Hotel mapearHotel(Document doc) {
	    Hotel h = new Hotel();
	    
	    // Mapeo de campos simples
	    h.setIdHotel(doc.getString("idHotel"));
	    h.setNombre(doc.getString("nombre") != null ? doc.getString("nombre") : "");
	    h.setFechaApertura(doc.getString("fechaApertura") != null ? doc.getString("fechaApertura") : "");
	    
	    // Estrellas 
	    Number estrellas = doc.get("estrellas", Number.class);
	    h.setEstrellas(estrellas != null ? estrellas.intValue() : 0);
	    
	    // Boolean mascota
	    Boolean adMascota = doc.getBoolean("admiteMascota");
	    h.setAdmiteMascotas(adMascota != null ? adMascota : false);
	    
	    // --- Mapeo de Ubicacion  ---
	    Document ubicacionDoc = (Document) doc.get("ubicacion");
	    
	    if (ubicacionDoc != null) { 
	        
	        // 1. Mapeo de Coordenadas
	        Coordenadas coordenadasObj = null;
	        Document coordenadasDoc = ubicacionDoc.get("coordenadas", Document.class);

	        if (coordenadasDoc != null) {
	            coordenadasObj = new Coordenadas(
	                coordenadasDoc.getDouble("lat") != null ? coordenadasDoc.getDouble("lat") : 0.0,
	                coordenadasDoc.getDouble("lon") != null ? coordenadasDoc.getDouble("lon") : 0.0
	            );
	        }
	        
	     // 2.  Ubicacion
	        h.setUbicacion(new Ubicacion(
	            ubicacionDoc.getString("calle") != null ? ubicacionDoc.getString("calle") : "",
	            
	            // Si el JSON es int: Usamos getInteger y el valor por defecto 0.
	            ubicacionDoc.getInteger("numero") != null ? ubicacionDoc.getInteger("numero") : 0,
	            
	            // CORRECCIÓN FINAL: Usamos getInteger, eliminando la conversión de String
	            ubicacionDoc.getInteger("codigoPostal") != null ? ubicacionDoc.getInteger("codigoPostal") : 0,
	            
	            coordenadasObj
	            ));
	    }
	    
	    // --- Lectura de habitaciones (Lista) ---
	    List<Document> habitacionesDoc =  doc.getList("habitaciones", Document.class);
	    if (habitacionesDoc != null) {
	        List<Habitaciones> listaHabitaciones = new ArrayList<Habitaciones>();
	        for (Document asigDocItem : habitacionesDoc) {
	            String tipoStr = asigDocItem.getString("tipo");
	            Tipo tipoEnum = null;
	            if (tipoStr != null) {
	                
	                tipoEnum = Tipo.valueOf(tipoStr.toUpperCase());
	            }
	            
	            listaHabitaciones.add(new Habitaciones(
	                tipoEnum,
	                asigDocItem.getDouble("precio") != null ? asigDocItem.getDouble("precio") : 0.0, 
	                asigDocItem.getInteger("capacidad") != null ? asigDocItem.getInteger("capacidad") : 0, 
	                asigDocItem.getBoolean("disponible") != null ? asigDocItem.getBoolean("disponible") : false
	            ));
	        }
	       
	        h.setHabitaciones(listaHabitaciones); 
	    }
	    
	   
	    return h;
	}
	
	
	public Hotel filterById(String idHotel) { 

	    Document queryMongoFiltrarPorId = new Document ("idHotel", idHotel);

	    Document docHotel = coleccion.find(queryMongoFiltrarPorId).first();

	    Hotel hotel = null;

	    if(docHotel !=null) {

	    	hotel=mapearHotel(docHotel);

	    }  	    

	    return hotel;

	} 

}
