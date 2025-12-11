package mongoDB.Repositorio;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Ejemplos {
	

	// --- Simulación de clases necesarias ---
	// Asume que estas clases existen en tu proyecto
	class Estudiante {
	    String id;
	    String nombre;
	    Curso curso;
	    // ... más campos
	    @Override
	    public String toString() {
	        return "Estudiante{" + "nombre='" + nombre + '\'' + ", curso=" + curso + '}';
	    }
	}
	enum Curso {
	    PRIMERO, SEGUNDO, TERCERO, CUARTO
	}
	// ----------------------------------------

	/**
	 * Clase que simula el acceso a datos para la colección 'estudiantes' en MongoDB.
	 */
	public class EstudianteDao {

	    // Simulación de la colección de MongoDB (debe ser inyectada en un proyecto real)
	    private final MongoCollection<Document> coleccion;

	    public EstudianteDao(MongoCollection<Document> coleccion) {
	        this.coleccion = coleccion;
	    }

	    /**
	     * Mapea un Document de MongoDB a la entidad Estudiante (Simulación).
	     */
	    private Estudiante mapearEstudiante(Document doc) {
	        Estudiante estudiante = new Estudiante();
	        estudiante.id = doc.getObjectId("_id").toString();
	        estudiante.nombre = doc.getString("Nombre");
	        // Asume que el campo Curso es un String que coincide con el enum
	        try {
	            estudiante.curso = Curso.valueOf(doc.getString("Curso"));
	        } catch (IllegalArgumentException | NullPointerException e) {
	            estudiante.curso = null; 
	        }
	        return estudiante;
	    }


	    // =========================================================================
	    // ✨ EJEMPLO 1: FILTRO POR EXISTENCIA DE CAMPO ($exists)
	    // =========================================================================
	    /**
	     * Busca estudiantes que tienen o no tienen una Entidad registrada.
	     * Query de Mongo: { "Entidad": { "$exists": true } } o { "Entidad": { "$exists": false } }
	     */
	    public List<Estudiante> buscarPorExistenciaDeEntidad(boolean debeExistir) {
	        List<Estudiante> resultado = new ArrayList<>();
	        
	        // Document query = new Document("Entidad", new Document("$exists", debeExistir));
	        Document query = new Document("Entidad", new Document("$exists", debeExistir));
	        
	        FindIterable<Document> docs = coleccion.find(query);
	        for (Document doc : docs) {
	            resultado.add(mapearEstudiante(doc));
	        }
	        return resultado;
	    }


	    // =========================================================================
	    // ✨ EJEMPLO 2: FILTRO NO CONTENIDO EN LA LISTA ($nin)
	    // =========================================================================
	    /**
	     * Busca estudiantes que NO están en los cursos proporcionados (ej. ni PRIMERO ni SEGUNDO).
	     * Query de Mongo: { "Curso": { "$nin": ["PRIMERO", "SEGUNDO"] } }
	     */
	    public List<Estudiante> buscarExcluyendoCursos(List<Curso> cursosAExcluir) {
	        List<Estudiante> resultado = new ArrayList<>();
	        
	        // Creamos la lista de nombres de cursos (String) para Mongo
	        List<String> nombresCursos = new ArrayList<>();
	        for (Curso c : cursosAExcluir) {
	            nombresCursos.add(c.name());
	        }
	        
	        // Document query = new Document("Curso", new Document("$nin", nombresCursos));
	        Document query = new Document("Curso", new Document("$nin", nombresCursos));
	        
	        FindIterable<Document> docs = coleccion.find(query);
	        for (Document doc : docs) {
	            resultado.add(mapearEstudiante(doc));
	        }
	        return resultado;
	    }

	    // =========================================================================
	    // ✨ EJEMPLO 3: FILTRO OR COMPUESTO Y ORDENACIÓN ($or, $gte)
	    // =========================================================================
	    /**
	     * Busca estudiantes de TERCERO O con Nota sobresaliente (>= 9.5).
	     * Luego ordena por Nota descendente.
	     * Query de Mongo: 
	     * { "$or": [ { "Curso": "TERCERO" }, { "Nota": { "$gte": 9.5 } } ] }
	     * Orden: { "Nota": -1 }
	     */
	    public List<Estudiante> buscarPorCursoOuNotaSobresaliente() {
	        List<Estudiante> resultado = new ArrayList<>();

	        // 1. Construir la cláusula OR: { "Curso": "TERCERO" }
	        Document filtroCurso = new Document("Curso", Curso.TERCERO.name());
	        
	        // 2. Construir la cláusula OR: { "Nota": { "$gte": 9.5 } }
	        Document filtroNota = new Document("Nota", new Document("$gte", 9.5));

	        // 3. Combinar en el operador $or
	        List<Document> orClauses = new ArrayList<>();
	        orClauses.add(filtroCurso);
	        orClauses.add(filtroNota);

	        // Documento final de la query
	        Document query = new Document("$or", orClauses);
	        
	        // Documento de ordenación: { "Nota": -1 } (Descendente)
	        Document sort = new Document("Nota", -1);

	        FindIterable<Document> docs = coleccion.find(query).sort(sort);
	        for (Document doc : docs) {
	            resultado.add(mapearEstudiante(doc));
	        }
	        return resultado;
	    }

	    // =========================================================================
	    // ✨ EJEMPLO 4: FILTRO AND IMPLÍCITO Y ORDENACIÓN (Rango)
	    // =========================================================================
	    /**
	     * Busca estudiantes en Curso TERCERO, turnoMañana=true, y Nota >= 8.0.
	     * Ordena por Edad ascendente.
	     * Query de Mongo: 
	     * { "Curso": "TERCERO", "turnoMañana": true, "Nota": { "$gte": 8.0 } }
	     * Orden: { "Edad": 1 }
	     */
	    public List<Estudiante> buscarPorCursoTurnoYNota() {
	        List<Estudiante> resultado = new ArrayList<>();

	        // 1. Construir la query AND implícita (separada por comas)
	        Document query = new Document()
	            .append("Curso", Curso.TERCERO.name())
	            .append("turnoMañana", true)
	            .append("Nota", new Document("$gte", 8.0)); // Filtro de Rango
	        
	        // 2. Documento de ordenación: { "Edad": 1 } (Ascendente)
	        Document sort = new Document("Edad", 1);

	        FindIterable<Document> docs = coleccion.find(query).sort(sort);
	        for (Document doc : docs) {
	            resultado.add(mapearEstudiante(doc));
	        }
	        return resultado;
	    }
	}

}
