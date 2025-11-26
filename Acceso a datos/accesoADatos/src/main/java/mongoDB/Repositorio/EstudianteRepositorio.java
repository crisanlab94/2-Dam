package mongoDB.Repositorio;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import mongoDB.Modelo.Adress;
import mongoDB.Modelo.Estudiante;
import mongoDB.Modelo.Score;



public class EstudianteRepositorio {
	 private static final String NOMBRE_COLECCION = "estudiantes";
	  private final MongoCollection<Document> coleccion;
	  private List<Estudiante>estudiantes;

	   public EstudianteRepositorio(MongoDatabase db) {
	       this.coleccion = db.getCollection(NOMBRE_COLECCION);
	       this.estudiantes=this.read();
	   }

	   //Guardar estudiante en base de datos
	   public void save(Estudiante e) {
		   Document estudiantes = new Document("id", e.getId())
		            .append("name", e.getName())
		            .append("notaMedia", e.getNotaMedia())
		            .append("aficiones", e.getAficiones());

		    // ✅ Dirección segura
		    if (e.getDirecciones() != null) {
		        Document direccion = new Document("city", e.getDirecciones().getCity())
		                .append("zip", e.getDirecciones().getZip())
		                .append("street", e.getDirecciones().getStreet())
		                .append("number", e.getDirecciones().getNumber());
		        estudiantes.append("address", direccion);
		    } else {
		        estudiantes.append("address", null); // o no agregarlo
		    }

		    // ✅ Puntuaciones
		    List<Document> puntuaciones = new ArrayList<>();
		    if (e.getPuntuaciones() != null) {
		        for (Score record : e.getPuntuaciones()) {
		            Document score = new Document("score", record.getScore())
		                    .append("type", record.getType());
		            puntuaciones.add(score);
		        }
		    }
		    estudiantes.append("scores", puntuaciones);

		    coleccion.insertOne(estudiantes);
		}
	   
	   //Select * Leer todo con el .find
	       
	   public List<Estudiante> read() {
			List<Estudiante> estudiantes = new ArrayList<>();
			FindIterable<Document> documentos = coleccion.find();
			
			for (Document doc : documentos) {
			     // Crear estudiante
		        Estudiante e = new Estudiante();
		        e.setId(doc.getInteger("id", 0));
		        e.setName(doc.getString("name")!= null ? doc.getString("name") : "");
		        e.setNotaMedia(doc.getDouble("notaMedia")!= null ? doc.getDouble("notaMedia") : 0.0);

			
			
			//Aficiones
				List<String> aficiones = doc.getList("aficiones", String.class);
				e.setAficiones(aficiones != null ? aficiones : new ArrayList<>());
				
				
				//Direccion
				Document addressDoc=(Document) doc.get("address");
				if(addressDoc != null) {
						Adress adress = new Adress(
							addressDoc.getString("city") != null ? addressDoc.getString("city") : "",
							addressDoc.getInteger("zip",0),
							addressDoc.getString("street")!= null ? addressDoc.getString("street") : "",
							addressDoc.getInteger("number",0));
					    e.setDirecciones(adress);
		        }
				
				//Scores 
			List<Document> scoreDoc =(List<Document>) doc.get("scores");
			if (scoreDoc != null) {
				List<Score> scores = new ArrayList<>();
			for (Document scoreDocItem : scoreDoc) {
				Score score = new Score(
					    scoreDocItem.getDouble("score")!= null ? scoreDocItem.getDouble("score") : 0.0,
					    scoreDocItem.getString("type")!= null ? scoreDocItem.getString("type") : "");
							

				 scores.add(score);
							
				}
			 
			
			   e.setPuntuaciones(scores);
			}
			estudiantes.add(e);
			
	   }
			return estudiantes; 
	   
	   }

	   public List<Estudiante> getEstudiantes() {
		   return estudiantes;
	   }

	   public void setEstudiantes(List<Estudiante> estudiantes) {
		   this.estudiantes = estudiantes;
	   }

	   public static String getNombreColeccion() {
		   return NOMBRE_COLECCION;
	   }

	   public MongoCollection<Document> getColeccion() {
		   return coleccion;
	   }
	   
	   
}



