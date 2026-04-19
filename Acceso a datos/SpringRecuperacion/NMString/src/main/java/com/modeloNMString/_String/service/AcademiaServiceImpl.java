package com.modeloNMString._String.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modeloNMString._String.modelo.Curso;
import com.modeloNMString._String.modelo.Estudiante;
import com.modeloNMString._String.repositorio.CursoRepository;
import com.modeloNMString._String.repositorio.EstudianteRepository;

// IMPORTANTE: Tus excepciones propias
import exceptions.CursoNotFoundException;
import exceptions.DuplicateIdException;
import exceptions.EstudianteNotFoundException;

@Service
public class AcademiaServiceImpl implements AcademiaService {

    private static final Logger logger = LoggerFactory.getLogger(AcademiaServiceImpl.class);

    @Autowired 
    private EstudianteRepository estRepo;
    
    @Autowired 
    private CursoRepository curRepo;

    // --- MÉTODOS DE BÚSQUEDA BÁSICA ---

    @Override
    public Estudiante findEstudianteById(String dni) {
        logger.info("Service: Buscando estudiante con DNI: {}", dni);
        return estRepo.findById(dni)
                .orElseThrow(() -> new EstudianteNotFoundException(dni));
    }

    @Override
    public Curso findCursoById(String codigo) {
        logger.info("Service: Buscando curso con código: {}", codigo);
        return curRepo.findById(codigo)
                .orElseThrow(() -> new CursoNotFoundException(codigo));
    }

    @Override
    public List<Estudiante> findAllEstudiantes() {
        return estRepo.findAll();
    }

    @Override
    public List<Curso> findAllCursos() {
        return curRepo.findAll();
    }

    // --- MÉTODOS DE REGISTRO (CON VALIDACIÓN ID MANUAL STRING) ---

    @Override
    public Estudiante registrarEstudiante(Estudiante e) {
        logger.info("Service: Registrando estudiante {}", e.getDni());
        if (estRepo.existsById(e.getDni())) {
            throw new DuplicateIdException(e.getDni());
        }
        return estRepo.save(e);
    }

    @Override
    public Curso registrarCurso(Curso c) {
        logger.info("Service: Registrando curso {}", c.getCodigoCurso());
        if (curRepo.existsById(c.getCodigoCurso())) {
            throw new DuplicateIdException(c.getCodigoCurso());
        }
        return curRepo.save(c);
    }

    // --- GESTIÓN DE RELACIÓN N:M (MANY-TO-MANY) ---

    @Transactional
    @Override
    public void matricular(String dni, String codigoCurso) {
        logger.info("Service: Matriculando alumno {} en curso {}", dni, codigoCurso);
        
        // Buscamos las entidades (lanzan excepción si no existen)
        Estudiante est = this.findEstudianteById(dni);
        Curso cur = this.findCursoById(codigoCurso);
        
        // Al ser N:M, añadimos a la lista del dueño (Curso)
        // Comprobamos si ya está matriculado para evitar duplicados en la lista de Java
        if (!cur.getEstudiantes().contains(est)) {
            cur.getEstudiantes().add(est);
            curRepo.save(cur); 
            logger.info("Matriculación realizada con éxito.");
        } else {
            logger.warn("El alumno ya estaba matriculado en este curso.");
        }
    }

    // --- FILTROS COMPLEJOS Y NAVEGACIÓN ---

    @Override
    public List<Estudiante> obtenerBecadosPorCurso(String tituloCurso) {
        logger.info("Service: Buscando becados en cursos que contienen '{}'", tituloCurso);
        // Navegación: Estudiante -> Cursos -> Titulo
        return estRepo.findByCursosTituloContaining(tituloCurso);
    }

    @Override
    public List<Curso> obtenerCursosDeAlumno(String nombreAlumno) {
        logger.info("Service: Buscando cursos del alumno '{}'", nombreAlumno);
        // Navegación: Curso -> Estudiantes -> Nombre
        return curRepo.findByEstudiantesNombreContaining(nombreAlumno);
    }

    @Override
    public List<Estudiante> buscarEstudiantesPorNombre(String nombre) {
        return estRepo.findByNombreContaining(nombre);
    }

    // --- MÉTODOS DE ACTUALIZACIÓN Y BORRADO ---

    @Override
    public void modificarNombreEstudiante(String dni, String nuevoNombre) {
        Estudiante est = this.findEstudianteById(dni);
        est.setNombre(nuevoNombre);
        estRepo.save(est);
        logger.info("Nombre del estudiante {} actualizado a {}", dni, nuevoNombre);
    }

    @Override
    public void eliminarEstudiante(String dni) {
        logger.info("Service: Eliminando estudiante {}", dni);
        if (!estRepo.existsById(dni)) {
            throw new EstudianteNotFoundException(dni);
        }
        estRepo.deleteById(dni);
    }
}