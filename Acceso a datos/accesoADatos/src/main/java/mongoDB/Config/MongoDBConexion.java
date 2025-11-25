package mongoDB.Config;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;



public class MongoDBConexion {
	private static final Logger logger = LogManager.getLogger(MongoDBConexion.class);
	private MongoDatabase db;

	public MongoDBConexion() {
		Propiedades propiedades;
		try {
			propiedades = new Propiedades("app.properties");
			String ruta = propiedades.get("mongodb.uri");
			String baseDatos = propiedades.get("mongodb.database");
			MongoClient client = MongoClients.create(ruta);
			this.db = client.getDatabase(baseDatos);
			//Aqui puedo hacer lo que quiera con mi base de datoss
			//Como añadir, leer.. con this.db. lo que quieras hacer
			logger.debug("Conectado a la BD: " + db.getName());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}	}

	public MongoDatabase getDb() {
		return db;
	}
	
}



