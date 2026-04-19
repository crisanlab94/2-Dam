package com.modeloNMInt._Int.repositorio;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modeloNMInt._Int.modelo.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {

    // 1. MÉTODOS ESTÁNDAR (Explícitos para el examen)
    Optional<Autor> findById(int id);
    List<Autor> findAll();
    Autor save(Autor a);
    boolean existsById(int id);
    void deleteById(int id);

    // 2. FILTROS POR ATRIBUTOS PROPIOS
    List<Autor> findByNombreContaining(String trozo);
    List<Autor> findByNacionalidad(String nacionalidad);
    
    // 3. FILTROS DE NAVEGACIÓN (N:M)
    // "Búscame autores que hayan escrito un libro con este título"
    List<Autor> findByLibrosTituloContaining(String titulo);
    
    // "Búscame autores que participen en un ID de libro concreto"
    List<Autor> findByLibrosIdLibro(int idLibro);
}
