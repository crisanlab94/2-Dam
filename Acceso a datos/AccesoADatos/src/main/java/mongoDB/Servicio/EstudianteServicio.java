package mongoDB.Servicio;

import java.util.List;

import com.mongodb.client.MongoDatabase;

import mongoDB.Modelo.Estudiante;
import mongoDB.Repositorio.EstudianteRepositorio;

public class EstudianteServicio {
	 private final EstudianteRepositorio repo;

	   // El servicio recibe MongoDatabase y construye el repositorio
	   public EstudianteServicio(MongoDatabase db) {
	       this.repo = new EstudianteRepositorio(db);
	   }
	   // Guarda un estudiante en la base de datos
	   public void save(Estudiante e) {
	       // Aquí podrías añadir validaciones, reglas de negocio, etc.
	       repo.save(e);
	   }
	   // Lista todos los estudiantes
	   public List<Estudiante> read() {
	       return repo.read();
	   }
	//TODO Agregar resto de operaciones del CRUD
	   public EstudianteRepositorio getRepo() {
		   return repo;
	   }
	   
	   
	   
	}




