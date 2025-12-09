package mongoDB.Repositorio;

public class EjemploDocumentoDocumento {
	// ==============================================================================
    // 1. MÉTODO AUXILIAR: JAVA -> MONGO (Para SAVE y UPDATE)
    // ==============================================================================
    private Document crearDocumento(Hotel h) {
        
        // --- PASO 1: PREPARAR NIVEL MÁS PROFUNDO (Coordenadas) ---
        Document docCoordenadas = new Document();
        // Verificamos nulos para no romper el programa si no hay ubicación o coordenadas
        if (h.getUbicacion() != null && h.getUbicacion().getCoordenadas() != null) {
            docCoordenadas.append("lat", h.getUbicacion().getCoordenadas().getLat());
            docCoordenadas.append("lon", h.getUbicacion().getCoordenadas().getLon());
        }

        // --- PASO 2: PREPARAR NIVEL INTERMEDIO (Ubicación) ---
        // Aquí metemos el documento de coordenadas dentro
        Document docUbicacion = new Document();
        if (h.getUbicacion() != null) {
            docUbicacion.append("calle", h.getUbicacion().getCalle() != null ? h.getUbicacion().getCalle() : "");
            
            // 🔗 AQUÍ ANIDAMOS: Metemos el docCoordenadas dentro de docUbicacion
            // Si el objeto coordenadas era null, guardamos null o el documento vacío (según prefieras)
            docUbicacion.append("coordenadas", h.getUbicacion().getCoordenadas() != null ? docCoordenadas : null);
        }

        // --- PASO 3: PREPARAR LA LISTA DE DOCUMENTOS (Reseñas) ---
        List<Document> listaDocsResenas = new ArrayList<Document>();
        if (h.getResenas() != null) {
            for (Resena r : h.getResenas()) {
                Document docResena = new Document();
                docResena.append("autor", r.getAutor() != null ? r.getAutor() : "");
                docResena.append("puntos", r.getPuntos()); // int no puede ser null
                
                // Añadimos el documento a la lista
                listaDocsResenas.add(docResena);
            }
        }

        // --- PASO 4: DOCUMENTO PRINCIPAL (Hotel) ---
        Document docHotel = new Document();
        docHotel.append("idHotel", h.getIdHotel());
        docHotel.append("nombre", h.getNombre() != null ? h.getNombre() : "");
        
        // 🔗 AQUÍ ANIDAMOS: Metemos el docUbicacion (que ya lleva coordenadas dentro)
        docHotel.append("ubicacion", h.getUbicacion() != null ? docUbicacion : null);
        
        // 🔗 AQUÍ ANIDAMOS LA LISTA
        docHotel.append("reseñas", listaDocsResenas); 

        return docHotel;
    }

    // ==============================================================================
    // 2. MÉTODO AUXILIAR: MONGO -> JAVA (Para READ y FILTROS)
    // ==============================================================================
    private Hotel mapearHotel(Document doc) {
        Hotel h = new Hotel();
        
        // 1. Campos simples
        h.setIdHotel(doc.getString("idHotel"));
        h.setNombre(doc.getString("nombre") != null ? doc.getString("nombre") : "");

        // 2. DESEMPAQUETAR "MATRYOSHKA" (Ubicación -> Coordenadas)
        Document docUbi = (Document) doc.get("ubicacion");
        
        if (docUbi != null) {
            // Leemos campo simple de ubicación
            String calle = docUbi.getString("calle") != null ? docUbi.getString("calle") : "";
            
            // Bajamos al sótano: Leemos Coordenadas dentro de Ubicación
            Document docCoord = (Document) docUbi.get("coordenadas");
            Coordenadas coordsObj = null; // Por defecto null si no existe
            
            if (docCoord != null) {
                // Usamos Number.class para evitar errores con doubles/integers
                double lat = docCoord.get("lat", Number.class) != null ? docCoord.get("lat", Number.class).doubleValue() : 0.0;
                double lon = docCoord.get("lon", Number.class) != null ? docCoord.get("lon", Number.class).doubleValue() : 0.0;
                
                coordsObj = new Coordenadas(lat, lon);
            }
            
            // Construimos la Ubicación completa
            h.setUbicacion(new Ubicacion(calle, coordsObj));
        }

        // 3. DESEMPAQUETAR LA LISTA (Reseñas)
        // Pedimos la lista especificando que contiene objetos 'Document.class'
        List<Document> listaDocs = doc.getList("reseñas", Document.class);
        
        if (listaDocs != null) {
            List<Resena> listaResenasJava = new ArrayList<Resena>();
            
            for (Document d : listaDocs) {
                // Convertimos cada documentito de la lista a objeto Resena
                String autor = d.getString("autor") != null ? d.getString("autor") : "";
                int puntos = d.get("puntos", Number.class) != null ? d.get("puntos", Number.class).intValue() : 0;
                
                listaResenasJava.add(new Resena(autor, puntos));
            }
            // Guardamos la lista convertida en el Hotel
            h.setResenas(listaResenasJava);
        }

        return h;
    }

}
