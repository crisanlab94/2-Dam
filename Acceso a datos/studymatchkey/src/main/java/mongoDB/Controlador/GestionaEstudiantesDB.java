package mongoDB.Controlador;

import java.util.ArrayList;
import java.util.List;

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
    
    public static void main(String[] args) {
        
      
      
        GestionaEstudiantesDB programa = new GestionaEstudiantesDB();
        
        System.out.println("--- PRUEBA DE CONEXIÓN ---");
        
        //Conexión y servicio
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();    
       
        EstudianteServicio estudianteS = new EstudianteServicio(db);
         
        // Leer la base de datos como estaba (Read)
        System.out.println("\n--- ESTADO INICIAL DE LA BASE DE DATOS ---");
        // Llamamos al método usando 'programa.'
        programa.mostrarLista(estudianteS);

        // Crear estudiante (Create)
        System.out.println("\n--- CREANDO NUEVO ESTUDIANTE ---");
        
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
        List<Asignatura> listaAsig = new ArrayList<>();
        listaAsig.add(new Asignatura("Programación", "PR1", "Prof. Code"));
        listaAsig.add(new Asignatura("Bases de Datos", "BD1", "Prof. Data"));
        listaAsig.add(new Asignatura("Entornos", "ENT1", "Prof. Dev"));
        e1.setAsignatura(listaAsig);
        
        try {
            estudianteS.save(e1);
            System.out.println("Estudiante creado con éxito");

        } catch (IdException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
        
        // Imprimir de nuevo la base de datos para ver que se ha añadido
        System.out.println("\n--- COMPROBANDO AÑADIDO ---");
        programa.mostrarLista(estudianteS);

        // Intentar añadirlo de nuevo para que no se pueda (ID Duplicado)
        System.out.println("\n--- INTENTANDO AÑADIR DUPLICADO ---");
        
        Estudiante e2 = new Estudiante();
        e2.setId_Estudiante("E1235"); // IMPORTANTE: Ponemos el MISmo ID 
        e2.setNombre("Intruso");      
        e2.setEmail("intruso@test.com");
        e2.setEdad(20);
        
        try {
            // Intentamos guardar e2 (mismo ID para que de error)
            estudianteS.save(e2);
        } catch (IdException ex) {
            System.out.println("Error esperado: No se puede añadir, el ID ya existe.");
        }

        //Modificar ese estudiante (Update)
        System.out.println("\n--- MODIFICANDO ESTUDIANTE ---");
        
        // Cambiamos datos
        e1.setNombre("PRUEBA MODIFICADA"); 
        e1.setNota(5.0);                   
        
        // Llamamos al método update 
        try {
			estudianteS.update(e1);
		} catch (IdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Estudiante modificado.");
        
        // Imprimir de nuevo la lista para ver la modificación
        System.out.println("\n--- COMPROBANDO MODIFICACIÓN ---");
        programa.mostrarLista(estudianteS);
       
        
     // PRUEBAS DE FILTROS Y ORDENACIÓN 
        System.out.println("\n---PRUEBAS DE FILTROS Y ORDENACIÓN ---");

        // Filtro Repositorio (MONGO QUERY) buscar por curso
        System.out.println("\nFILTRO MONGO: Estudiantes de CURSO SEGUNDO:");
        List<Estudiante> cursoSegundo = estudianteS.buscarPorCursoMongo(Curso.SEGUNDO);
        for(Estudiante e : cursoSegundo) System.out.println("   - " + e.getNombre() + ", Curso: " + e.getCurso());
        
        // Filtro Repositorio (MONGO QUERY) buscar por Rango de Nota
        System.out.println("\nFILTRO MONGO: Estudiantes con Nota entre 5.0 y 7.5:");
        List<Estudiante> rangoNotas = estudianteS.buscarPorRangoDeNota(5.0, 7.5);
        for(Estudiante e : rangoNotas) System.out.println("   - " + e.getNombre() + ", Nota: " + e.getNota());
        
        // Filtro Repositorio (MONGO QUERY) buscar por Dirección de Entidad (Campo Anidado)
        System.out.println("\nFILTRO MONGO: Estudiantes de la Entidad en 'Calle Letras 4':");
        List<Estudiante> porDireccion = estudianteS.buscarPorDireccionEntidad("Calle Letras 4");
        for(Estudiante e : porDireccion) {
             String direccion = e.getEntidad() != null ? e.getEntidad().getDireccion() : "N/A";
             System.out.println("   - " + e.getNombre() + ", Dirección: " + direccion);
        }

        //Ordenación Repositorio (MONGO QUERY) ordenar por nota, solo salen las 10 mejores notas
        System.out.println("\nORDEN MONGO: Por Nota (Mayor a menor, DESCENDENTE):");
        List<Estudiante> mejoresNotas = estudianteS.leerOrdenadoPorNotaMongo();
        int limite = Math.min(mejoresNotas.size(), 10);
        for(Estudiante e : mejoresNotas.subList(0, limite)) System.out.println("   - " + e.getNombre() + ": " + e.getNota());
        
     // Ordenación Repositorio (MONGO QUERY) ordenar por Nombre de Entidad
        System.out.println("\nORDEN MONGO: Por Nombre de Entidad (A-Z):");
        List<Estudiante> porEntidad = estudianteS.leerOrdenadoPorNombreEntidad();
        for(Estudiante e : porEntidad) {
            String nombreEntidad = e.getEntidad() != null ? e.getEntidad().getNombre() : "SIN ENTIDAD ASIGNADA";
            System.out.println("   - " + e.getNombre() + ", Entidad: " + nombreEntidad);
        }
        
        // Ordenación Repositorio (MONGO QUERY) ordenar por Fecha de Nacimiento (Más jóvenes primero)
        System.out.println("\nORDEN MONGO: Por Fecha de Nacimiento (Ascendente):");
        List<Estudiante> porFechaNac = estudianteS.leerOrdenadoPorFechaNacimientoAscendente();
        for(Estudiante e : porFechaNac) System.out.println("   - " + e.getNombre() + ", Fecha: " + e.getFecha_de_nacimiento());
        
        // Ordenación Repositorio (MONGO QUERY) Ordenación Combinada (Entidad A-Z, luego Edad DESC)
        System.out.println("\nORDEN MONGO: Por Entidad (A-Z) y luego Edad (Descendente):");
        List<Estudiante> porEntidadYEdad = estudianteS.leerOrdenadoPorEntidadYEdad();
        for(Estudiante e : porEntidadYEdad) {
            // 🔥 CORRECCIÓN 3: Null Check para Entidad en Ordenación Combinada
            String entidadNombre = e.getEntidad() != null ? e.getEntidad().getNombre() : "N/A";
            System.out.println("   - " + e.getNombre() + ", Entidad: " + entidadNombre + ", Edad: " + e.getEdad());
        }
        
        // Filtro Servicio (JAVA) filtro por edad 
        System.out.println("\nFILTRO JAVA: Estudiantes de 20 años o más:");
        List<Estudiante> mayores = estudianteS.filtrarPorEdadJava(20);
        for(Estudiante e : mayores) System.out.println("   - " + e.getNombre() + ", Edad: " + e.getEdad());

        // Ordenación Servicio (JAVA) ordenacion alfabetica por nombre
        System.out.println("\nORDEN JAVA: Por Nombre Alfabético:");
        List<Estudiante> nombresOrdenados = estudianteS.ordenarPorNombreJava();
        for(Estudiante e : nombresOrdenados) System.out.println("   - " + e.getNombre());
        
        // Filtro Servicio (JAVA) por Nota y Turno de Mañana
        System.out.println("\nFILTRO JAVA: Nota >= 8.0 Y Turno de Mañana:");
        List<Estudiante> notaTurno = estudianteS.filtrarPorNotaYTurnoManana(8.0);
        for(Estudiante e : notaTurno) System.out.println("   - " + e.getNombre() + ", Nota: " + e.getNota() + ", Turno Mañana: " + e.isTurnoManana());

        // Ordenación Servicio (JAVA) por Longitud del Nombre
        System.out.println("\nORDEN JAVA: Por Longitud de Nombre (Más corto primero):");
        List<Estudiante> porLongitud = estudianteS.ordenarPorLongitudNombreJava();
        for(Estudiante e : porLongitud) System.out.println("   - " + e.getNombre() + " (Longitud: " + e.getNombre().length() + ")");
        
        // Borrar estudiante (Delete)
        System.out.println("\n--- BORRANDO ESTUDIANTE ---");
        
        // Borramos usando el ID
        try {
			estudianteS.delete("E1235");
			estudianteS.delete("123"); //Intento borrar uno que no existe
		} catch (IdException e3) {
			System.out.println("Error no se puede borrar ese estudiante porque no está registrado");
		}
        System.out.println("Estudiante E1235 borrado");
        
        // Imprimir la lista para ver que se ha borrado
        System.out.println("\n--- COMPROBANDO BORRADO (LISTA FINAL) ---");
        programa.mostrarLista(estudianteS);
        
        System.out.println("\n--- FIN DE LAS PRUEBAS ---");
    }
        

    // Método auxiliar 
    // Solo porque imprim 4 veces para hacer las comprobaciones
    private void mostrarLista(EstudianteServicio servicio) {
        List<Estudiante> lista = servicio.read();
        
        if (lista.isEmpty()) {
            System.out.println("No se han encontrado estudiantes");
        } else {
            for(Estudiante e : lista) {
                System.out.println(e); 
            }
        }
    }
}