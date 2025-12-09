package mongoDB.Repositorio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;

import mongoDB.Modelo.Coordenadas;
import mongoDB.Modelo.Habitaciones;
import mongoDB.Modelo.Hotel;
import mongoDB.Modelo.Tipo;
import mongoDB.Modelo.Ubicacion;

public class HotelRepositorio {
	
	
    private static final Logger logger = LogManager.getLogger(HotelRepositorio.class);
	
    private static final String NOMBRE_COLECCION = "hoteles";
    private final MongoCollection<Document> coleccion;
    
    // ✨ CACHE LOCAL: Lista en memoria que guarda una copia de los datos.
    private List<Hotel> hoteles; 

    public HotelRepositorio(MongoDatabase db) {
        // ✨ CONEXIÓN: Inicializa la colección de MongoDB
        this.coleccion = db.getCollection(NOMBRE_COLECCION);
        // ✨ CARGA INICIAL: Llenamos la cache local con todos los datos al iniciar el programa
        this.hoteles = this.read(); 
    }

    
    //Guardar un hotel en base de datos
    //Create(save)
    public void save(Hotel h) {
        
        // ✨ LÓGICA DE NEGOCIO (VERIFICACIÓN DE UNICIDAD)
        // 1. Buscamos en Mongo si ya existe un documento con este idHotel
        Document existe = coleccion.find(new Document("idHotel", h.getIdHotel())).first(); 

        if (existe != null) {
            // Si existe, logueamos el error y cancelamos la operación
            logger.error("El ID " + h.getIdHotel() + " ya está registrado en la base de datos. Operación cancelada.");
            return; 
        }
        
        // ✨ MAPEO Y CREACIÓN DEL DOCUMENTO (Usamos el método auxiliar)
        Document hotelDoc = crearDocumento(h);
        
        // ✨ EJECUCIÓN: Insertamos el documento en MongoDB
        coleccion.insertOne(hotelDoc);
        
        // ✨ SINCRONIZACIÓN: Añadimos a la lista local
        this.hoteles.add(h);
        
        logger.info("Hotel guardado correctamente.");
    }
    
    //Select * Leer todo con el .find
    //---READ
    public List<Hotel> read() {
        List<Hotel> listaHoteles = new ArrayList<Hotel>();
        // ✨ CURSOR: Abrimos un iterador para recorrer los resultados sin cargar todo de golpe en memoria
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) { // Mientras haya documentos pendientes de leer
                Document doc = cursor.next(); // Traemos el siguiente documento de Mongo
                
                // Usamos el metodo auxiliar para mapear el Document a objeto Java (POJO)
                Hotel h = mapearHotel(doc); // <-- Mapeo ocurre aquí
                listaHoteles.add(h);
            }
        } finally {
            // ✨ GESTIÓN DE RECURSOS: El cursor debe cerrarse SIEMPRE para liberar la conexión de BD
            cursor.close();
        }
        
        return listaHoteles;
    }
    
    // --- UPDATE
    public void update(Hotel h) {
        int i = 0;
        boolean encontrado = false;

        while (i < hoteles.size() && !encontrado) {
            
            String idActual = hoteles.get(i).getIdHotel();

            if (idActual != null && idActual.equals(h.getIdHotel())) {
                
                hoteles.set(i, h); 

                Document docNuevo = crearDocumento(h);

                UpdateResult resultado = coleccion.replaceOne(new Document("idHotel", h.getIdHotel()), docNuevo);
                long numModificados =  resultado.getModifiedCount();
                
                if (numModificados > 0)
                    logger.info("Hotel actualizado correctamente en MongoDB. Documentos modificados: " + numModificados);
                
                encontrado = true;
            }
            i++;
        }
        if (!encontrado) {
            logger.error("ERROR: No se puede actualizar. El hotel con ID " + h.getIdHotel() + " no existe.");
        }
    }
    
    // --- DELETE
    public void delete(String idHotel) {
        int i = 0;
        boolean encontrado = false;

        while (i < hoteles.size() && !encontrado) {
            
            String idActual = hoteles.get(i).getIdHotel();

            if (idActual != null && idActual.equals(idHotel)) {
                
                hoteles.remove(i);
                
                Document busqueda = new Document("idHotel", idHotel);
                DeleteResult resultado = coleccion.deleteOne(busqueda);
                long numBorrados = resultado.getDeletedCount();
                
                if (numBorrados > 0)
                    logger.info("Hotel borrado correctamente en MongoDB. Documentos borrados: " + numBorrados);
                
                encontrado = true;
            }
            i++;
        }
     
        if (!encontrado) {
            logger.error("ERROR: No se puede borrar. El hotel con ID " + idHotel + " no existe.");
        }
    }
    
    // Filtro principal: Buscar por ID
    public Hotel filterById(String idHotel) { 
        // Creamos el filtro
        Document query = new Document("idHotel", idHotel);
        
        // Buscamos en la base de datos
        Document docHotel = coleccion.find(query).first();

        // Si encontramos algo, lo mapeamos a objeto Java
        if (docHotel != null) {
            return mapearHotel(docHotel);
        } 
        
        // Si no, devolvemos null
        return null;
    }
    
    
    //Busqueda con filtro combinado
    //5 estrellas y madrid
    //admite mascotas y madrid
 // Método en HotelRepositorio
    public List<Hotel> recuperarHotelesMadridFiltro() {
        List<Hotel> resultado = new ArrayList<>();
        
        // Filtro OR: Estrellas=5 O AdmiteMascotas=true
        Document orFilter = new Document("$or", Arrays.asList(
            new Document("estrellas", 5),
            new Document("admiteMascotas", true)
        ));
        
        // Filtro AND: Ciudad = Madrid
        Document andFilter = new Document("ubicacion.ciudad", "Madrid");
        
        // Combinamos OR y AND (AND es implícito al poner ambos en el documento raíz)
        Document query = new Document("ubicacion.ciudad", "Madrid").append("$or", Arrays.asList(
            new Document("estrellas", 5),
            new Document("admiteMascotas", true)
        ));

        FindIterable<Document> docs = coleccion.find(query);
        for (Document doc : docs) {
            resultado.add(mapearHotel(doc));
        }
        return resultado;
    }
    
    
    /**
     * Filtro complejo de tres condiciones:
     * Estrellas = 4 AND (Mascotas = true OR Precio Doble Estándar <= 115.0)
     *
     * Utiliza $elemMatch para asegurar que la condición del precio se aplica solo a la habitación "Doble Estándar".
     */
    public List<Hotel> buscarHotelesFiltroComplejo() {
        List<Hotel> resultado = new ArrayList<>();

        // 1. Condición del Precio Máximo (Para la parte OR)
        // Busca si existe AL MENOS una habitación que sea 'DOBLE_ESTÁNDAR' Y su precio sea <= 115.0
        Document precioMaxMatch = new Document("$elemMatch", 
            new Document("tipo", Tipo.DOBLE_ESTÁNDAR.name()).append("precio", new Document("$lte", 115.0))
        );

        // 2. Construcción del filtro $OR: (Admita Mascotas O Tenga Hab. Doble Estándar Barata)
        Document orFilter = new Document("$or", Arrays.asList(
            new Document("admiteMascotas", true), // Opción A: Admite mascotas
            new Document("habitaciones", precioMaxMatch) // Opción B: Habitación doble barata (usando el $elemMatch)
        ));

        // 3. Construcción del filtro $AND (implícito): Estrellas = 4 Y (OR anterior)
        Document query = new Document("estrellas", 4).append("$and", Arrays.asList(orFilter));
        
        // NOTA: Para simplificar, la estructura es: { "estrellas": 4, "$or": [...] }
        // En lugar de usar $and, usamos la estructura implícita de coma para el AND:
        Document querySimple = new Document("estrellas", 4).append("$or", Arrays.asList(
            new Document("admiteMascotas", true), 
            new Document("habitaciones", precioMaxMatch)
        ));


        logger.info("Filtro ejecutado (Complejo 3 Cond.): {}", querySimple.toJson());

        FindIterable<Document> docs = coleccion.find(querySimple);
        for (Document doc : docs) {
            resultado.add(mapearHotel(doc));
        }
        return resultado;
    }
    
    
   
    
    //filtro or
    //Filtro simple usando el operador $OR para buscar hoteles
    //que tengan 3 estrellas O 4 estrellas.
    
   public List<Hotel> buscarHotelesPor3O4Estrellas() {
       List<Hotel> resultado = new ArrayList<>();

       // Construcción del filtro: (Estrellas == 3) OR (Estrellas == 4)
       Document query = new Document("$or", Arrays.asList(
           new Document("estrellas", 3), 
           new Document("estrellas", 4) 
       ));

       logger.info("Filtro ejecutado (OR simple 3* O 4*): {}", query.toJson());

       FindIterable<Document> docs = coleccion.find(query);
       for (Document doc : docs) {
           resultado.add(mapearHotel(doc));
       }
       return resultado;
   }
    
    //Contar hoteles con Suite Junior
    //Obtener el número total de hoteles que tienen al menos una habitación del tipo "Suite Junior".

// Código corregido para HotelRepositorio.java

   public long contarHotelesSuiteJunior() {
       
       // ❌ Antes: String tipoHab = Tipo.SUITE_JUNIOR.name(); // Genera "SUITE_JUNIOR"
       // ✅ Ahora: Adaptamos el Enum de vuelta al formato del JSON: "Suite Junior"
       // Esto asume que el método mapearHotel es el único que maneja la conversión a mayúsculas/guiones bajos.
       
       // Usamos el nombre legible del Enum (o el nombre tal cual está en el JSON)
       String tipoHab = "Suite Junior"; 

       // 1. Ejecutar la agregación ($match y $count)
       Document result = coleccion.aggregate(Arrays.asList(
           // El filtro ahora busca el valor exacto del JSON: "Suite Junior"
           new Document("$match", new Document("habitaciones.tipo", tipoHab)),
           new Document("$count", "totalHoteles")
       )).first();
       
       if (result != null) {
           Number count = result.get("totalHoteles", Number.class);
           return count != null ? count.longValue() : 0L;
       }
       
       return 0L;
   }
    
  //añadir nueva habitacion($push --> solo para lista)
  //Añadir una nueva habitación de tipo "Penthouse" al hotel con ID dado.
  public UpdateResult anadirHabitacion(String idHotel, Habitaciones nuevaHab) {
          
      // String idHotel: Es el criterio de búsqueda (query). Especifica dónde realizar la operación (en qué hotel).
      // Habitaciones nuevaHab: Es el dato de actualización (update). Contiene la información que quieres añadir (la nueva habitación).
      
      //elemento añadir es lo que mapeo
      // Mapeamos el objeto Habitaciones a Document
      Document habDoc = new Document("tipo", nuevaHab.getTipo().name()) //pongo .name porque es enum
                        .append("precio", nuevaHab.getPrecio())
                        .append("capacidad", nuevaHab.getCapacidad())
                        .append("disponible", nuevaHab.isDisponible());

      // 2. Construimos la instrucción $push
      Document update = new Document("$push", new Document("habitaciones", habDoc));
      
      // 3. Ejecutamos la actualización
      UpdateResult resultado = coleccion.updateOne(new Document("idHotel", idHotel), update);
      
      // 4. Logueamos el resultado
      if (resultado.getModifiedCount() > 0) {
          logger.info("Hotel ID {} actualizado con éxito. Nueva habitación '{}' añadida al array.", idHotel, nuevaHab.getTipo().name());
      } else if (resultado.getMatchedCount() > 0) {
          logger.warn("Hotel ID {} encontrado, pero la operación de añadir no resultó en un cambio.", idHotel);
      } else {
          logger.error(" ERROR al añadir habitación. No se encontró el hotel con ID {}.", idHotel);
      }
      
      return resultado;
      // Para probar: usa idHotel="h101" y la nueva Habitación con Tipo.PENTHOUSE
  }
    
    //actulizar campos simples(del documento principal)
   
    public UpdateResult actualizarDatosBasicos(String idHotel, String nuevoNombre, int nuevasEstrellas) {
        
        Document filter = new Document("idHotel", idHotel);
        Document updateFields = new Document()
                                    .append("nombre", nuevoNombre)
                                    .append("estrellas", nuevasEstrellas);
        Document update = new Document("$set", updateFields);

        logger.info("Intentando actualizar datos básicos del hotel ID: {}", idHotel);

        // 1. Ejecutar la actualización
        UpdateResult resultado = coleccion.updateOne(filter, update);
        
        // 2. Loguear el resultado
        if (resultado.getModifiedCount() > 0) {
            logger.info(" Hotel ID {} actualizado con éxito. Documentos modificados: {}", idHotel, resultado.getModifiedCount());
        } else if (resultado.getMatchedCount() > 0) {
            logger.warn("Hotel ID {} encontrado, pero no se realizaron cambios (los datos ya eran correctos).", idHotel);
        } else {
            logger.error("ERROR al actualizar. No se encontró el hotel con ID {}.", idHotel);
        }

        return resultado;
    }
    
    //Actualizar documento anidado
    public UpdateResult actualizarUbicacionCompleta(
    	    String idHotel, 
    	    String newCalle, 
    	    int newNumero, 
    	    String newCP, 
    	    double newLat, 
    	    double newLon) {
    	    
    	    // 1. Criterio de Búsqueda (Query)
    	    Document filter = new Document("idHotel", idHotel);

    	    // 2. Operador de Actualización ($set y Notación de Punto)
    	    Document updateFields = new Document()
    	        // Nivel 1: Ubicacion
    	        .append("ubicacion.calle", newCalle)
    	        .append("ubicacion.numero", newNumero)
    	        .append("ubicacion.codigoPostal", newCP)
    	        // Nivel 2: Coordenadas
    	        .append("ubicacion.coordenadas.lat", newLat) 
    	        .append("ubicacion.coordenadas.lon", newLon); 
    	        
    	    Document update = new Document("$set", updateFields);

    	    logger.info("Intentando actualizar ubicación y coordenadas del hotel ID: {}", idHotel);

    	    // 3. Ejecución
    	    UpdateResult resultado = coleccion.updateOne(filter, update);
    	    
    	    // 4. Loguear el resultado
    	    if (resultado.getModifiedCount() > 0) {
    	        logger.info("Ubicación y Coordenadas del Hotel ID {} actualizadas con éxito. Documentos modificados: {}", idHotel, resultado.getModifiedCount());
    	    } else if (resultado.getMatchedCount() > 0) {
    	        logger.warn("Hotel ID {} encontrado, pero no se realizaron cambios en la ubicación.", idHotel);
    	    } else {
    	        logger.error("ERROR al actualizar ubicación. No se encontró el hotel con ID {}.", idHotel);
    	    }

    	    return resultado;
    	}
    
 // Actualizacion masiva aupdateMany
 // Actualizar el código postal de todos los hoteles en la calle "Gran Vía" a "28013".
 public UpdateResult actualizarCPGranVia() {
     
     Document filter = new Document("ubicacion.calle", "Gran Vía");
     
     // $set: Establece el nuevo valor en el campo anidado
     Document update = new Document("$set", new Document("ubicacion.codigoPostal", "28013"));
     
     // 1. Ejecutar la actualización masiva
     UpdateResult resultado = coleccion.updateMany(filter, update);

     // 2. Loguear el resultado
     long modificados = resultado.getModifiedCount();
     long coincidentes = resultado.getMatchedCount();

     if (modificados > 0) {
         logger.info("Actualización masiva de CP en la calle 'Gran Vía' completada. Hoteles modificados: {}", modificados);
     } else if (coincidentes > 0) {
         logger.warn("Hoteles de la calle 'Gran Vía' encontrados ({} coincidentes), pero el CP ya era '28013' y no se requirió modificación.", coincidentes);
     } else {
         logger.error(" ERROR: No se encontró ningún hotel en la calle 'Gran Vía' para actualizar (0 documentos coincidentes).");
     }
     
     return resultado;
 }
    
//Actualizacion posicion en array($)
//Localiza el hotel con ID dado y actualiza el precio de la habitación de tipo "Individual" al nuevoPrecio.

public UpdateResult actualizarPrecioHabitacionIndividual(String idHotel, double nuevoPrecio) {
  
  // Usamos el Enum para asegurar la coincidencia exacta con el tipo guardado en la BD
  String tipoHabitacion = Tipo.INDIVIDUAL.name(); 
  
  // 1. Filtro: Localiza el documento (por ID) Y nos aseguramos de que exista una habitación de tipo INDIVIDUAL
  Document filter = new Document("idHotel", idHotel)
                    .append("habitaciones.tipo", tipoHabitacion);

  // 2. Usamos el operador posicional '$' para actualizar el campo 'precio'
  // $set: Indica la operación de establecer un nuevo valor.
  // Dólar ($): Se refiere a la posición dentro del array que coincidió con el filtro ('Individual').
  Document update = new Document("$set", new Document("habitaciones.$.precio", nuevoPrecio));
  
  logger.info("Intentando actualizar el precio de la habitación '{}' en el hotel ID: {} al precio: {}", 
      tipoHabitacion, idHotel, nuevoPrecio);

  // 3. Ejecutar la actualización
  UpdateResult resultado = coleccion.updateOne(filter, update);
  
  // 4. Loguear el resultado (Inspeccionando el UpdateResult)
  if (resultado.getModifiedCount() > 0) {
      logger.info("Precio de la habitación '{}' en Hotel ID {} actualizado con éxito. Precio modificado a {}", 
          tipoHabitacion, idHotel, nuevoPrecio);
  } else if (resultado.getMatchedCount() > 0) {
      logger.warn("Hotel ID {} encontrado, pero el precio de '{}' ya era {}. No se requirió modificación.", 
          idHotel, tipoHabitacion, nuevoPrecio);
  } else {
      logger.error("ERROR al actualizar. No se encontró el hotel con ID {} O la habitación tipo '{}' no existe en su array.", 
          idHotel, tipoHabitacion);
  }
  
  return resultado;
	}


	//Eliminar todas las habitaciones con precio superior a 300.00 en el "Grand Hotel Central".
	
	public UpdateResult eliminarHabitacionesCaras(String nombreHotel, double precioLimite) {
	 
	 // 1. Criterio de Búsqueda (Query): Localizar el hotel por nombre
	 Document filter = new Document("nombre", nombreHotel);
	
	 // 2. Operador de Actualización ($pull)
	 // $pull: Elimina del array 'habitaciones' todos los elementos que cumplan el criterio
	 // La condición es: precio > precioLimite ($gt significa "Greater Than")
	 Document update = new Document("$pull", 
	     new Document("habitaciones", new Document("precio", new Document("$gt", precioLimite)))
	 );
	 
	 logger.info("Intentando eliminar habitaciones con precio > {} en el hotel: {}", precioLimite, nombreHotel);
	
	 // 3. Ejecución: updateOne porque trabajamos sobre un único hotel.
	 UpdateResult resultado = coleccion.updateOne(filter, update);
	 
	 // 4. Loguear el resultado
	 long modificados = resultado.getModifiedCount();
	 
	 if (modificados > 0) {
	     logger.info("Eliminación por $pull completada. El array de habitaciones del hotel '{}' fue modificado.", nombreHotel);
	 } else if (resultado.getMatchedCount() > 0) {
	     logger.warn("Hotel '{}' encontrado, pero no se eliminó ninguna habitación (ninguna superaba el precio de {}).", nombreHotel, precioLimite);
	 } else {
	     logger.error("ERROR al eliminar. No se encontró el hotel con nombre '{}'.", nombreHotel);
	 }
	
	 return resultado;
	}
    
    //Media de estrellas por ciudad
    //Calcula la media de estrellas de todos los hoteles que se encuentran en "Barcelona".
    
    public double calcularMediaEstrellasPorCiudad(String ciudad) {
        
        // Pipeline de Agregación:
        Document result = coleccion.aggregate(Arrays.asList(
            // 1. $match: Filtra los documentos por la ciudad
            new Document("$match", new Document("ubicacion.ciudad", ciudad)),
            
            // 2. $group: Calcula la media de las estrellas ($avg)
            new Document("$group", new Document("_id", null).append("media", new Document("$avg", "$estrellas")))
            
        )).first(); // Tomamos el primer y único resultado
        
        if (result != null) {
            return result.getDouble("media");
        }
        return 0.0;
    }
    
    
 // Metodo auxiliar para convertir Document a Hotel
  //convertimos bien los enum porque los mios tienen _
  //Y el json original no
  private Hotel mapearHotel(Document doc) {
      Hotel h = new Hotel();
      
      // 1. Mapeo de campos simples (idHotel, nombre, fechaApertura, estrellas, etc.)
      h.setIdHotel(doc.getString("idHotel"));
      h.setNombre(doc.getString("nombre") != null ? doc.getString("nombre") : "");
      h.setFechaApertura(doc.getString("fechaApertura") != null ? doc.getString("fechaApertura") : "");
      
      Number estrellas = doc.get("estrellas", Number.class);
      h.setEstrellas(estrellas != null ? estrellas.intValue() : 0);
      
      Boolean adMascota = doc.getBoolean("admiteMascotas");
      h.setAdmiteMascotas(adMascota != null ? adMascota : false);
      
      // --- Mapeo de Ubicacion ---
      Document ubicacionDoc = (Document) doc.get("ubicacion");
      
      if (ubicacionDoc != null) { 
          
          // A. Mapeo de Coordenadas
          Coordenadas coordenadasObj = null;
          Document coordenadasDoc = ubicacionDoc.get("coordenadas", Document.class);

          if (coordenadasDoc != null) {
              Number lat = coordenadasDoc.get("lat", Number.class);
              Number lon = coordenadasDoc.get("lon", Number.class);

              coordenadasObj = new Coordenadas(
                  lat != null ? lat.doubleValue() : 0.0,
                  lon != null ? lon.doubleValue() : 0.0
              );
          }
          
          // B. TRATAMIENTO DEL CÓDIGO POSTAL 
          int cpFinal = 0;
          try {
              String cpStr = ubicacionDoc.getString("codigoPostal");
              if (cpStr != null) {
                  cpFinal = Integer.parseInt(cpStr);
              } else {
                  Integer cpInt = ubicacionDoc.getInteger("codigoPostal");
                  if (cpInt != null) cpFinal = cpInt;
              }
          } catch (Exception e) {
              logger.error("Aviso: Error parseando CP, se pondrá 0.", e);
              cpFinal = 0;
          }

          // C. Crear objeto Ubicación
          h.setUbicacion(new Ubicacion(
              ubicacionDoc.getString("calle") != null ? ubicacionDoc.getString("calle") : "",
              ubicacionDoc.getInteger("numero") != null ? ubicacionDoc.getInteger("numero") : 0,
              String.valueOf(cpFinal),
              coordenadasObj
              ));
      }
      
      // --- Lectura de habitaciones (Lista) ---
      List<Document> habitacionesDoc = doc.getList("habitaciones", Document.class);
      if (habitacionesDoc != null) {
          List<Habitaciones> listaHabitaciones = new ArrayList<Habitaciones>();
          for (Document habDoc : habitacionesDoc) {
              
              // Mapeo del Enum Tipo
              String tipoStr = habDoc.getString("tipo");
              Tipo tipoEnum = null;
              if (tipoStr != null) {
                  try {
                      // 1. Convertir a mayúsculas
                      String normalizedTipo = tipoStr.toUpperCase();
                      
                      // 2. REEMPLAZAR ACENTOS
                      // Solo dejamos activa la 'Á' como se solicitó.
                      normalizedTipo = normalizedTipo.replace("Á", "A"); 
                      /*
                      normalizedTipo = normalizedTipo.replace("É", "E");
                      normalizedTipo = normalizedTipo.replace("Í", "I");
                      normalizedTipo = normalizedTipo.replace("Ó", "O");
                      normalizedTipo = normalizedTipo.replace("Ú", "U");
                      // normalizedTipo = normalizedTipo.replace("Ñ", "N");
                      */
                      
                      // 3. Reemplazar ESPACIOS por guiones bajos
                      normalizedTipo = normalizedTipo.replace(" ", "_");
                      
                      tipoEnum = Tipo.valueOf(normalizedTipo);
                  } catch (IllegalArgumentException ex) {
                      tipoEnum = null; 
                  }
              }
              
              // Precio (Usamos Number por si acaso viene int en vez de double)
              Number precio = habDoc.get("precio", Number.class);

              listaHabitaciones.add(new Habitaciones(
                  tipoEnum,
                  precio != null ? precio.doubleValue() : 0.0, 
                  habDoc.getInteger("capacidad") != null ? habDoc.getInteger("capacidad") : 0, 
                  habDoc.getBoolean("disponible") != null ? habDoc.getBoolean("disponible") : false
              ));
          }
         
          h.setHabitaciones(listaHabitaciones); 
      }
      
      return h;
  }
    
 // Metodo auxiliar para convertir Hotel a Document (Java -> Mongo)
    private Document crearDocumento(Hotel h) {
        
        // 1. ATRIBUTOS RAÍZ
        Document doc = new Document("idHotel", h.getIdHotel())
                .append("nombre", h.getNombre()) 
                .append("estrellas", h.getEstrellas()) 
                .append("admiteMascotas", h.isAdmiteMascotas()) 
                .append("fechaApertura", h.getFechaApertura()); 
                
        // 2. DOCUMENTO ANIDADO (Ubicacion + Coordenadas)
        if (h.getUbicacion() != null) {
            
            // --- 2a. CREAR EL DOCUMENTO MÁS PROFUNDO (Coordenadas) ---
            Document docCoordenadas = null;
            if (h.getUbicacion().getCoordenadas() != null) {
                docCoordenadas = new Document("lat", h.getUbicacion().getCoordenadas().getLat())
                                         .append("lon", h.getUbicacion().getCoordenadas().getLon());
            }

            // --- 2b. CREAR EL DOCUMENTO INTERMEDIO (Ubicación) ---
            Document ubicacionDoc = new Document("calle", h.getUbicacion().getCalle() != null ? h.getUbicacion().getCalle() : null) 
                    .append("numero", h.getUbicacion().getNumero()) 
                    .append("codigoPostal", h.getUbicacion().getCodigoPostal()); 
            
            // 🔗 ANIDAMIENTO: Metemos el Documento 'docCoordenadas' dentro de 'ubicacionDoc'
            ubicacionDoc.append("coordenadas", docCoordenadas); 
            
            // Metemos el Documento 'ubicacionDoc' dentro de 'hotelDoc'
            doc.append("ubicacion", ubicacionDoc); 
            
        } else {
            doc.append("ubicacion", null); 
        }

        // 3. LISTA DE DOCUMENTOS (Habitaciones)
        List<Document> listaHabitaciones = new ArrayList<>();
        if (h.getHabitaciones() != null) {
            for (Habitaciones hab : h.getHabitaciones()) {
                
                // Seguridad: Convertir Enum a String para guardar en Mongo
                String tipoStr = hab.getTipo() != null ? hab.getTipo().name() : null;
                
                Document habDoc = new Document("tipo", tipoStr)
                		.append("precio", hab.getPrecio())
                        .append("capacidad", hab.getCapacidad()) 
                        .append("disponible", hab.isDisponible()); 
                listaHabitaciones.add(habDoc);
            }
        }
        doc.append("habitaciones", listaHabitaciones);
        
        return doc;
    }
    
    // Getters y Setters
    public List<Hotel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(List<Hotel> hoteles) {
        this.hoteles = hoteles;
    }

    public static String getNombreColeccion() {
        return NOMBRE_COLECCION;
    }

    public MongoCollection<Document> getColeccion() {
        return coleccion;
    }
}