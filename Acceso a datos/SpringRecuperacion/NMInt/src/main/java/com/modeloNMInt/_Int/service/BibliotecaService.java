package com.modeloNMInt._Int.service;


import java.util.List;
import com.modeloNMInt._Int.modelo.Autor;
import com.modeloNMInt._Int.modelo.Libro;

public interface BibliotecaService {
    // Búsqueda
    Autor findAutorById(int id);
    Libro findLibroById(int id);
    List<Autor> findAllAutores();
    
    // Registro (ID Manual)
    Autor registrarAutor(Autor a);
    Libro registrarLibro(Libro l);
    
    // N:M
    void vincularAutorConLibro(int idAutor, int idLibro);
    
    // Filtros complejos
    List<Autor> buscarAutoresPorLibro(String tituloLibro);
    List<Libro> buscarLibrosPorAutor(String nombreAutor);
    List<Autor> buscarPorNacionalidad(String nacionalidad);
}
