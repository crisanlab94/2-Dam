package mongoDB.Controlador;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import mongoDB.Config.MongoDBConexion;
import mongoDB.Modelo.Hotel;
import mongoDB.Servicio.HotelServicio;
import mongoDB.Modelo.Coordenadas;
import mongoDB.Modelo.Habitaciones;
import mongoDB.Modelo.Tipo;
import mongoDB.Modelo.Ubicacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class GestionaHoteles {
    
    // Inicialización del Logger
    private static final Logger logger = LogManager.getLogger(GestionaHoteles.class);
    
    public static void main(String[] args) {
        
        // 1. Conexión y Servicio
        // Inicializa la conexión a MongoDB y obtiene la instancia de la base de datos.
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();    
        // Inicializa la capa de servicio, que a su vez inicializa el Repositorio.
        HotelServicio hotelS = new HotelServicio(db);
        
        // --- SECUENCIA DE PRUEBA: CRUD BÁSICO (Mantenido) ---
        
        // PRUEBA 1: READ ALL (Estado Inicial)
        mostrarLista(hotelS);
        
        // PRUEBA 2: CREATE/SAVE (Crear un hotel complejo - ID: H1234)
        logger.info("\n--- PRUEBA 2: CREATE/SAVE (H1234) ---");
        Hotel hNuevo = crearHotelPrueba("H1234", "Test prueba");
        
        try {
            hotelS.save(hNuevo);
            logger.info(" Hotel H1234 '" + hNuevo.getNombre() + "' creado/procesado.");
        } catch (Exception e) {
            logger.error("ERROR al guardar (ID duplicado o fallo): " + e.getMessage());
        }
        
        // PRUEBA 3: READ ALL (Comprobar que se añadió)
        mostrarLista(hotelS);

        // PRUEBA 4: UPDATE (Modificar H1234: Bajar estrellas y cambiar nombre)
        logger.info("\n--- PRUEBA 4: UPDATE (H1234) ---");
        try {
            // Utilizamos filterById (ahora renombrado para coincidir con el servicio)
            Hotel hModificar = hotelS.filterById("H1234"); 
            if (hModificar != null) {
                hModificar.setNombre("TEST UPDATE - Hotel Maravilla");
                hModificar.setEstrellas(1);
                
                hotelS.update(hModificar);
                logger.info(" Hotel H1234 actualizado. Nuevo nombre: " + hModificar.getNombre());
            } else {
                 logger.error("No se pudo encontrar H1234 para actualizar.");
            }
        } catch (Exception e) {
             logger.error("ERROR inesperado durante el UPDATE: " + e.getMessage());
        }
        
        // PRUEBA 5: READ ALL (Comprobar la actualización)
        mostrarLista(hotelS);


        // PRUEBA 6: READ ONE (Buscar un ID específico)
        logger.info("\n--- PRUEBA 6: READ ONE (h102) ---");
        String idABuscar = "h102"; 
        
        Hotel hotelEncontrado = hotelS.filterById(idABuscar); // Usando filterById
        
        if (hotelEncontrado != null) {
            logger.info(" Éxito! Hotel encontrado: " + hotelEncontrado.getNombre());
        } else {
            logger.info(" No se encontró ningún hotel con el ID: " + idABuscar);
        }
        
        // --- INICIO DE PRUEBAS AVANZADAS DE FILTROS Y AGREGACIÓN ---
        
        // PRUEBA 7: FILTRO COMBINADO (Madrid y (5 estrellas O Mascotas)) - Filtro de la profesora (puede dar 0)
        logger.info("\n--- PRUEBA 7: FILTRO COMBINADO (Madrid y (5* O Mascotas)) ---");
        List<Hotel> listaFiltro1 = hotelS.recuperarHotelesMadridFiltro();
        logger.info("Hoteles encontrados: {}", listaFiltro1.size());
        if (!listaFiltro1.isEmpty()) logger.info("  -> Primer resultado: {}", listaFiltro1.get(0).getNombre());

        /* No existen ciudades
        // PRUEBA 8: FILTRO COMPLEJO ($OR anidado: (BCN y 4*) O (MAD y 5*)) - Filtro de la profesora (puede dar 0)
        logger.info("\n--- PRUEBA 8: FILTRO COMPLEJO ((BCN 4*) O (MAD 5*)) ---");
        List<Hotel> listaFiltro2 = hotelS.buscarHotelesPorCiudadYEstrellas();
        logger.info("Hoteles encontrados: {}", listaFiltro2.size());
        if (!listaFiltro2.isEmpty()) logger.info("  -> Primer resultado: {}", listaFiltro2.get(0).getNombre());

*/
        /* No existen ciudades
        // PRUEBA 9: FILTRO OR SIMPLE (Ciudades) - Usa el método que sí existe en el Repositorio
        logger.info("\n--- PRUEBA 9: FILTRO OR SIMPLE (Ciudades: Madrid O Valencia) ---");
        List<Hotel> listaFiltro3 = hotelS.buscarHotelesEnCiudades("Madrid", "Valencia");
        logger.info("Hoteles encontrados: {}", listaFiltro3.size());
        
        */
        
        // PRUEBA 10: FILTRO OR ESTRELLAS (3* O 4*) - El filtro simple y robusto
        logger.info("\n--- PRUEBA 10: FILTRO OR SIMPLE (3* O 4* Estrellas) ---");
        List<Hotel> listaFiltroEstrellas = hotelS.buscarHotelesPor3O4Estrellas();
        logger.info("Hoteles encontrados: {}", listaFiltroEstrellas.size());
        if (!listaFiltroEstrellas.isEmpty()) logger.info("  -> Primer resultado: {} ({} estrellas)", 
                                        listaFiltroEstrellas.get(0).getNombre(), listaFiltroEstrellas.get(0).getEstrellas());

        // PRUEBA 11: FILTRO TRIPLE QUE SÍ EXISTE (Estrellas=4 AND (Mascotas=T OR Precio<=115))
        logger.info("\n--- PRUEBA 11: FILTRO TRIPLE VÁLIDO (4* AND (Mascotas OR Precio<=115)) ---");
        List<Hotel> listaFiltro4 = hotelS.buscarHotelesFiltroComplejo();
        logger.info("Hoteles encontrados: {}", listaFiltro4.size());
        if (!listaFiltro4.isEmpty()) listaFiltro4.forEach(h -> logger.info("  -> [ID: {}] {}", h.getIdHotel(), h.getNombre()));

        // PRUEBA 12: AGREGACIÓN ($COUNT - Hoteles con Suite Junior)
        logger.info("\n--- PRUEBA 12: AGREGACIÓN ($COUNT Hoteles con Suite Junior) ---");
        long totalSuites = hotelS.contarHotelesSuiteJunior();
        logger.info("Total de hoteles con Suite Junior: {}", totalSuites);

        // PRUEBA 13: AGREGACIÓN ($AVG - Media de Estrellas en Barcelona)
        logger.info("\n--- PRUEBA 13: AGREGACIÓN ($AVG Media Estrellas en Barcelona) ---");
        double mediaEstrellas = hotelS.calcularMediaEstrellasPorCiudad("Barcelona");
        logger.info("Media de estrellas en Barcelona: {}", mediaEstrellas);
        
        // --- INICIO DE PRUEBAS AVANZADAS DE ACTUALIZACIÓN ---
        
        // Creamos un hotel para las pruebas de UPDATE específicas (ID: H888)
        Hotel hUpdateTest = crearHotelPrueba("H888", "Test Update Data");
        hotelS.save(hUpdateTest); 
        logger.info("\n--- PREPARACIÓN: Hotel H888 creado para pruebas de UPDATE. ---");
        
        // PRUEBA 14: ACTUALIZACIÓN BÁSICA (Nombre y Estrellas en H888)
        logger.info("\n--- PRUEBA 14: ACTUALIZACIÓN BÁSICA (Datos Simples H888) ---");
        UpdateResult res1 = hotelS.actualizarDatosBasicos("H888", "H888 - Nuevo Nombre", 3);
        logger.info("Documentos modificados (Datos Básicos): {}", res1.getModifiedCount());

        // PRUEBA 15: ACTUALIZACIÓN DE DOCUMENTO ANIDADO (Ubicación Completa en H888)
        logger.info("\n--- PRUEBA 15: ACTUALIZACIÓN ANIDADA (Ubicación H888) ---");
        UpdateResult res2 = hotelS.actualizarUbicacionCompleta("H888", 
            "Calle Actualizada", 99, "99999", 1.11, 2.22);
        logger.info("Documentos modificados (Ubicación Completa): {}", res2.getModifiedCount());

        // PRUEBA 16: ACTUALIZACIÓN MASIVA (updateMany)
        logger.info("\n--- PRUEBA 16: ACTUALIZACIÓN MASIVA (CP Gran Vía) ---");
        // Asumiendo que hay hoteles con "Gran Vía" que necesitan actualización
        UpdateResult res3 = hotelS.actualizarCPGranVia();
        logger.info("Documentos modificados (Actualización Masiva CP): {}", res3.getModifiedCount());
        
        
        // PRUEBA 17: ARRAY $PUSH (Añadir habitación suite_junior a H888)
        logger.info("\n--- PRUEBA 17: ARRAY $PUSH (Añadir suite_junior a H888) ---");
        Habitaciones nuevaHab = new Habitaciones(Tipo.SUITE_JUNIOR, 1000.0, 6, true);
        UpdateResult res4 = hotelS.anadirHabitacion("H888", nuevaHab);
        logger.info("Documentos modificados (Añadir habitación): {}", res4.getModifiedCount());
        
	     // PRUEBA DE REQUISITO ESPECÍFICO: Eliminar en "Grand Hotel Central"
	     // NOTA: Esta prueba debería devolver 0 borrados, ya que el Grand Hotel Central no tiene nada > 300€.
	     logger.info("\n--- PRUEBA REQUISITO ESPECÍFICO (Grand Hotel Central > 300€) ---");
	     UpdateResult resReq = hotelS.eliminarHabitacionesCaras("Grand Hotel Central", 300.00); 
	     logger.info("Documentos modificados (Grand Hotel Central): {} (Esperado: 0)", resReq.getModifiedCount());
        
        
        // PRUEBA 18: ARRAY POSICIONAL $ (Actualizar precio Individual en H888)
        logger.info("\n--- PRUEBA 18: ARRAY POSICIONAL $ (Actualizar precio Individual en H888) ---");
        // Asumiendo que el hotel tiene una habitación de tipo INDIVIDUAL
        UpdateResult res5 = hotelS.actualizarPrecioHabitacionIndividual("H888", 90.00);
        logger.info("Documentos modificados (Actualización precio Individual): {}", res5.getModifiedCount());

        // PRUEBA 19: ARRAY $PULL (Eliminar habitaciones caras en H888 > 500€)
        logger.info("\n--- PRUEBA 19: ARRAY $PULL (Eliminar habitaciones caras (>200€) en H888) ---");
        // La habitación PENTHOUSE (1000.0€) debería borrarse.
        UpdateResult res6 = hotelS.eliminarHabitacionesCaras("H888 - Nuevo Nombre", 200.00); 
        logger.info("Documentos modificados (Eliminación $pull): {}", res6.getModifiedCount());
    
        
        // --- LIMPIEZA FINAL ---

        // PRUEBA 20: DELETE (Limpiar los hoteles de prueba)
        logger.info("\n--- PRUEBA 20: LIMPIEZA FINAL (DELETE H1234 y H888) ---");
        // Intentamos borrar el hotel de prueba original
        hotelS.delete("H1234"); 
        // Borramos el hotel usado para las pruebas de UPDATE
        hotelS.delete("H888");
        logger.info("Limpieza final completada.");
        
        // PRUEBA 21: READ ALL (Estado Final)
        mostrarLista(hotelS);
        
       
    }
    
    // Método auxiliar para crear un objeto complejo de prueba (utilizado en SAVE)
    private static Hotel crearHotelPrueba(String id, String nombreBase) {
        Hotel h = new Hotel();
        h.setIdHotel(id);
        h.setNombre(nombreBase);
        h.setEstrellas(4);
        h.setAdmiteMascotas(true);
        h.setFechaApertura("2025-12-09"); 

        // 1. UBICACION + COORDENADAS (Anidamiento)
        Coordenadas coords = new Coordenadas(40.4168, -3.7038);
        Ubicacion ubicacion = new Ubicacion("Calle Prueba", 12, "28001", coords);

        // 2. HABITACIONES (Lista de Documentos)
        List<Habitaciones> habitaciones = new ArrayList<>();
        // Incluimos la habitación INDIVIDUAL para la PRUEBA 16 (Actualización posicional)
        habitaciones.add(new Habitaciones(Tipo.INDIVIDUAL, 50.0, 1, true)); 
        habitaciones.add(new Habitaciones(Tipo.DOBLE_ESTÁNDAR, 150.0, 2, true)); 
        habitaciones.add(new Habitaciones(Tipo.SUITE_JUNIOR, 300.0, 4, false));

        h.setUbicacion(ubicacion);
        h.setHabitaciones(habitaciones);
        
        return h;
    }
    
 // Método auxiliar para imprimir la lista de hoteles (utilizado en READ ALL)
    private static void mostrarLista(HotelServicio servicio) {
        List<Hotel> lista = servicio.read();
        logger.info("\n--- LISTADO COMPLETO DE HOTELES ({}) ---", lista.size());
        
        if (lista.isEmpty()) {
            logger.info("No se han encontrado hoteles.");
        } else {
            for(Hotel h : lista) {
                
                // --- 1. EXTRACCIÓN Y RESUMEN DE ATRIBUTOS RAÍZ ---
                
                // Datos básicos
                String nombreHotel = (h.getNombre() != null) ? h.getNombre() : "N/A";
                String calleStr = (h.getUbicacion() != null && h.getUbicacion().getCalle() != null) 
                                   ? h.getUbicacion().getCalle() : "N/A";
                String cpStr = (h.getUbicacion() != null) ? h.getUbicacion().getCodigoPostal() : "N/A";
                
                logger.info("==================================================================");
                logger.info("    HOTEL: {} | ID: {}", nombreHotel, h.getIdHotel());
                logger.info("   --------------------------------------------------------------");
                logger.info("       Estrellas: {} | Admite Mascotas: {} | Apertura: {}", 
                    h.getEstrellas(), h.isAdmiteMascotas(), h.getFechaApertura());
                
                // --- 2. UBICACIÓN COMPLETA (Anidamiento Profundo) ---
                logger.info("      [Ubicación] Calle: {} (CP: {})", calleStr, cpStr);

                if (h.getUbicacion() != null && h.getUbicacion().getCoordenadas() != null) {
                    logger.info("         - Coordenadas: Latitud: {} | Longitud: {}", 
                        h.getUbicacion().getCoordenadas().getLat(), 
                        h.getUbicacion().getCoordenadas().getLon());
                }

                // --- 3. DETALLE COMPLETO DE LA LISTA (Habitaciones) ---
                if (h.getHabitaciones() != null && !h.getHabitaciones().isEmpty()) {
                    logger.info("      [Habitaciones - Total: {}]", h.getHabitaciones().size());
                    
                    for (Habitaciones hab : h.getHabitaciones()) {
                        String tipo = (hab.getTipo() != null) ? hab.getTipo().toString() : "N/A";
                        logger.info("        - Tipo: {} | Precio: {}€ | Capacidad: {} | Disponible: {}", 
                            tipo, hab.getPrecio(), hab.getCapacidad(), hab.isDisponible());
                    }
                } else {
                     logger.info("      [Habitaciones] No hay información de habitaciones.");
                }
            }
        }
    }
}