package com.modelo11Int._Int.repositorio;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo11Int._Int.modelo.Director;


@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

    // 1. Búsqueda por ID (Clave Primaria Manual)
    Optional<Director> findById(Integer id);

    // 2. Búsqueda por nombre (Exacta y Parcial)
    List<Director> findByNombre(String nombre);
    List<Director> findByNombreContaining(String trozo);

    // 3. Filtros numéricos para el sueldo
    List<Director> findBySueldoGreaterThan(double sueldo);
    List<Director> findBySueldoLessThan(double sueldo);
    List<Director> findBySueldoBetween(double min, double max);

    // 4. Filtros por fecha de nombramiento
    List<Director> findByFechaNombramientoBefore(LocalDate fecha);
    List<Director> findByFechaNombramientoAfter(LocalDate fecha);

    // 5. Búsqueda por relación (ID del Departamento asociado)
    Optional<Director> findByDepartamentoId(int idDepto);
}
