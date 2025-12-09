package mongoDB.Repositorio;

import java.util.ArrayList;
import java.util.List;

// LOGGING
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// MONGO DRIVER
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult; // <--- Importante para el delete
import com.mongodb.client.result.UpdateResult;

// TUS MODELOS (Sustituye esto por tus clases reales)
import mongoDB.Modelo.MiClase;         // Tu objeto principal (Ej: Estudiante, Hotel)
import mongoDB.Modelo.MiEnum;          // Tu Enum (Ej: Curso, Tipo)
import mongoDB.Modelo.ObjetoAnidado;   // Tu objeto dentro de otro (Ej: Entidad, Ubicacion)
import mongoDB.Modelo.SubItemLista;    // Objeto dentro de una lista (Ej: Asignatura)
import mongoDB.Modelo.IdException;     // Tu excepción personalizada

public class RepositorioGenerico {

    // 1. LOGGER Y CONSTANTES
    private static final Logger logger = LogManager.getLogger(RepositorioGenerico.class);
    
    // ⚠️ CAMBIA ESTO: El nombre de la colección en MongoDB
    private static final String NOMBRE_COLECCION = "nombre_coleccion"; 
    
    private final MongoCollection<Document> coleccion;
    private List<MiClase> cacheLocal; 

    // 2. CONSTRUCTOR
    public RepositorioGenerico(MongoDatabase db) {
        this.coleccion = db.getCollection(NOMBRE_COLECCION);
        // Cargamos la lista al iniciar
        this.cacheLocal = this.read(); 
    }

    // ==================================================================================
    //                                  MÉTODOS CRUD
    // ==================================================================================

    // --- CREATE (Guardar) ---
    public void save(MiClase obj) throws IdException {
        
        // 1. Verificar duplicados (Usando el ID único del objeto)
        // ⚠️ CAMBIA "idPropio" por el nombre de tu campo ID en Mongo (ej: "idHotel", "Id_Estudiante")
        Document existe = coleccion.find(new Document("idPropio", obj.getId())).first();

        if (existe != null) {
            throw new IdException("El ID " + obj.getId() + " ya existe en la base de datos.");
        }

        // 2. Convertir Java -> Documento (Usamos el método auxiliar)
        Document doc = crearDocumento(obj);

        // 3. Insertar en Mongo
        coleccion.insertOne(doc);
        
        // 4. Actualizar caché y Log
        this.cacheLocal.add(obj);
        logger.info("Guardado correctamente con ID: " + obj.getId());
    }

    // --- READ (Leer Todos) ---
    public List<MiClase> read() {
        List<MiClase> lista = new ArrayList<>();
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // Convertir Documento -> Java (Usamos el método auxiliar)
                lista.add(mapearDocumento(doc)); 
            }
        } finally {
            cursor.close();
        }
        return lista;
    }

    // --- UPDATE (Actualizar) ---
    public void update(MiClase obj) throws IdException {
        int i = 0;
        boolean encontrado = false;

        // 1. Buscar en la caché local
        while (i < cacheLocal.size() && !encontrado) {
            String idActual = cacheLocal.get(i).getId();

            if (idActual != null && idActual.equals(obj.getId())) {
                
                // Actualizar en memoria
                cacheLocal.set(i, obj); 

                // 2. Crear documento nuevo con los datos actualizados
                Document docNuevo = crearDocumento(obj);

                // 3. Reemplazar en MongoDB
                // ⚠️ CAMBIA "idPropio" por tu campo ID real
                UpdateResult resultado = coleccion.replaceOne(new Document("idPropio", obj.getId()), docNuevo);
                
                long numModificados = resultado.getModifiedCount();
                if (numModificados > 0) {
                    logger.info("Actualizado correctamente en MongoDB. Documentos modificados: " + numModificados);
                }
                
                encontrado = true;
            }
            i++;
        }

        if (!encontrado) {
            throw new IdException("ERROR: No se puede actualizar. El ID " + obj.getId() + " no existe.");
        }
    }

    // --- DELETE (Borrar) ---
    public void delete(String id) throws IdException {
        int i = 0;
        boolean encontrado = false;

        while (i < cacheLocal.size() && !encontrado) {
            String idActual = cacheLocal.get(i).getId();

            if (idActual != null && idActual.equals(id)) {
                
                // 1. Borrar de memoria
                cacheLocal.remove(i);
                
                // 2. Borrar de Mongo y recoger resultado
                // ⚠️ CAMBIA "idPropio" por tu campo ID real
                Document busqueda = new Document("idPropio", id);
                DeleteResult resultado = coleccion.deleteOne(busqueda);
                
                long numBorrados = resultado.getDeletedCount();
                
                if (numBorrados > 0)
                    logger.info("Borrado correctamente en MongoDB. Documentos borrados: " + numBorrados);
                
                encontrado = true;
            }
            i++;
        }
     
        if (!encontrado) {
            throw new IdException("ERROR: No se puede borrar. El ID " + id + " no existe.");
        }
    }

    // ==================================================================================
    //                          MÉTODOS DE BÚSQUEDA ESPECÍFICOS
    // ==================================================================================

    // ✨ NUEVO: Buscar por ID individual
    public MiClase buscarPorId(String id) {
        // ⚠️ CAMBIA "idPropio" por tu campo ID real
        Document query = new Document("idPropio", id);
        Document doc = coleccion.find(query).first();
        
        if (doc != null) {
            return mapearDocumento(doc);
        }
        return null;
    }

    // Ejemplo: Filtro por Rango (ej: precio, nota, edad)
    public List<MiClase> buscarPorRango(double min, double max) {
        List<MiClase> resultado = new ArrayList<>();
        
        // { "campoNum": { "$gte": min, "$lte": max } }
        Document rango = new Document("$gte", min).append("$lte", max);
        
        // ⚠️ CAMBIA "campoNumerico" por tu campo real (ej: "Nota", "Precio")
        Document query = new Document("campoNumerico", rango);
        
        for (Document doc : coleccion.find(query)) {
            resultado.add(mapearDocumento(doc));
        }
        return resultado;
    }

    // Ejemplo: Filtro por Atributo Simple (ej: Dirección, Nombre)
    public List<MiClase> buscarPorAtributo(String valor) {
        List<MiClase> resultado = new ArrayList<>();
        
        // ⚠️ CAMBIA "campoBusqueda" por tu campo real (ej: "Entidad.Direccion")
        Document query = new Document("campoBusqueda", valor);
        
        for (Document doc : coleccion.find(query)) {
            resultado.add(mapearDocumento(doc));
        }
        return resultado;
    }

    // Ejemplo: Ordenar
    public List<MiClase> listarOrdenado() {
        List<MiClase> resultado = new ArrayList<>();
        // -1 Descendente, 1 Ascendente
        // ⚠️ CAMBIA "campoOrden" por tu campo real
        Document sort = new Document("campoOrden", 1); 
        
        for (Document doc : coleccion.find().sort(sort)) {
            resultado.add(mapearDocumento(doc));
        }
        return resultado;
    }

    // ==================================================================================
    //                     MAPPERS (JAVA <-> BSON) - ¡LA PARTE CLAVE!
    // ==================================================================================

    /**
     * 🟢 DE JAVA A MONGO (Para save/update)
     * Maneja NULLS usando operador ternario para evitar guardar nulos indeseados.
     */
    private Document crearDocumento(MiClase obj) {
        Document doc = new Document();

        // 1. Strings (Si es null, guardamos cadena vacía "" o null si prefieres)
        doc.append("idPropio", obj.getId() != null ? obj.getId() : "");
        doc.append("nombre", obj.getNombre() != null ? obj.getNombre() : "");

        // 2. Primitivos (int, double, boolean siempre tienen valor)
        doc.append("edad", obj.getEdad());
        doc.append("nota", obj.getNota());
        doc.append("activo", obj.isActivo());

        // 3. Wrappers (Integer/Double) -> Si son null, guardamos 0
        // doc.append("numeroOpcional", obj.getNum() != null ? obj.getNum() : 0);

        // 4. ENUM -> Guardamos el String (.name()) o null si no existe
        doc.append("tipoEnum", obj.getEnum() != null ? obj.getEnum().name() : null);

        // 5. OBJETO ANIDADO (Subdocumento)
        if (obj.getObjetoAnidado() != null) {
            Document subDoc = new Document();
            // Mapeamos sus campos internos con seguridad
            subDoc.append("calle", obj.getObjetoAnidado().getCalle() != null ? obj.getObjetoAnidado().getCalle() : "");
            subDoc.append("numero", obj.getObjetoAnidado().getNumero());
            
            doc.append("ubicacion", subDoc);
        } else {
            doc.append("ubicacion", null);
        }

        // 6. LISTA DE OBJETOS (Array de documentos)
        List<Document> listaDocs = new ArrayList<>();
        if (obj.getLista() != null) {
            for (SubItemLista item : obj.getLista()) {
                Document itemDoc = new Document();
                itemDoc.append("nombreItem", item.getNombre() != null ? item.getNombre() : "");
                itemDoc.append("valorItem", item.getValor());
                listaDocs.add(itemDoc);
            }
        }
        doc.append("items", listaDocs);

        return doc;
    }

    /**
     * 🟠 DE MONGO A JAVA (Para read/find)
     * Maneja NULLS y TIPOS NUMÉRICOS para evitar caídas al leer.
     */
    private MiClase mapearDocumento(Document doc) {
        MiClase obj = new MiClase();

        // 1. Strings
        obj.setId(doc.getString("idPropio") != null ? doc.getString("idPropio") : "");
        obj.setNombre(doc.getString("nombre") != null ? doc.getString("nombre") : "");

        // 2. Números (Usar Number.class es lo más seguro para int/double)
        Number edadNum = doc.get("edad", Number.class);
        obj.setEdad(edadNum != null ? edadNum.intValue() : 0);

        Number notaNum = doc.get("nota", Number.class);
        obj.setNota(notaNum != null ? notaNum.doubleValue() : 0.0);

        // 3. Booleanos
        Boolean activo = doc.getBoolean("activo");
        obj.setActivo(activo != null ? activo : false);

        // 4. ENUM (Recuperar desde String con try-catch por seguridad)
        String enumStr = doc.getString("tipoEnum");
        if (enumStr != null) {
            try { 
                obj.setEnum(MiEnum.valueOf(enumStr.toUpperCase())); 
            } catch (IllegalArgumentException e) { 
                obj.setEnum(null); 
            }
        }

        // 5. OBJETO ANIDADO
        Document subDoc = (Document) doc.get("ubicacion");
        if (subDoc != null) {
            // Reconstruimos el objeto anidado
            String calle = subDoc.getString("calle") != null ? subDoc.getString("calle") : "";
            int numero = subDoc.getInteger("numero") != null ? subDoc.getInteger("numero") : 0;
            
            obj.setObjetoAnidado(new ObjetoAnidado(calle, numero));
        }

        // 6. LISTA DE OBJETOS
        List<Document> listaDocs = doc.getList("items", Document.class);
        if (listaDocs != null) {
            List<SubItemLista> listaJava = new ArrayList<>();
            for (Document itemDoc : listaDocs) {
                // Mapeamos cada item de la lista
                String nom = itemDoc.getString("nombreItem") != null ? itemDoc.getString("nombreItem") : "";
                // Usamos Number.class para ser seguros con doubles/integers
                double val = itemDoc.get("valorItem", Number.class) != null ? itemDoc.get("valorItem", Number.class).doubleValue() : 0.0;
                
                listaJava.add(new SubItemLista(nom, val));
            }
            obj.setLista(listaJava);
        }

        return obj;
    }
}