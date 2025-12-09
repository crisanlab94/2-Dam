package mongoDB.Repositorio;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import mongoDB.Modelo.Entidad;
import mongoDB.Modelo.Estudiante;
import mongoDB.Modelo.IdException;
import mongoDB.Modelo.Tipo;
import mongoDB.Modelo.Asignatura;
import mongoDB.Modelo.Curso;

public class EstudianteRepositorio {
	private static final Logger logger = LogManager.getLogger(EstudianteRepositorio.class);
	
	//Donde salga la llave tiene que estar escrito igual que en el Json (Base de datos)
    
    // ✨ CONSTANTE: Define el nombre de la colección a la que nos conectamos.
    private static final String NOMBRE_COLECCION = "estudiantes";
    private final MongoCollection<Document> coleccion;
    
    // ✨ CACHE LOCAL: Lista en memoria que guarda una copia de los datos.
    private List<Estudiante> estudiantes; 

    public EstudianteRepositorio(MongoDatabase db) {
        // ✨ CONEXIÓN: Inicializa la colección de MongoDB
        this.coleccion = db.getCollection(NOMBRE_COLECCION);
        // ✨ CARGA INICIAL: Llenamos la cache local con todos los datos al iniciar el programa
        this.estudiantes = this.read(); 
    }

    //Guardar estudiante en base de datos
    //Create(save)
    public void save(Estudiante e) throws IdException {
        
        // ✨ LÓGICA DE NEGOCIO (VERIFICACIÓN DE UNICIDAD)
        // 1. Buscamos en Mongo si ya existe un documento con este Id_Estudiante
        Document existe = coleccion.find(new Document("Id_Estudiante", e.getId_Estudiante())).first(); // 🔑 CLAVE BD: Id_Estudiante

        if (existe != null) {
            // si existe, lanzamos mi excepcion
            throw new IdException("El ID " + e.getId_Estudiante() + " ya está registrado en la base de datos.");
        }
        
        // ✨ Mapeo Objeto Java -> Documento BSON (Preparación para Mongo)
        Document estudiantesDoc = new Document("Id_Estudiante", e.getId_Estudiante()) // 🔑 CLAVE BD: Id_Estudiante
                .append("Nombre", e.getNombre()) // 🔑 CLAVE BD: Nombre
                .append("Fecha_De_Nacimiento", e.getFecha_de_nacimiento()) // 🔑 CLAVE BD: Fecha_De_Nacimiento
                .append("Email", e.getEmail()) // 🔑 CLAVE BD: Email
                .append("Edad", e.getEdad()) // 🔑 CLAVE BD: Edad
                .append("Nota", e.getNota()) // 🔑 CLAVE BD: Nota
                .append("turnoMañana", e.isTurnoManana()) // 🔑 CLAVE BD: turnoMañana
                .append("Curso", e.getCurso() != null ? e.getCurso().name() : null); // 🔑 CLAVE BD: Curso

        // Lógica de mapeo para Subdocumentos (Entidad)
        if (e.getEntidad() != null) {
            Document entidadDoc = new Document("Tipo", e.getEntidad().getTipo() != null ? e.getEntidad().getTipo().name() : null) // 🔑 CLAVE BD: Tipo
                    .append("Nombre", e.getEntidad().getNombre()) // 🔑 CLAVE BD: Nombre
                    .append("Direccion", e.getEntidad().getDireccion()); // 🔑 CLAVE BD: Direccion
            estudiantesDoc.append("Entidad", entidadDoc); // 🔑 CLAVE BD: Entidad
        } else {
            estudiantesDoc.append("Entidad", null); // 🔑 CLAVE BD: Entidad
        }

        // Lógica de mapeo para Listas de Documentos (Asignaturas)
        List<Document> listaAsignaturas = new ArrayList<>();
        if (e.getAsignatura() != null) {
            for (Asignatura asig : e.getAsignatura()) {
                Document asigDoc = new Document("Nombre", asig.getNombre()) // 🔑 CLAVE BD: Nombre
                        .append("Codigo", asig.getCodigo()) // 🔑 CLAVE BD: Codigo
                        .append("Profesor", asig.getProfesor()); // 🔑 CLAVE BD: Profesor
                listaAsignaturas.add(asigDoc);
            }
        }
        estudiantesDoc.append("Asignaturas", listaAsignaturas); // 🔑 CLAVE BD: Asignaturas
        
        // ✨ EJECUCIÓN: Insertamos el documento en MongoDB
        coleccion.insertOne(estudiantesDoc);
        
        // ✨ SINCRONIZACIÓN: Añadimos a la lista local para que la cache esté actualizada
        this.estudiantes.add(e);
        
        System.out.println("Estudiante guardado correctamente.");
    }

    //Select * Leer todo con el .find
    //---READ
    public List<Estudiante> read() {
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        // ✨ CURSOR: Abrimos un iterador para recorrer los resultados sin cargar todo de golpe en memoria
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) { // Mientras haya documentos pendientes de leer
                Document doc = cursor.next(); // Traemos el siguiente documento de Mongo
                
                // Usamos el metodo auxiliar para mapear el Document a objeto Java (POJO)
                Estudiante e = mapearEstudiante(doc); // <-- Mapeo ocurre aquí
                listaEstudiantes.add(e);
            }
        } finally {
            // ✨ GESTIÓN DE RECURSOS: El cursor debe cerrarse SIEMPRE para liberar la conexión de BD
            cursor.close();
        }
        
        return listaEstudiantes;
    }

    // --- UPDATE 
    public void update(Estudiante e) throws IdException {
        // Usamos variables de control para el bucle WHILE
        int i = 0;
        boolean encontrado = false;

        // Bucle WHILE: Recorremos la lista local (cache) y paramos cuando encontramos el ID
        while (i < estudiantes.size() && !encontrado) {
            
            String idActual = estudiantes.get(i).getId_Estudiante();

            // ✨ PROTECCIÓN: Comprobamos que el ID no sea nulo antes de usar .equals()
            if (idActual != null && idActual.equals(e.getId_Estudiante())) {
                
                // 1. Actualizar en lista local (sincronización en RAM)
                estudiantes.set(i, e); 

                // 2. Actualizar en base de datos (Usando el mapeador inverso)
                // Reemplazamos el documento viejo por el nuevo
                Document docNuevo = crearDocumentoDeEstudiante(e); // <-- Mapeo ocurre aquí
                
                

                UpdateResult resultado =coleccion.replaceOne(new Document("Id_Estudiante", e.getId_Estudiante()), docNuevo); // 🔑 CLAVE BD: Id_Estudiante
                long numModificados =  resultado.getModifiedCount();
                if (numModificados > 0)
              	  logger.info("Estudiante actualizado correctamente en MongoDB. Documentos modificados: " + numModificados);
                
                
                encontrado = true; // Paramos el bucle, ya encontramos el elemento
            }
            i++;
        }
     // ✨ CONTROL DE EXCEPCIÓN: Si al terminar el bucle no lo hemos encontrado, lanzamos error.
        if (!encontrado) {
            throw new IdException("ERROR: No se puede actualizar. El estudiante con ID " + e.getId_Estudiante() + " no existe");
        }
    }

    // --- DELETE 
    public void delete(String idEstudiante) throws IdException  {
        int i = 0;
        boolean encontrado = false;

        // Bucle WHILE: Recorremos la lista local y paramos cuando encontramos el ID
        while (i < estudiantes.size() && !encontrado) {
            
            String idActual = estudiantes.get(i).getId_Estudiante();

            // ✨ PROTECCIÓN: Evitamos NullPointerException
            if (idActual != null && idActual.equals(idEstudiante)) {
                
                // 1. Borramos de la lista local
                estudiantes.remove(i);
                
                // 2. Borramos de Mongo (consulta directa)
                Document busqueda = new Document("Id_Estudiante", idEstudiante); // 🔑 CLAVE BD: Id_Estudiante
                coleccion.deleteOne(busqueda);
                
                encontrado = true; // Paramos el bucle
            }
            i++;
        }
     // ✨ CONTROL DE EXCEPCIÓN: Si al terminar el bucle no lo hemos encontrado, lanzamos error.
        if (!encontrado) {
            throw new IdException("ERROR: No se puede borrar. El estudiante con ID " + idEstudiante + " no existe.");
        }
    }
    
    // ✨ Filtro repositorio mongo por Curso (Requisito del Examen - Query en Mongo)
    public List<Estudiante> buscarPorCursoMongo(Curso curso) {
        List<Estudiante> resultado = new ArrayList<>();
        // Query de Mongo: Filtra los documentos donde el campo "Curso" sea igual al valor
        Document query = new Document("Curso", curso.name()); // 🔑 CLAVE BD: Curso
        
        FindIterable<Document> docs = coleccion.find(query);
        
        for (Document doc : docs) {
            resultado.add(mapearEstudiante(doc)); // Usamos el mapeador
        }
        return resultado;
    }

    // ✨ Ordenacion repositorio mongo por mejor nota (Requisito del Examen - Query en Mongo)
    public List<Estudiante> leerOrdenadoPorNotaMongo() {
        List<Estudiante> resultado = new ArrayList<>();
        // Query de Mongo: Ordena la colección por el campo "Nota" de forma descendente (-1)
        Document sort = new Document("Nota", -1); // 🔑 CLAVE BD: Nota
        
        FindIterable<Document> docs = coleccion.find().sort(sort);
        
        for (Document doc : docs) {
            resultado.add(mapearEstudiante(doc)); // Usamos el mapeador
        }
        return resultado;
    }

 // -------------------------------------------------------------------
 // ✨ BATERÍA COMPLETA DE FILTROS Y ORDENACIONES EN MONGO (REPOSITORY)
 // -------------------------------------------------------------------

 // 1. FILTRO TIPO: Boolean (Encontrar por 'turnoMañana')
 // Usamos el filtro directo { "turnoMañana": true/false }
 public List<Estudiante> buscarPorTurno(boolean turnoManana) {
     List<Estudiante> resultado = new ArrayList<>();
     Document query = new Document("turnoMañana", turnoManana); // 🔑 CLAVE BD: turnoMañana
     
     FindIterable<Document> docs = coleccion.find(query);
     for (Document doc : docs) {
         resultado.add(mapearEstudiante(doc));
     }
     return resultado;
 }

 // 2. FILTRO TIPO: Int (Edad en Rango)
 // Usamos el operador $gte (Greater Than or Equal, Mayor o igual que)
 public List<Estudiante> filtrarPorEdadMayorOIgual(int edadMinima) {
     List<Estudiante> resultado = new ArrayList<>();
     // Query de Mongo: { "Edad": { "$gte": 20 } }
     Document query = new Document("Edad", new Document("$gte", edadMinima)); // 🔑 CLAVE BD: Edad
     
     FindIterable<Document> docs = coleccion.find(query);
     for (Document doc : docs) {
         resultado.add(mapearEstudiante(doc));
     }
     return resultado;
 }

 // 3. FILTRO TIPO: String (Busqueda exacta por Email)
 public Estudiante buscarPorEmail(String email) {
     // Solo esperamos un resultado, usamos .first()
     Document doc = coleccion.find(new Document("Email", email)).first(); // 🔑 CLAVE BD: Email
     
     if (doc != null) {
         return mapearEstudiante(doc);
     }
     return null; // Devolvemos null si no lo encuentra
 }

 // 4. FILTRO TIPO: String anidado 
 // Buscar por Entidad.Tipo (Ej: "Universidad")
 public List<Estudiante> buscarPorTipoEntidad(Tipo tipo) {
     List<Estudiante> resultado = new ArrayList<>();
     // Query de Mongo: { "Entidad.Tipo": "UNIVERSIDAD" }
     Document query = new Document("Entidad.Tipo", tipo.name()); // 🔑 CLAVE BD: Entidad.Tipo (Uso de Dot Notation)
     
     FindIterable<Document> docs = coleccion.find(query);
     for (Document doc : docs) {
         resultado.add(mapearEstudiante(doc));
     }
     return resultado;
 }

 // 5. FILTRO TIPO: Lista de Documentos ($elemMatch)
 // Buscar estudiantes que estén matriculados con un profesor específico
 public List<Estudiante> buscarPorProfesor(String profesor) {
     List<Estudiante> resultado = new ArrayList<>();
     // Query de Mongo: Busca en la lista "Asignaturas" por el campo "Profesor"
     Document query = new Document("Asignaturas.Profesor", profesor); // 🔑 CLAVE BD: Asignaturas.Profesor
     
     FindIterable<Document> docs = coleccion.find(query);
     for (Document doc : docs) {
         resultado.add(mapearEstudiante(doc));
     }
     return resultado;
 }

 // 6. ORDENACIÓN TIPO: String (Ascendente A-Z)
 public List<Estudiante> ordenarPorNombreMongoAscendente() {
     List<Estudiante> resultado = new ArrayList<>();
     // 1 es Ascendente (A-Z)
     Document sort = new Document("Nombre", 1); // 🔑 CLAVE BD: Nombre
     
     FindIterable<Document> docs = coleccion.find().sort(sort);
     for (Document doc : docs) {
         resultado.add(mapearEstudiante(doc));
     }
     return resultado;
 }

 // 7. ORDENACIÓN TIPO: Double (Descendente - Peor a Mejor Nota)
 public List<Estudiante> ordenarPorNotaMongoAscendente() {
     List<Estudiante> resultado = new ArrayList<>();
     // 1 es Ascendente (0.0 a 10.0)
     Document sort = new Document("Nota", 1); // 🔑 CLAVE BD: Nota
     
     FindIterable<Document> docs = coleccion.find().sort(sort);
     for (Document doc : docs) {
         resultado.add(mapearEstudiante(doc));
     }
     return resultado;
 }
 
//✨ FILTRO COMBINADO: Boolean (turnoMañana) AND Enum (Curso)
public List<Estudiante> buscarPorTurnoYCurso(boolean esManana, Curso curso) {
  List<Estudiante> resultado = new ArrayList<>();
  
  // Query de Mongo: La coma (,) dentro del Documento es el operador AND implícito
  Document query = new Document("turnoMañana", esManana) // 🔑 CLAVE BD: turnoMañana
                          .append("Curso", curso.name()); // 🔑 CLAVE BD: Curso
  
  FindIterable<Document> docs = coleccion.find(query);
  for (Document doc : docs) {
      resultado.add(mapearEstudiante(doc));
  }
  return resultado;
}
    
//✨ FILTRO COMBINADO: Int (Edad) AND String (Regex)
public List<Estudiante> buscarPorEdadYNombre(int edadMinima, String letraInicial) {
 List<Estudiante> resultado = new ArrayList<>();
 
 // 1. Condición de Edad: { "Edad": { "$gte": 20 } }
 Document edadFiltro = new Document("$gte", edadMinima);
 
 // 2. Condición de Nombre: Regex (e.g., /^J/i -> Empieza por 'J', insensible a mayúsculas)
 Document nombreFiltro = new Document("$regex", "^" + letraInicial)
                                .append("$options", "i");
 
 // 3. Documento Final: La coma (,) dentro del Documento es el operador AND implícito
 Document query = new Document("Edad", edadFiltro) // 🔑 CLAVE BD: Edad
                         .append("Nombre", nombreFiltro); // 🔑 CLAVE BD: Nombre
 
 FindIterable<Document> docs = coleccion.find(query);
 for (Document doc : docs) {
     resultado.add(mapearEstudiante(doc));
 }
 return resultado;
}


//✨ ORDENACIÓN COMBINADA: String (Entidad) y Double (Nota)
public List<Estudiante> ordenarPorEntidadYNota() {
 List<Estudiante> resultado = new ArrayList<>();
 
 // 1. Campo principal de ordenación: Entidad.Nombre (1 = Ascendente A-Z)
 // 2. Campo secundario de ordenación: Nota (-1 = Descendente Mayor a Menor)
 Document sort = new Document("Entidad.Nombre", 1) // 🔑 CLAVE BD: Entidad.Nombre
                         .append("Nota", -1); // 🔑 CLAVE BD: Nota
 
 FindIterable<Document> docs = coleccion.find().sort(sort);
 for (Document doc : docs) {
     resultado.add(mapearEstudiante(doc));
 }
 return resultado;
}

//✨ FILTRO TRIPLE: Boolean (Turno) AND Enum (Curso) AND Int (Edad >= X)
public List<Estudiante> buscarPorCursoEdadYTurno(Curso curso, int edadMinima, boolean esManana) {
 List<Estudiante> resultado = new ArrayList<>();
 
 Document edadFiltro = new Document("$gte", edadMinima);
 
 // 2. Documento Final: Todos los criterios separados por comas (AND implícito)
 Document query = new Document("Curso", curso.name()) // 🔑 CLAVE BD: Curso
                         .append("Edad", edadFiltro) // 🔑 CLAVE BD: Edad
                         .append("turnoMañana", esManana); // 🔑 CLAVE BD: turnoMañana
 
 FindIterable<Document> docs = coleccion.find(query);
 for (Document doc : docs) {
     resultado.add(mapearEstudiante(doc));
 }
 return resultado;
}


//✨ ORDENACIÓN TRIPLE: Entidad (String) THEN Curso (Enum) THEN Nota (Double)
public List<Estudiante> ordenarPorEntidadCursoYNota() {
 List<Estudiante> resultado = new ArrayList<>();
 
 // 1. Campo principal: Entidad.Nombre (1 = Ascendente A-Z)
 // 2. Campo secundario: Curso (1 = Ascendente por valor de String/Enum)
 // 3. Campo terciario: Nota (-1 = Descendente, Mejores notas primero)
 Document sort = new Document("Entidad.Nombre", 1) // 🔑 CLAVE BD: Entidad.Nombre
                         .append("Curso", 1) // 🔑 CLAVE BD: Curso
                         .append("Nota", -1); // 🔑 CLAVE BD: Nota
 
 FindIterable<Document> docs = coleccion.find().sort(sort);
 for (Document doc : docs) {
     resultado.add(mapearEstudiante(doc));
 }
 return resultado;
}
    // -------------------------------------------------------------------
    // ✨ MÉTODOS AUXILIARES PRIVADOS (Mappers Inverso y Directo)
    // -------------------------------------------------------------------
    
    // Método auxiliar 1: Mapeo Documento -> Objeto (Para READ, Filtros, Ordenación)
    private Estudiante mapearEstudiante(Document doc) {
        // ✨ ROL: Traduce el BSON de Mongo a nuestro objeto Java Estudiante
        Estudiante e = new Estudiante();
        
        e.setId_Estudiante(doc.getString("Id_Estudiante")); // 🔑 CLAVE BD: Id_Estudiante
        e.setNombre(doc.getString("Nombre") != null ? doc.getString("Nombre") : ""); // 🔑 CLAVE BD: Nombre
        e.setFecha_de_nacimiento(doc.getString("Fecha_De_Nacimiento") != null ? doc.getString("Fecha_De_Nacimiento") : ""); // 🔑 CLAVE BD: Fecha_De_Nacimiento
        e.setEmail(doc.getString("Email") != null ? doc.getString("Email") : ""); // 🔑 CLAVE BD: Email
        
        // Conversión de tipos (Number a int/double)
        Number edadNum = doc.get("Edad", Number.class); // 🔑 CLAVE BD: Edad
        e.setEdad(edadNum != null ? edadNum.intValue() : 0);
        Number notaNum = doc.get("Nota", Number.class); // 🔑 CLAVE BD: Nota
        e.setNota(notaNum != null ? notaNum.doubleValue() : 0.0);
        
        // Conversión de booleanos y Enums
        Boolean turno = doc.getBoolean("turnoMañana"); // 🔑 CLAVE BD: turnoMañana
        e.setTurnoManana(turno != null ? turno : false);
        String cursoStr = doc.getString("Curso"); // 🔑 CLAVE BD: Curso
        if (cursoStr != null) {
            try { e.setCurso(Curso.valueOf(cursoStr.toUpperCase())); } catch (Exception ex) { e.setCurso(null); }
        }

        // Mapeo del Subdocumento (Entidad)
        Document entidadDoc = (Document) doc.get("Entidad"); // 🔑 CLAVE BD: Entidad
        if (entidadDoc != null) {
            Tipo tipoEnum = null;
            String tipoStr = entidadDoc.getString("Tipo"); // 🔑 CLAVE BD: Tipo
            if (tipoStr != null) {
                try { tipoEnum = Tipo.valueOf(tipoStr.toUpperCase()); } catch (Exception ex) { tipoEnum = null; }
            }
            e.setEntidad(new Entidad(
                tipoEnum,
                entidadDoc.getString("Nombre") != null ? entidadDoc.getString("Nombre") : "", // 🔑 CLAVE BD: Nombre
                entidadDoc.getString("Direccion") != null ? entidadDoc.getString("Direccion") : "" // 🔑 CLAVE BD: Direccion
            ));
        }
        /** Si todo fuera string o int
         * // Mapeo del Subdocumento (Entidad)
Document entidadDoc = (Document) doc.get("Entidad"); // 🔑 CLAVE BD: Entidad

if (entidadDoc != null) {
    
    // ✨ CÓDIGO MODIFICADO: Leemos directamente el String, sin conversión a Enum.
    String tipoStr = entidadDoc.getString("Tipo"); // 🔑 CLAVE BD: Tipo
    String nombreStr = entidadDoc.getString("Nombre"); // 🔑 CLAVE BD: Nombre
    String direccionStr = entidadDoc.getString("Direccion"); // 🔑 CLAVE BD: Direccion

    e.setEntidad(new Entidad(
        // Pasamos el String leído directamente al constructor
        tipoStr != null ? tipoStr : "", 
        nombreStr != null ? nombreStr : "", 
        direccionStr != null ? direccionStr : ""
    ));
    
    /* -- ELIMINAMOS ESTA COMPROBACIÓN COMPLEJA --
    Tipo tipoEnum = null;
    if (tipoStr != null) {
        try { tipoEnum = Tipo.valueOf(tipoStr.toUpperCase()); } catch (Exception ex) { tipoEnum = null; }
    }
    */

         

        // Mapeo de la Lista de Documentos (Asignaturas)
        List<Document> asignaturasDoc = (List<Document>) doc.get("Asignaturas"); // 🔑 CLAVE BD: Asignaturas
        if (asignaturasDoc != null) {
            List<Asignatura> listaAsig = new ArrayList<>();
            for (Document asigDocItem : asignaturasDoc) {
                listaAsig.add(new Asignatura(
                    asigDocItem.getString("Nombre") != null ? asigDocItem.getString("Nombre") : "", // 🔑 CLAVE BD: Nombre
                    asigDocItem.getString("Codigo") != null ? asigDocItem.getString("Codigo") : "", // 🔑 CLAVE BD: Codigo
                    asigDocItem.getString("Profesor") != null ? asigDocItem.getString("Profesor") : "" // 🔑 CLAVE BD: Profesor
                ));
            }
            e.setAsignatura(listaAsig);
        }
        
        return e;
    }
    
    // Método auxiliar 2: Mapeo Objeto -> Documento (Para SAVE, UPDATE)
    private Document crearDocumentoDeEstudiante(Estudiante e) {
        // ✨ ROL: Traduce el objeto Java Estudiante al Documento BSON que Mongo entiende
        Document doc = new Document("Id_Estudiante", e.getId_Estudiante()) // 🔑 CLAVE BD: Id_Estudiante
                .append("Nombre", e.getNombre()) // 🔑 CLAVE BD: Nombre
                .append("Fecha_De_Nacimiento", e.getFecha_de_nacimiento()) // 🔑 CLAVE BD: Fecha_De_Nacimiento
                .append("Email", e.getEmail()) // 🔑 CLAVE BD: Email
                .append("Edad", e.getEdad()) // 🔑 CLAVE BD: Edad
                .append("Nota", e.getNota()) // 🔑 CLAVE BD: Nota
                .append("turnoMañana", e.isTurnoManana()) // 🔑 CLAVE BD: turnoMañana
                .append("Curso", e.getCurso() != null ? e.getCurso().name() : null); // 🔑 CLAVE BD: Curso

        // Mapeo del Subdocumento (Entidad)
        if (e.getEntidad() != null) {
            Document entidadDoc = new Document("Tipo", e.getEntidad().getTipo() != null ? e.getEntidad().getTipo().name() : null) // 🔑 CLAVE BD: Tipo
                    .append("Nombre", e.getEntidad().getNombre()) // 🔑 CLAVE BD: Nombre
                    .append("Direccion", e.getEntidad().getDireccion()); // 🔑 CLAVE BD: Direccion
            doc.append("Entidad", entidadDoc); // 🔑 CLAVE BD: Entidad
        } else {
            doc.append("Entidad", null); // 🔑 CLAVE BD: Entidad
        }

        // Mapeo de la Lista de Documentos (Asignaturas)
        List<Document> listaAsignaturas = new ArrayList<>();
        if (e.getAsignatura() != null) {
            for (Asignatura asig : e.getAsignatura()) {
                Document asigDoc = new Document("Nombre", asig.getNombre()) // 🔑 CLAVE BD: Nombre
                        .append("Codigo", asig.getCodigo()) // 🔑 CLAVE BD: Codigo
                        .append("Profesor", asig.getProfesor()); // 🔑 CLAVE BD: Profesor
                listaAsignaturas.add(asigDoc);
            }
        }
        doc.append("Asignaturas", listaAsignaturas); // 🔑 CLAVE BD: Asignaturas
        
        return doc;
    }


    // -------------------------------------------------------------------
    // GETTERS Y SETTERS
    // -------------------------------------------------------------------
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