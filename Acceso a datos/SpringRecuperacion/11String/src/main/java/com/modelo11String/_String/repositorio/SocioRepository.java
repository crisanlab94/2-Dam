package com.modelo11String._String.repositorio;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo11String._String.modelo.Genero;
import com.modelo11String._String.modelo.Socio;


@Repository
public interface SocioRepository extends JpaRepository<Socio, String> {

    // Buscar por DNI (ID): Heredado de JpaRepository, devuelve Optional
    Optional<Socio> findById(String dni);

    // Búsqueda por trozo de nombre (LIKE %trozo%)
    List<Socio> findByNombreContaining(String trozo);

    // No necesita parámetros porque el valor "True" es parte del método
    List<Socio> findByActivoTrue();

    // Filtro por enumerado
    List<Socio> findByGenero(Genero genero);

    // Filtro por fecha (nacidos antes de...)
    List<Socio> findByFechaNacimientoBefore(LocalDate fecha);
}
