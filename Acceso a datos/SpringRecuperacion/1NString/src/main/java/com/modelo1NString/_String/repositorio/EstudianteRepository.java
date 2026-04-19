package com.modelo1NString._String.repositorio;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo1NString._String.modelo.Estudiante;
import com.modelo1NString._String.modelo.Genero;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {
    Optional<Estudiante> findById(String id);
    List<Estudiante> findAll();
    Estudiante save(Estudiante e);
    void deleteById(String id);
    boolean existsById(String id);

    // Filtros Complejos
    List<Estudiante> findByActivoTrueAndGenero(Genero genero);
    List<Estudiante> findByFechaIngresoBetween(LocalDate inicio, LocalDate fin);
    
    // Navegación: Alumnos de colegios públicos
    List<Estudiante> findByColegioPublicoTrue();
    
    // Filtro cruzado: Nombre de alumno y nombre de colegio
    List<Estudiante> findByNombreContainingAndColegioNombreContaining(String nomEst, String nomCol);
}
