package mongoDB.Servicio;

import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.MongoDatabase;

import mongoDB.Modelo.Asignatura;
import mongoDB.Modelo.Curso;
import mongoDB.Modelo.Estudiante;
import mongoDB.Modelo.IdException;
import mongoDB.Modelo.Tipo;
import mongoDB.Repositorio.EstudianteRepositorio;

public class EstudianteServicio {
    //Recuerda que tienes que poner los filtros/ordenacion que has puesto
	//en el repositorio, estan en el punto  3. DELEGACIÓN DE QUERYS MONGO
    private final EstudianteRepositorio repo;

    public EstudianteServicio(MongoDatabase db) {
        // Inicializamos el Repositorio, que se encarga de la conexión a Mongo.
        this.repo = new EstudianteRepositorio(db);
    }

    // ----------------------------------------------------------------------
    // 1. MÉTODOS CRUD BÁSICOS (DELEGACIÓN DIRECTA)
    // ----------------------------------------------------------------------

    public void save(Estudiante e) throws IdException {
        // La validación de ID único ocurre dentro del Repositorio.
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

    // ----------------------------------------------------------------------
    // 2. FILTROS Y ORDENACIÓN EN JAVA (Procesamiento en Memoria) 💾
    // ----------------------------------------------------------------------

    // FILTRO EN JAVA: Estudiantes por Edad (Requisito del Examen)
    public List<Estudiante> filtrarPorEdadJava(int edadMinima) {
        // 1. Traemos TODOS los estudiantes a la memoria RAM.
        List<Estudiante> todos = repo.read();
        List<Estudiante> filtrados = new ArrayList<>();
        
        // 2. Aplicamos el filtro en Java (lógica de negocio).
        for (Estudiante e : todos) {
            if (e.getEdad() >= edadMinima) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }

    // ORDENACIÓN EN JAVA: Ordenar por Nombre (Requisito del Examen - Sin Lambdas)
    public List<Estudiante> ordenarPorNombreJava() {
     
        List<Estudiante> todos = repo.read();
        
        // Usamos la Clase Anónima Comparator para el ordenamiento alfabético A-Z.
        todos.sort(new java.util.Comparator<Estudiante>() {
            
            @Override
            public int compare(Estudiante e1, Estudiante e2) {
                return e1.getNombre().compareToIgnoreCase(e2.getNombre());
            }
        });
        
        return todos;
    }
    
    /**
     * FILTRO AVANZADO: Comprueba si el estudiante tiene una asignatura específica
     * Y, además, si su nota final es superior a un mínimo. (SIN BREAK)
     */
    public List<Estudiante> filtrarAprobadosProgramacion(double notaMinima) {
        
        List<Estudiante> todos = repo.read(); // Traemos todos los datos a RAM
        List<Estudiante> filtrados = new ArrayList<Estudiante>();
        
        for (Estudiante e : todos) {
            boolean tieneProgramacion = false;
            
            // Bucle anidado para recorrer la lista de Asignaturas de CADA estudiante
            if (e.getAsignatura() != null) {
                for (Asignatura asig : e.getAsignatura()) {
                    if (asig.getNombre().equalsIgnoreCase("Programación I")) {
                        tieneProgramacion = true;
                       
                    }
                }
            }
            
            // Lógica AND: Debe cumplir ambas condiciones
            if (tieneProgramacion && e.getNota() >= notaMinima) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }
    
    /**
     * ORDENACIÓN AVANZADA: Ordena por un campo calculado (Ratio Edad / Nota).
     * Los estudiantes con el ratio más alto (más edad por cada punto de nota) irán primero.
     */
    public List<Estudiante> ordenarPorRatioEdadNota() {
        List<Estudiante> todos = repo.read();
        
        todos.sort(new java.util.Comparator<Estudiante>() {
            @Override
            public int compare(Estudiante e1, Estudiante e2) {
                // Prevenir división por cero si la nota es 0
                if (e1.getNota() == 0 || e2.getNota() == 0) return 0; 
                
                // Calculamos el índice (Ratio)
                double ratio1 = (double) e1.getEdad() / e1.getNota();
                double ratio2 = (double) e2.getEdad() / e2.getNota();
                
                // Ordenamos descendente (el ratio más alto primero)
                return -Double.compare(ratio1, ratio2); 
            }
        });
        
        return todos;
    }
    
    // ----------------------------------------------------------------------
    // 3. DELEGACIÓN DE QUERYS MONGO (Exponiendo todos los filtros del Repositorio) ⚙️
    // ----------------------------------------------------------------------

    // --- Filtros Simples ---

    // Filtro por Curso (MONGO QUERY - Requisito del Examen)
    public List<Estudiante> buscarPorCursoMongo(Curso curso) {
        return repo.buscarPorCursoMongo(curso);
    }
    
    // Filtro por Nota DESC (MONGO QUERY - Requisito del Examen)
    public List<Estudiante> leerOrdenadoPorNotaMongo() {
        return repo.leerOrdenadoPorNotaMongo();
    }

    // Filtro por Turno (Boolean)
    public List<Estudiante> buscarPorTurno(boolean turnoManana) {
        return repo.buscarPorTurno(turnoManana);
    }
    
    // Filtro por Edad Mayor o Igual (MONGO QUERY)
    public List<Estudiante> filtrarPorEdadMayorOIgualMongo(int edadMinima) {
        return repo.filtrarPorEdadMayorOIgual(edadMinima);
    }
    
    // Filtro por Email Exacto (MONGO QUERY)
    public Estudiante buscarPorEmail(String email) {
        return repo.buscarPorEmail(email);
    }

    // Filtro por Tipo de Entidad Anidada (MONGO QUERY)
    public List<Estudiante> buscarPorTipoEntidad(Tipo tipo) {
        return repo.buscarPorTipoEntidad(tipo);
    }

    // Filtro por Profesor en Lista Anidada (MONGO QUERY)
    public List<Estudiante> buscarPorProfesor(String profesor) {
        return repo.buscarPorProfesor(profesor);
    }

    // Ordenación por Nombre ASC (MONGO QUERY)
    public List<Estudiante> ordenarPorNombreMongoAscendente() {
        return repo.ordenarPorNombreMongoAscendente();
    }
    
    // Ordenación por Nota ASC (MONGO QUERY)
    public List<Estudiante> ordenarPorNotaMongoAscendente() {
        return repo.ordenarPorNotaMongoAscendente();
    }

    // --- Filtros Combinados (AND/Multi-sort) ---

    // Combinado Doble: Boolean AND Enum (MONGO QUERY)
    public List<Estudiante> buscarPorTurnoYCurso(boolean esManana, Curso curso) {
        return repo.buscarPorTurnoYCurso(esManana, curso);
    }

    // Combinado Doble: Int AND String Regex (MONGO QUERY)
    public List<Estudiante> buscarPorEdadYNombre(int edadMinima, String letraInicial) {
        return repo.buscarPorEdadYNombre(edadMinima, letraInicial);
    }

    // Ordenación Doble: String AND Double (MONGO QUERY)
    public List<Estudiante> ordenarPorEntidadYNota() {
        return repo.ordenarPorEntidadYNota();
    }

    // Combinado Triple: Boolean AND Enum AND Int (MONGO QUERY)
    public List<Estudiante> buscarPorCursoEdadYTurno(Curso curso, int edadMinima, boolean esManana) {
        return repo.buscarPorCursoEdadYTurno(curso, edadMinima, esManana);
    }

    // Ordenación Triple: String AND Enum AND Double (MONGO QUERY)
    public List<Estudiante> ordenarPorEntidadCursoYNota() {
        return repo.ordenarPorEntidadCursoYNota();
    }


    
    public EstudianteRepositorio getRepo() {
        return repo;
    }
}