package com.modeloNMString._String.service;



import java.util.List;

import com.modeloNMString._String.modelo.Curso;
import com.modeloNMString._String.modelo.Estudiante;

public interface AcademiaService {

    // --- MÉTODOS DE BÚSQUEDA ---
    
    /**
     * Localiza un estudiante por su DNI. 
     * Debe lanzar EstudianteNotFoundException si no existe.
     */
    Estudiante findEstudianteById(String dni);

    /**
     * Localiza un curso por su código.
     * Debe lanzar CursoNotFoundException si no existe.
     */
    Curso findCursoById(String codigo);

    List<Estudiante> findAllEstudiantes();

    List<Curso> findAllCursos();


    // --- MÉTODOS DE REGISTRO ---

    /**
     * Guarda un estudiante validando que el DNI no esté duplicado.
     */
    Estudiante registrarEstudiante(Estudiante e);

    /**
     * Guarda un curso validando que el código no esté duplicado.
     */
    Curso registrarCurso(Curso c);


    // --- GESTIÓN DE MATRICULACIÓN (N:M) ---

    /**
     * Vincula un alumno con un curso en la tabla intermedia.
     */
    void matricular(String dni, String codigoCurso);


    // --- MÉTODOS DE FILTRADO Y NAVEGACIÓN ---

    /**
     * Retorna estudiantes becados que pertenecen a un curso específico.
     */
    List<Estudiante> obtenerBecadosPorCurso(String tituloCurso);

    /**
     * Retorna todos los cursos en los que participa un alumno por su nombre.
     */
    List<Curso> obtenerCursosDeAlumno(String nombreAlumno);

    List<Estudiante> buscarEstudiantesPorNombre(String nombre);


    // --- MÉTODOS DE MANTENIMIENTO (CRUD) ---

    void modificarNombreEstudiante(String dni, String nuevoNombre);

    void eliminarEstudiante(String dni);
}