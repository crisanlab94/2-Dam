package mongoDB.Controlador;

import com.mongodb.client.MongoDatabase;

import mongoDB.Config.MongoDBConexion;
import mongoDB.Modelo.Hotel;
import mongoDB.Servicio.HotelServicio;


public class GestionaHoteles {
	public static void main(String[] args) {
		
	// 1. Conexión
    MongoDBConexion conexion = new MongoDBConexion();
    MongoDatabase db = conexion.getDb();   
    
    // 2. Instanciar Servicio
    
    HotelServicio hotelS = new HotelServicio(db);
    
    String idABuscar = "h102"; 
    
    // 3. Llamar al método

    Hotel hotelEncontrado = hotelS.obtenerHotelPorId(idABuscar);
    
    // 4. Verificar resultado
    if (hotelEncontrado != null) {
        System.out.println("Éxito! Hotel encontrado: " + hotelEncontrado.getNombre());
        
        if (hotelEncontrado.getUbicacion() != null) {
            System.out.println("Calle: " + hotelEncontrado.getUbicacion().getCalle());
        }
    } else {
        System.out.println("No se encontró ningún hotel con el ID: " + idABuscar);
    }
}
      
	
}
