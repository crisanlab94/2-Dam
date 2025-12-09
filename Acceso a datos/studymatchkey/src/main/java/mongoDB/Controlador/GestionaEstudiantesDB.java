package mongoDB.Controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;     

import com.mongodb.client.MongoDatabase;

import mongoDB.Config.MongoDBConexion;
import mongoDB.Modelo.Asignatura;
import mongoDB.Modelo.Curso;
import mongoDB.Modelo.Entidad;
import mongoDB.Modelo.Estudiante;
import mongoDB.Modelo.IdException;
import mongoDB.Modelo.Tipo;
import mongoDB.Servicio.EstudianteServicio;

public class GestionaEstudiantesDB {
    
    // Declaración del Logger
    private static final Logger logger = LogManager.getLogger(GestionaEstudiantesDB.class);
    
    public static void main(String[] args) {
        
        GestionaEstudiantesDB programa = new GestionaEstudiantesDB();
        
        logger.info("--- PRUEBA DE CONEXIÓN ---");
        
        //Conexión y servicio
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();    
       
        EstudianteServicio estudianteS = new EstudianteServicio(db);
         
        // Leer la base de datos como estaba (Read)
        logger.info("\n--- ESTADO INICIAL DE LA BASE DE DATOS ---");
        // Llamamos al método usando 'programa.'
        programa.mostrarLista(estudianteS);

        // Crear estudiante (Create)
        logger.info("\n--- CREANDO NUEVO ESTUDIANTE ---");
        
        Estudiante e1 = new Estudiante();
        e1.setId_Estudiante("E1235"); 
        e1.setNombre("Prueba2");
        e1.setFecha_de_nacimiento("2020-06-07");
        e1.setEmail("java2@test.com");
        e1.setEdad(25);
        e1.setNota(10.0);
        e1.setTurnoManana(true);
        e1.setCurso(Curso.SEGUNDO);
        
        // Entidad de prueba
        Entidad miEntidad = new Entidad(Tipo.UNIVERSIDAD, "Universidad de prueba", "Calle Java 1");
        e1.setEntidad(miEntidad);

        // Asignaturas de prueba 
        List<Asignatura> listaAsig = new ArrayList<Asignatura>();
        listaAsig.add(new Asignatura("Programación", "PR1", "Prof. Code"));
        listaAsig.add(new Asignatura("Bases de Datos", "BD1", "Prof. Data"));
        listaAsig.add(new Asignatura("Entornos", "ENT1", "Prof. Dev"));
        e1.setAsignatura(listaAsig);
        
        try {
            estudianteS.save(e1);
            logger.info("Estudiante creado con éxito");

        } catch (IdException e) {
            logger.error("ERROR: " + e.getMessage());
        }
        
        // Imprimir de nuevo la base de datos para ver que se ha añadido
        logger.info("\n--- COMPROBANDO AÑADIDO ---");
        programa.mostrarLista(estudianteS);

        // Intentar añadirlo de nuevo para que no se pueda (ID Duplicado)
        logger.info("\n--- INTENTANDO AÑADIR DUPLICADO ---");
        
        Estudiante e2 = new Estudiante();
        e2.setId_Estudiante("E1235"); // IMPORTANTE: Ponemos el MISmo ID 
        e2.setNombre("Intruso");      
        e2.setEmail("intruso@test.com");
        e2.setEdad(20);
        
        try {
            // Intentamos guardar e2 (mismo ID para que de error)
            estudianteS.save(e2);
        } catch (IdException ex) {
            logger.info("Error esperado: No se puede añadir, el ID ya existe.");
        }

        //Modificar ese estudiante (Update)
        logger.info("\n--- MODIFICANDO ESTUDIANTE ---");
        
        // Cambiamos datos
        e1.setNombre("PRUEBA MODIFICADA"); 
        e1.setNota(5.0);                   
        
        // Llamamos al método update 
        try {
            estudianteS.update(e1);
        } catch (IdException e) {
            logger.error(e.getMessage());
        }
        logger.info("Estudiante modificado.");
        
        // Imprimir de nuevo la lista para ver la modificación
        logger.info("\n--- COMPROBANDO MODIFICACIÓN ---");
        programa.mostrarLista(estudianteS);
       
        
     // PRUEBAS DE FILTROS Y ORDENACIÓN 
        logger.info("\n---PRUEBAS DE FILTROS Y ORDENACIÓN ---");

        // Filtro Repositorio (MONGO QUERY) buscar por curso
        logger.info("\nFILTRO MONGO: Estudiantes de CURSO SEGUNDO:");
        List<Estudiante> cursoSegundo = estudianteS.buscarPorCursoMongo(Curso.SEGUNDO);
        for(Estudiante e : cursoSegundo) logger.info("   - " + e.getNombre() + ", Curso: " + e.getCurso());
        
        // Filtro Repositorio (MONGO QUERY) buscar por Rango de Nota
        logger.info("\nFILTRO MONGO: Estudiantes con Nota entre 5.0 y 7.5:");
        List<Estudiante> rangoNotas = estudianteS.buscarPorRangoDeNota(5.0, 7.5);
        for(Estudiante e : rangoNotas) logger.info("   - " + e.getNombre() + ", Nota: " + e.getNota());
        
        // Filtro Repositorio (MONGO QUERY) buscar por Dirección de Entidad (Campo Anidado)
        logger.info("\nFILTRO MONGO: Estudiantes de la Entidad en 'Calle Letras 4':");
        List<Estudiante> porDireccion = estudianteS.buscarPorDireccionEntidad("Calle Letras 4");
        for(Estudiante e : porDireccion) {
             String direccion = e.getEntidad() != null ? e.getEntidad().getDireccion() : "N/A";
             logger.info("   - " + e.getNombre() + ", Dirección: " + direccion);
        }

        //Ordenación Repositorio (MONGO QUERY) ordenar por nota, solo salen las 10 mejores notas
        logger.info("\nORDEN MONGO: Por Nota (Mayor a menor, DESCENDENTE):");
        List<Estudiante> mejoresNotas = estudianteS.leerOrdenadoPorNotaMongo();
        int limite = Math.min(mejoresNotas.size(), 10);
        for(Estudiante e : mejoresNotas.subList(0, limite)) logger.info("   - " + e.getNombre() + ": " + e.getNota());
        
     // Ordenación Repositorio (MONGO QUERY) ordenar por Nombre de Entidad
        logger.info("\nORDEN MONGO: Por Nombre de Entidad (A-Z):");
        List<Estudiante> porEntidad = estudianteS.leerOrdenadoPorNombreEntidad();
        for(Estudiante e : porEntidad) {
            String nombreEntidad = e.getEntidad() != null ? e.getEntidad().getNombre() : "SIN ENTIDAD ASIGNADA";
            logger.info("   - " + e.getNombre() + ", Entidad: " + nombreEntidad);
        }
        
        // Ordenación Repositorio (MONGO QUERY) ordenar por Fecha de Nacimiento (Más jóvenes primero)
        logger.info("\nORDEN MONGO: Por Fecha de Nacimiento (Ascendente):");
        List<Estudiante> porFechaNac = estudianteS.leerOrdenadoPorFechaNacimientoAscendente();
        for(Estudiante e : porFechaNac) logger.info("   - " + e.getNombre() + ", Fecha: " + e.getFecha_de_nacimiento());
        
        // Ordenación Repositorio (MONGO QUERY) Ordenación Combinada (Entidad A-Z, luego Edad DESC)
        logger.info("\nORDEN MONGO: Por Entidad (A-Z) y luego Edad (Descendente):");
        List<Estudiante> porEntidadYEdad = estudianteS.leerOrdenadoPorEntidadYEdad();
        for(Estudiante e : porEntidadYEdad) {
            String entidadNombre = e.getEntidad() != null ? e.getEntidad().getNombre() : "N/A";
            logger.info("   - " + e.getNombre() + ", Entidad: " + entidadNombre + ", Edad: " + e.getEdad());
        }
        
        // Filtro Servicio (JAVA) filtro por edad 
        logger.info("\nFILTRO JAVA: Estudiantes de 20 años o más:");
        List<Estudiante> mayores = estudianteS.filtrarPorEdadJava(20);
        for(Estudiante e : mayores) logger.info("   - " + e.getNombre() + ", Edad: " + e.getEdad());

        // Ordenación Servicio (JAVA) ordenacion alfabetica por nombre
        logger.info("\nORDEN JAVA: Por Nombre Alfabético:");
        List<Estudiante> nombresOrdenados = estudianteS.ordenarPorNombreJava();
        for(Estudiante e : nombresOrdenados) logger.info("   - " + e.getNombre());
        
        // Filtro Servicio (JAVA) por Nota y Turno de Mañana
        logger.info("\nFILTRO JAVA: Nota >= 8.0 Y Turno de Mañana:");
        List<Estudiante> notaTurno = estudianteS.filtrarPorNotaYTurnoManana(8.0);
        for(Estudiante e : notaTurno) logger.info("   - " + e.getNombre() + ", Nota: " + e.getNota() + ", Turno Mañana: " + e.isTurnoManana());

        // Ordenación Servicio (JAVA) por Longitud del Nombre
        logger.info("\nORDEN JAVA: Por Longitud de Nombre (Más corto primero):");
        List<Estudiante> porLongitud = estudianteS.ordenarPorLongitudNombreJava();
        for(Estudiante e : porLongitud) logger.info("   - " + e.getNombre() + " (Longitud: " + e.getNombre().length() + ")");
        
        // Borrar estudiante (Delete)
        logger.info("\n--- BORRANDO ESTUDIANTE ---");
        
        // Borramos usando el ID
        try {
            estudianteS.delete("E1235");
            estudianteS.delete("123"); //Intento borrar uno que no existe
        } catch (IdException e3) {
            logger.error("Error no se puede borrar ese estudiante porque no está registrado");
        }
        logger.info("Estudiante E1235 borrado");
        
        // Imprimir la lista para ver que se ha borrado
        logger.info("\n--- COMPROBANDO BORRADO (LISTA FINAL) ---");
        programa.mostrarLista(estudianteS);
        
        logger.info("\n--- FIN DE LAS PRUEBAS ---");
    }
        

    // Método auxiliar 
    private void mostrarLista(EstudianteServicio servicio) {
        List<Estudiante> lista = servicio.read();
        
        if (lista.isEmpty()) {
            logger.info("No se han encontrado estudiantes");
        } else {
            for(Estudiante e : lista) {
                logger.info(e); 
            }
        }
    }
}