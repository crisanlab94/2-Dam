package com.modelo11IntAuto._IntAuto.repositorio;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo11IntAuto._IntAuto.modelo.Genero;
import com.modelo11IntAuto._IntAuto.modelo.Paciente;

/**
 * @Repository: Anotación obligatoria para que Spring gestione el Bean[cite: 181, 220].
 * Heredamos de JpaRepository para tener métodos avanzados como flush() y deleteById()[cite: 219, 224].
 */
@Repository 
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // 1. Encontrar por nombre (devuelve lista por si hay repetidos) [cite: 203]
    List<Paciente> findByNombre(String nombre);

    // 2. Encontrar por asegurado (Boolean) [cite: 216]
    List<Paciente> findByAsegurado(boolean asegurado);

    // 3. Encontrar por género (Enum) [cite: 209]
    List<Paciente> findByGenero(Genero genero);

    // 4. Encontrar por fecha de nacimiento (LocalDate) [cite: 214]
    List<Paciente> findByFechaNacimiento(LocalDate fechaNacimiento);
    
    /**
     * Buscar por ID: Aunque heredas uno, si ella pide "añadir la cabecera", 
     * usamos Optional porque puede ser que el ID no exista en la BD.
     */
    Optional<Paciente> findById(Long id);
}
