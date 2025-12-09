package mongoDB.Servicio;


import com.mongodb.client.MongoDatabase;

import mongoDB.Modelo.Hotel;
import mongoDB.Repositorio.HotelRepositorio;


public class HotelServicio {
	
	private final HotelRepositorio hotelRepositorio; 

	
	
  
    public HotelServicio(MongoDatabase db) {
		super();
		this.hotelRepositorio = new HotelRepositorio(db);
	}
    
    public Hotel obtenerHotelPorId(String id) {
        return hotelRepositorio.filterById(id); 
    }
}
