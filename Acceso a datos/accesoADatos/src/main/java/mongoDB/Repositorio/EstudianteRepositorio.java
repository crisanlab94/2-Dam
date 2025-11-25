package mongoDB.Repositorio;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import mongoDB.Modelo.Estudiante;



public class EstudianteRepositorio {
	 private static final String NOMBRE_COLECCION = "estudiantes";
	  private final MongoCollection<Document> coleccion;

	   public EstudianteRepositorio(MongoDatabase db) {
	       this.coleccion = db.getCollection(NOMBRE_COLECCION);
	   }

	   //Guardar estudiante en base de datos
	   public void save(Estudiante e) {
		   Document doc = new Document("id", e.getId())
	               .append("name", e.getName())
	               .append("notaMedia", e.getNotaMedia())
	               .append("aficiones", e.getAficiones());
	       coleccion.insertOne(doc);

	   }
	   //Select * Leer todo con el .find
	       
	   public List<Estudiante> read() {
			List<Estudiante> estudiantes = new ArrayList<>();
			FindIterable<Document> documentos = coleccion.find();
			for (Document doc : documentos) {
				Estudiante e = new Estudiante();
				e.setId(doc.getInteger("id", 0));
				e.setName(doc.getString("name"));
				e.setNotaMedia(doc.getDouble("notaMedia"));
				List<String> aficiones = doc.getList("aficiones", String.class);
				e.setAficiones(aficiones != null ? aficiones : new ArrayList<>());
				estudiantes.add(e);
			}
			return estudiantes;
	   } 
	   }



