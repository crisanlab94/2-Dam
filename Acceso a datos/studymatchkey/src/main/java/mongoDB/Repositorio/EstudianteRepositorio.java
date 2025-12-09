package mongoDB.Repositorio;

import java.util.ArrayList;
import java.util.List;

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
    
    private static final String NOMBRE_COLECCION = "estudiantes";
    private final MongoCollection<Document> coleccion;
    private List<Estudiante> estudiantes;

    public EstudianteRepositorio(MongoDatabase db) {
        this.coleccion = db.getCollection(NOMBRE_COLECCION);
        this.estudiantes = this.read();
    }

    //Guardar estudiante en base de datos
    //Create(save)
    public void save(Estudiante e) throws IdException {
        
        //Verificación: ¿Existe ya este ID?
        Document existe = coleccion.find(new Document("Id_Estudiante", e.getId_Estudiante())).first();

        if (existe != null) {
            // si existe, lanzamos mi excepcion
            throw new IdException("El ID " + e.getId_Estudiante() + " ya está registrado en la base de datos.");
        }
        
        Document estudiantes = new Document("Id_Estudiante", e.getId_Estudiante())
                .append("Nombre", e.getNombre())
                .append("Fecha_De_Nacimiento", e.getFecha_de_nacimiento())
                .append("Email", e.getEmail())
                .append("Edad", e.getEdad())
                .append("Nota", e.getNota())
                .append("turnoMañana", e.isTurnoManana())
                .append("Curso", e.getCurso() != null ? e.getCurso().name() : null);

        // Documento Entidad
        if (e.getEntidad() != null) {
            Document entidadDoc = new Document("Tipo", e.getEntidad().getTipo() != null ? e.getEntidad().getTipo().name() : null)
                    .append("Nombre", e.getEntidad().getNombre())
                    .append("Direccion", e.getEntidad().getDireccion());
            
            estudiantes.append("Entidad", entidadDoc);
        } else {
            estudiantes.append("Entidad", null);
        }

        // Lista de asignaturas
        List<Document> listaAsignaturas = new ArrayList<Document>();
        if (e.getAsignatura() != null) {
            for (Asignatura asig : e.getAsignatura()) {
                Document asigDoc = new Document("Nombre", asig.getNombre())
                        .append("Codigo", asig.getCodigo())
                        .append("Profesor", asig.getProfesor());
                listaAsignaturas.add(asigDoc);
            }
        }
        estudiantes.append("Asignaturas", listaAsignaturas);
        
        coleccion.insertOne(estudiantes);
        
        // Añadimos a la lista local
        this.estudiantes.add(e);
        
        System.out.println("Estudiante guardado correctamente.");
    }

    //Select * Leer todo con el .find
    //---READ
    public List<Estudiante> read() {
        List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // Usamos el metodo auxiliar
                Estudiante e = mapearEstudiante(doc);
                listaEstudiantes.add(e);
            }
        } finally {
            cursor.close();
        }
        
        return listaEstudiantes;
    }

    // --- UPDATE 
    public void update(Estudiante e) throws IdException {
        int i = 0;
        boolean encontrado = false;

        while (i < estudiantes.size() && !encontrado) {
            
            String idActual = estudiantes.get(i).getId_Estudiante();

            // Comprobamos que no sea null para evitar errores
            if (idActual != null && idActual.equals(e.getId_Estudiante())) {
                
                // Actualizamos lista local
                estudiantes.set(i, e); 

                // Actualizamos base de datos
                Document docNuevo = new Document("Id_Estudiante", e.getId_Estudiante())
                        .append("Nombre", e.getNombre())
                        .append("Fecha_De_Nacimiento", e.getFecha_de_nacimiento())
                        .append("Email", e.getEmail())
                        .append("Edad", e.getEdad())
                        .append("Nota", e.getNota())
                        .append("turnoMañana", e.isTurnoManana())
                        .append("Curso", e.getCurso() != null ? e.getCurso().name() : null);

                if (e.getEntidad() != null) {
                    Document entidadDoc = new Document("Tipo", e.getEntidad().getTipo() != null ? e.getEntidad().getTipo().name() : null)
                            .append("Nombre", e.getEntidad().getNombre())
                            .append("Direccion", e.getEntidad().getDireccion());
                    docNuevo.append("Entidad", entidadDoc);
                } else {
                    docNuevo.append("Entidad", null);
                }

                List<Document> listaAsignaturas = new ArrayList<Document>();
                if (e.getAsignatura() != null) {
                    for (Asignatura asig : e.getAsignatura()) {
                        Document asigDoc = new Document("Nombre", asig.getNombre())
                                .append("Codigo", asig.getCodigo())
                                .append("Profesor", asig.getProfesor());
                        listaAsignaturas.add(asigDoc);
                    }
                }
                docNuevo.append("Asignaturas", listaAsignaturas);

               UpdateResult resultado = coleccion.replaceOne(new Document("Id_Estudiante", e.getId_Estudiante()), docNuevo);
              long numModificados =  resultado.getModifiedCount();
              if (numModificados > 0)
            	  System.out.println("Estudiante actualizado correctamente en MongoDB. Documentos modificados: " + numModificados);
                
                encontrado = true;
            }
            i++;
        }
        if (!encontrado) {
            throw new IdException("ERROR: No se puede actualizar. El estudiante con ID " + e.getId_Estudiante() + " no existe.");
        }
    }

    // --- DELETE 
    public void delete(String idEstudiante) throws IdException {
        int i = 0;
        boolean encontrado = false;

        while (i < estudiantes.size() && !encontrado) {
            
            String idActual = estudiantes.get(i).getId_Estudiante();

            if (idActual != null && idActual.equals(idEstudiante)) {
                
                estudiantes.remove(i);
                
                Document busqueda = new Document("Id_Estudiante", idEstudiante);
                coleccion.deleteOne(busqueda);
                
                encontrado = true;
            }
            i++;
        }
     
        if (!encontrado) {
            throw new IdException("ERROR: No se puede borrar. El estudiante con ID " + idEstudiante + " no existe.");
        }
    }
    
    //Filtros en el repositorio(query mongo)
    
    // Filtro repositorio mongo por Curso
    public List<Estudiante> buscarPorCursoMongo(Curso curso) {
        List<Estudiante> resultado = new ArrayList<Estudiante>();
        Document query = new Document("Curso", curso.name());
        
        FindIterable<Document> docs = coleccion.find(query);
        
        for (Document doc : docs) {
            resultado.add(mapearEstudiante(doc));
        }
        return resultado;
    }
    
    
    //filtro por id
    
 // Filtro repositorio rango de Notas (Usando $gte y $lte)
    public List<Estudiante> buscarPorRangoDeNota(double notaMinima, double notaMaxima) {
        List<Estudiante> resultado = new ArrayList<Estudiante>();
        
        // Query de Mongo: { "Nota": { "$gte": notaMinima, "$lte": notaMaxima } }
        Document rango = new Document("$gte", notaMinima)
                               .append("$lte", notaMaxima);
        
        Document query = new Document("Nota", rango);
        
        FindIterable<Document> docs = coleccion.find(query);
        
        for (Document doc : docs) {
            resultado.add(mapearEstudiante(doc));
        }
        return resultado;
    }

 // Filtro campo Anidado por Dirección de Entidad 
    public List<Estudiante> buscarPorDireccionEntidad(String direccion) {
        List<Estudiante> resultado = new ArrayList<Estudiante>();
        
        // Query de Mongo: { "Entidad.Direccion": "Calle Principal 10" }
      
        Document query = new Document("Entidad.Direccion", direccion);
        
        FindIterable<Document> docs = coleccion.find(query);
        
        for (Document doc : docs) {
            resultado.add(mapearEstudiante(doc));
        }
        return resultado;
    }
    //Ordenacion reposiotrio 
    // Ordenacion repositorio mongo por mejor nota Nota
    public List<Estudiante> leerOrdenadoPorNotaMongo() {
        List<Estudiante> resultado = new ArrayList<Estudiante>();
        // (-1) es descendente
        Document sort = new Document("Nota", -1); 
        
        FindIterable<Document> docs = coleccion.find().sort(sort);
        
        for (Document doc : docs) {
            resultado.add(mapearEstudiante(doc));
        }
        return resultado;
    }

 // Ordenación por Nombre de Entidad Ascendente (Campo Anidado)
    public List<Estudiante> leerOrdenadoPorNombreEntidad() {
        List<Estudiante> resultado = new ArrayList<Estudiante>();
        // 1 es Ascendente. Usamos Dot Notation en el sort.
        Document sort = new Document("Entidad.Nombre", 1); 
        
        FindIterable<Document> docs = coleccion.find().sort(sort);
        
        for (Document doc : docs) {
            resultado.add(mapearEstudiante(doc));
        }
        return resultado;
    }
    
 // Ordenación  por Fecha de Nacimiento Ascendente (Más jóvenes primero)
    public List<Estudiante> leerOrdenadoPorFechaNacimientoAscendente() {
        List<Estudiante> resultado = new ArrayList<Estudiante>();
        // 1 es Ascendente. Si la fecha es un String, se ordena alfabéticamente (no ideal para fechas, pero demuestra el sort).
        Document sort = new Document("Fecha_De_Nacimiento", 1); 
        
        FindIterable<Document> docs = coleccion.find().sort(sort);
        
        for (Document doc : docs) {
            resultado.add(mapearEstudiante(doc));
        }
        return resultado;
    }

    // Ordenación combinada por Nombre de Entidad Ascendente, luego por Edad Descendente
    public List<Estudiante> leerOrdenadoPorEntidadYEdad() {
        List<Estudiante> resultado = new ArrayList<Estudiante>();
        
        // Ordena primero por Entidad (A-Z) y luego los más viejos de esa Entidad van primero (-1).
        Document sort = new Document("Entidad.Nombre", 1) 
                              .append("Edad", -1);
        
        FindIterable<Document> docs = coleccion.find().sort(sort);
        
        for (Document doc : docs) {
            resultado.add(mapearEstudiante(doc));
        }
        return resultado;
    }
 
    
    
    // Metodo auxiliar para convertir Document a Estudiante y no repetir ese trozo de codigo
    private Estudiante mapearEstudiante(Document doc) {
        Estudiante e = new Estudiante();
        
        e.setId_Estudiante(doc.getString("Id_Estudiante"));
        e.setNombre(doc.getString("Nombre") != null ? doc.getString("Nombre") : "");
        e.setFecha_de_nacimiento(doc.getString("Fecha_De_Nacimiento") != null ? doc.getString("Fecha_De_Nacimiento") : "");
        e.setEmail(doc.getString("Email") != null ? doc.getString("Email") : "");
        
        //EDAD 
        Number edadNum = doc.get("Edad", Number.class);
        e.setEdad(edadNum != null ? edadNum.intValue() : 0);
        
        //Nota
        Number notaNum = doc.get("Nota", Number.class);
        e.setNota(notaNum != null ? notaNum.doubleValue() : 0.0);
        
        // Boolean
        Boolean turno = doc.getBoolean("turnoMañana");
        e.setTurnoManana(turno != null ? turno : false);
        
        //Lectura del Enum
        String cursoStr = doc.getString("Curso");
        if (cursoStr != null) {
            try { e.setCurso(Curso.valueOf(cursoStr.toUpperCase())); } catch (Exception ex) { e.setCurso(null); }
        }

        //Entidad
        Document entidadDoc = (Document) doc.get("Entidad");
        if (entidadDoc != null) {
            Tipo tipoEnum = null;
            String tipoStr = entidadDoc.getString("Tipo");
            if (tipoStr != null) {
                try { tipoEnum = Tipo.valueOf(tipoStr.toUpperCase()); } catch (Exception ex) { tipoEnum = null; }
            }
            e.setEntidad(new Entidad(
                tipoEnum,
                entidadDoc.getString("Nombre") != null ? entidadDoc.getString("Nombre") : "",
                entidadDoc.getString("Direccion") != null ? entidadDoc.getString("Direccion") : ""
            ));
        }

        //Lectura de Asignaturas(Lista)
        List<Document> asignaturasDoc =  doc.getList("Asignaturas", Document.class);
        if (asignaturasDoc != null) {
            List<Asignatura> listaAsig = new ArrayList<Asignatura>();
            for (Document asigDocItem : asignaturasDoc) {
                listaAsig.add(new Asignatura(
                    asigDocItem.getString("Nombre") != null ? asigDocItem.getString("Nombre") : "",
                    asigDocItem.getString("Codigo") != null ? asigDocItem.getString("Codigo") : "",
                    asigDocItem.getString("Profesor") != null ? asigDocItem.getString("Profesor") : ""
                ));
            }
            e.setAsignatura(listaAsig);
        }
        
        return e;
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