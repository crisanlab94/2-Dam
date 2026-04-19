package com.modeloNMInt._Int.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modeloNMInt._Int.modelo.Autor;
import com.modeloNMInt._Int.modelo.Libro;
import com.modeloNMInt._Int.repositorio.AutorRepository;
import com.modeloNMInt._Int.repositorio.LibroRepository;

import exceptions.AutorNotFoundException;
import exceptions.DuplicateIdException;
import exceptions.LibroNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BibliotecaServiceImpl implements BibliotecaService {

    private static final Logger logger = LoggerFactory.getLogger(BibliotecaServiceImpl.class);

    @Autowired private AutorRepository autorRepo;
    @Autowired private LibroRepository libroRepo;

    @Override
    public Autor findAutorById(int id) {
        return autorRepo.findById(id).orElseThrow(() -> new AutorNotFoundException(id));
    }

    @Override
    public Libro findLibroById(int id) {
        return libroRepo.findById(id).orElseThrow(() -> new LibroNotFoundException(id));
    }

    @Override
    public Autor registrarAutor(Autor a) {
        logger.info("Service: Registrando autor ID manual {}", a.getIdAutor());
        if (autorRepo.existsById(a.getIdAutor())) throw new DuplicateIdException(a.getIdAutor());
        return autorRepo.save(a);
    }

    @Override
    public Libro registrarLibro(Libro l) {
        logger.info("Service: Registrando libro ID manual {}", l.getIdLibro());
        if (libroRepo.existsById(l.getIdLibro())) throw new DuplicateIdException(l.getIdLibro());
        return libroRepo.save(l);
    }

    @Transactional
    @Override
    public void vincularAutorConLibro(int idAutor, int idLibro) {
        Autor a = this.findAutorById(idAutor);
        Libro l = this.findLibroById(idLibro);
        
        // El dueño es Libro, añadimos el autor a su lista
        if (!l.getAutores().contains(a)) {
            l.getAutores().add(a);
            a.getLibros().add(l); // <--- ¡Añade esto para que el Autor lo sepa!
            libroRepo.save(l);
        }
    }

    @Override
    public List<Autor> buscarAutoresPorLibro(String tituloLibro) {
        return autorRepo.findByLibrosTituloContaining(tituloLibro);
    }

    @Override
    public List<Libro> buscarLibrosPorAutor(String nombreAutor) {
        return libroRepo.findByAutoresNombreContaining(nombreAutor);
    }

    @Override
    public List<Autor> buscarPorNacionalidad(String nacionalidad) {
        return autorRepo.findByNacionalidad(nacionalidad);
    }

    @Override
    public List<Autor> findAllAutores() {
        return autorRepo.findAll();
    }
}
