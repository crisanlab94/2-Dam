package com.modeloNMInt._Int.repositorio;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.modeloNMInt._Int.modelo.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    // 1. MÉTODOS ESTÁNDAR
    Optional<Libro> findById(int id);
    List<Libro> findAll();
    Libro save(Libro l);
    boolean existsById(int id);
    void deleteById(int id);

    // 2. FILTROS POR ATRIBUTOS PROPIOS
    List<Libro> findByTituloContaining(String trozo);
    List<Libro> findByGenero(String genero);

    // 3. FILTROS DE NAVEGACIÓN (N:M)
    // "Búscame libros escritos por un autor con este nombre"
    List<Libro> findByAutoresNombreContaining(String nombreAutor);
    
    // "Libros vinculados a un ID de autor específico"
    List<Libro> findByAutoresIdAutor(int idAutor);
}
