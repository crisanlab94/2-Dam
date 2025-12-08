package mongoDB.Servicio;

import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.MongoDatabase;

import mongoDB.Modelo.Curso;
import mongoDB.Modelo.Estudiante;
import mongoDB.Modelo.IdException;
import mongoDB.Modelo.LongitudNombre;
import mongoDB.Modelo.NombreAscendenteComparator;
import mongoDB.Repositorio.EstudianteRepositorio;

public class EstudianteServicio {
    
    private final EstudianteRepositorio repo;

    public EstudianteServicio(MongoDatabase db) {
        this.repo = new EstudianteRepositorio(db);
    }


    public void save(Estudiante e) throws IdException {
        repo.save(e);
    }

  
    public List<Estudiante> read() {
        return repo.read();
    }
    
    public void update(Estudiante e) throws IdException {
        repo.update(e);
    }
    
    public void delete(String idEstudiante) throws IdException {
        repo.delete(idEstudiante);
    }


    // Filtro del repositorio en el servicio, buscar por curso
    public List<Estudiante> buscarPorCursoMongo(Curso curso) {
        return repo.buscarPorCursoMongo(curso);
    }
    
    
    //Filtro del repositorio por Rango de Notas (Usando $gte y $lte en Mongo)
    public List<Estudiante> buscarPorRangoDeNota(double notaMinima, double notaMaxima) {
        return repo.buscarPorRangoDeNota(notaMinima, notaMaxima);
    }
    
    // Filtro del repositorio por Campo Anidado (Dirección de la Entidad)
    public List<Estudiante> buscarPorDireccionEntidad(String direccion) {
        return repo.buscarPorDireccionEntidad(direccion);
    }
    
  //ordenacion del reposirotio en el servico, leer ordenador por nota
    public List<Estudiante> leerOrdenadoPorNotaMongo() {
        return repo.leerOrdenadoPorNotaMongo();
    }
    
    //Ordenación del repositorio por Fecha de Nacimiento Ascendente (Más jóvenes primero)
    public List<Estudiante> leerOrdenadoPorFechaNacimientoAscendente() {
        return repo.leerOrdenadoPorFechaNacimientoAscendente();
    }
    
    //Ordenación Combinada  del repositorio (Entidad Ascendente, luego Edad Descendente)
    public List<Estudiante> leerOrdenadoPorEntidadYEdad() {
        return repo.leerOrdenadoPorEntidadYEdad();
    }
    
 // Ordenación por Nombre de Entidad Ascendente 
    public List<Estudiante> leerOrdenadoPorNombreEntidad() {
        return repo.leerOrdenadoPorNombreEntidad();
    }
    
    
    //Filtros del servicio (Java)
    
    //Estudiantes por edad 
    public List<Estudiante> filtrarPorEdadJava(int edadMinima) {
        
        List<Estudiante> todos = repo.read();
        List<Estudiante> filtrados = new ArrayList<Estudiante>();
        
       
        for (Estudiante e : todos) {
            if (e.getEdad() >= edadMinima) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }
    
    
    // Estudiantes con Nota Mínima Y Turno de Mañana 
    public List<Estudiante> filtrarPorNotaYTurnoManana(double notaMinima) {
        List<Estudiante> todos = repo.read();
        List<Estudiante> filtrados = new ArrayList<Estudiante>();
        
        for (Estudiante e : todos) {
           
            if (e.getNota() >= notaMinima && e.isTurnoManana()) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }

   //Ordenar por nombre
    public List<Estudiante> ordenarPorNombreJava() {
     
    	List<Estudiante> todos = repo.read();
        
   
        todos.sort(new NombreAscendenteComparator()); 
        
        return todos;
    }
    
    
    public EstudianteRepositorio getRepo() {
        return repo;
    }
    
    
    //Ordenar por Longitud de Nombre (más corto primero)
    public List<Estudiante> ordenarPorLongitudNombreJava() {
        List<Estudiante> todos = repo.read();
        todos.sort(new LongitudNombre()); 
        
        return todos;
    }
}