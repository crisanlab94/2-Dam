package com.modeloNMString._String.repositorio;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modeloNMString._String.modelo.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    // 1. MÉTODOS ESTÁNDAR (Explícitos para el examen)
    Optional<Estudiante> findById(String dni);
    List<Estudiante> findAll();
    Estudiante save(Estudiante e);
    boolean existsById(String dni);
    void deleteById(String dni);

    // 2. FILTROS POR ATRIBUTOS PROPIOS
    List<Estudiante> findByNombreContaining(String trozo); // Like %trozo%
    List<Estudiante> findByBecadoTrue();                   // Solo becados
    List<Estudiante> findByBecadoFalse();                  // Solo no becados

    // 3. FILTROS DE NAVEGACIÓN (N:M)
    // "Búscame estudiantes que estén apuntados a un curso cuyo título contenga..."
    List<Estudiante> findByCursosTituloContaining(String titulo);
    
    // "Búscame estudiantes apuntados a un curso concreto por su código"
    List<Estudiante> findByCursosCodigoCurso(String codigo);
}
