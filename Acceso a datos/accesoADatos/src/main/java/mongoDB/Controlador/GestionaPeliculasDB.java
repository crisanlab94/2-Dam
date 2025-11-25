package mongoDB.Controlador;

import java.util.List;

import com.mongodb.client.MongoDatabase;

import mongoDB.Config.MongoDBConexion;
import mongoDB.Modelo.Estudiante;
import mongoDB.Servicio.EstudianteServicio;

public class GestionaPeliculasDB {
	public static void main(String[] args) {
		MongoDBConexion conexion = new MongoDBConexion();
		MongoDatabase db= conexion.getDb();	
		
		EstudianteServicio  estudianteS= new EstudianteServicio(db);
		
		Estudiante e1 = new Estudiante(1, "Maria Pelaez", 7.78, List.of("leer","nadar"));
		estudianteS.save(e1);
		List<Estudiante> estudiantes = estudianteS.read();
		for(Estudiante e : estudiantes)
		{
			System.out.println(e);
		}
//TODO Aquí creamos los diferentes servicios a partir del objeto db	
	}


}
