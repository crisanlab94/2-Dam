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
        //Lo necesito para el metodo al final
        GestionaEstudiantesDB programa = new GestionaEstudiantesDB();
        
        System.out.println("--- PRUEBA DE CONEXIÓN ---");
        
        //Conexión y servicio
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();    
        
        //Creo la instacia del servicio
        EstudianteServicio estudianteS = new EstudianteServicio(db);
          
        // Leer la base de datos como estaba (Read)
        System.out.println("\n--- ESTADO INICIAL DE LA BASE DE DATOS ---");
        programa.mostrarLista(estudianteS);

        // Crear estudiante (Create)
        System.out.println("\n--- CREANDO NUEVO ESTUDIANTE (E1235) ---");
        
        Estudiante e1 = new Estudiante();
        e1.setId_Estudiante("E1235"); 
        e1.setNombre("Prueba MODIFICADA"); // Nombre cambiado en el update
        e1.setFecha_de_nacimiento("2020-06-07");
        e1.setEmail("java2@test.com");
        e1.setEdad(25);
        e1.setNota(10.0);
        e1.setTurnoManana(true);
        e1.setCurso(Curso.SEGUNDO);
        
        // Entidad de prueba
        Entidad miEntidad = new Entidad(Tipo.UNIVERSIDAD, "Universidad Central", "Calle Java 1");
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
        e1.getEntidad().setNombre("Mates");
        
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
        
        
     // PRUEBAS DE FILTROS Y ORDENACIÓN AVANZADOS
        System.out.println("\n=== PRUEBAS AVANZADAS DE FILTROS Y ORDENACIÓN ===");

        // ✨ 1. FILTRO MONGO COMBINADO DOBLE (Turno AND Curso)
        // Buscamos: Turno Mañana (true) Y Curso SEGUNDO
        System.out.println("\n1. MONGO COMBI: Turno Mañana AND SEGUNDO:");
        List<Estudiante> comboMongo = estudianteS.buscarPorTurnoYCurso(true, Curso.SEGUNDO);
        for(Estudiante e : comboMongo) System.out.println("   - " + e.getNombre() + ", Curso: " + e.getCurso());

        // ✨ 2. ORDENACIÓN MONGO TRIPLE (Entidad, Curso, Nota)
        // Ranking: 1º Universidad (A-Z), 2º Curso (A-Z), 3º Nota (Descendente)
        System.out.println("\n2. ORDEN MONGO TRIPLE: Por Entidad, Curso y Nota:");
        List<Estudiante> ordenTriple = estudianteS.ordenarPorEntidadCursoYNota();
        for(Estudiante e : ordenTriple) {
            System.out.printf("   - %-30s | Curso: %s | Nota: %.1f\n", 
                               e.getNombre(), e.getCurso(), e.getNota());
        }

        // ✨ 3. FILTRO JAVA AVANZADO (Asignatura 'Programación' AND Nota >= 8.0)
        System.out.println("\n3. FILTRO JAVA AVANZADO: Aprobados en Programación (>= 5.0):");
        List<Estudiante> aprobadosProg = estudianteS.filtrarAprobadosProgramacion(5.0);
        for(Estudiante e : aprobadosProg) System.out.println("   - " + e.getNombre() + " (Nota: " + e.getNota() + ")");

        // ✨ 4. ORDENACIÓN JAVA AVANZADA (Ratio Edad / Nota)
        System.out.println("\n4. ORDENACIÓN JAVA: Por Ratio Edad/Nota (Calculado en RAM):");
        List<Estudiante> ordenRatio = estudianteS.ordenarPorRatioEdadNota();
        for(Estudiante e : ordenRatio) {
            double ratio = (double) e.getEdad() / e.getNota();
            System.out.printf("   - %-30s | Edad: %d | Nota: %.1f | Ratio: %.2f\n", 
                               e.getNombre(), e.getEdad(), e.getNota(), ratio);
        }

        // --- PRUEBAS SIMPLES (Ya incluidas antes) ---
        
        // Filtro Repositorio (MONGO QUERY) buscar por curso
        System.out.println("\nFILTRO MONGO: Estudiantes de CURSO SEGUNDO:");
        List<Estudiante> cursoSegundo = estudianteS.buscarPorCursoMongo(Curso.SEGUNDO);
        for(Estudiante e : cursoSegundo) System.out.println("    - " + e.getNombre() + ", Curso: " + e.getCurso());

        //Ordenación Repositorio (MONGO QUERY) ordenar por nota, solo salen las 10 mejores notas
        System.out.println("\nORDEN MONGO: Por Nota (Mayor a menor, DESCENDENTE):");
        List<Estudiante> mejoresNotas = estudianteS.leerOrdenadoPorNotaMongo();
        for(Estudiante e : mejoresNotas.subList(0, 10)) System.out.println("    - " + e.getNombre() + ": " + e.getNota());
        
        // Filtro Servicio (JAVA) filtro por edad 
        System.out.println("\nFILTRO JAVA: Estudiantes de 20 años o más:");
        List<Estudiante> mayores = estudianteS.filtrarPorEdadJava(20);
        for(Estudiante e : mayores) System.out.println("    - " + e.getNombre() + ", Edad: " + e.getEdad());

        // Ordenación Servicio (JAVA) ordenacion alfabetica por nombre
        System.out.println("\nORDEN JAVA: Por Nombre Alfabético:");
        List<Estudiante> nombresOrdenados = estudianteS.ordenarPorNombreJava();
        for(Estudiante e : nombresOrdenados) System.out.println("    - " + e.getNombre());


        // Borrar estudiante (Delete)
        System.out.println("\n--- BORRANDO ESTUDIANTE ---");
        
        // Borramos usando el ID
        try {
			estudianteS.delete("E1236");
		} catch (IdException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
        System.out.println("Estudiante E1235 borrado");
        
        // Imprimir la lista para ver que se ha borrado
        System.out.println("\n--- COMPROBANDO BORRADO (LISTA FINAL) ---");
        programa.mostrarLista(estudianteS);
        
        System.out.println("\n--- FIN DE LAS PRUEBAS ---");
    }
        

    // Método auxiliar   necesito instanciar la clase (al princio)
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