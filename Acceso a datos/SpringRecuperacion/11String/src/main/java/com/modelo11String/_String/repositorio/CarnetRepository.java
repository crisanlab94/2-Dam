package com.modelo11String._String.repositorio;



import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo11String._String.modelo.Carnet;
import com.modelo11String._String.modelo.Socio;

/**
 * Repositorio para la entidad Carnet.
 * Maneja ID manual de tipo String (codigoBarras).
 */
@Repository
public interface CarnetRepository extends JpaRepository<Carnet, String> {

    // Buscar carnet por su ID (codigoBarras)
    // Spring ya lo trae, pero lo declaramos para mayor claridad
    Optional<Carnet> findById(String codigoBarras);

    // Búsqueda personalizada: Carnets con una cuota mayor a X
    List<Carnet> findByCuotaMensualGreaterThan(double cuota);

    // Búsqueda por la entidad relacionada (Socio)
    Optional<Carnet> findBySocio(Socio socio);
}
