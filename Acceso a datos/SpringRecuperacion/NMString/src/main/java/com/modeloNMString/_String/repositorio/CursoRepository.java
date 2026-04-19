package com.modeloNMString._String.repositorio;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modeloNMString._String.modelo.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, String> {

    // 1. MÉTODOS ESTÁNDAR
    Optional<Curso> findById(String codigo);
    List<Curso> findAll();
    Curso save(Curso c);
    boolean existsById(String codigo);
    void deleteById(String codigo);

    // 2. FILTROS POR ATRIBUTOS PROPIOS
    List<Curso> findByTituloContaining(String titulo);
    List<Curso> findByActivoTrue();
    
    // 3. FILTROS DE NAVEGACIÓN (N:M)
    // "Búscame cursos donde esté matriculado un alumno con este nombre"
    List<Curso> findByEstudiantesNombreContaining(String nombre);
    
    // "Cursos donde participa un DNI específico"
    List<Curso> findByEstudiantesDni(String dni);
}
