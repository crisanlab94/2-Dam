package com.modelo11String._String.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelo11String._String.modelo.Carnet;
import com.modelo11String._String.modelo.Genero;
import com.modelo11String._String.modelo.Socio;
import com.modelo11String._String.repositorio.CarnetRepository;
import com.modelo11String._String.repositorio.SocioRepository;

import exceptions.DniInvalidoException;
import exceptions.DuplicateIdException;
import exceptions.SocioNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SocioServiceImpl implements SocioService {

    // Logger siempre para trazar cada operación (Requisito de examen)
    private static final Logger logger = LoggerFactory.getLogger(SocioServiceImpl.class);

    @Autowired
    private SocioRepository sRepo;

    @Autowired
    private CarnetRepository cRepo;

    // ============================================================
    // SECCIÓN: SOCIO - CRUD Y BÚSQUEDAS POR ID
    // ============================================================

    @Override
    public Socio findSocioByDni(String dni) {
        logger.info("Service: Iniciando búsqueda de socio con DNI: {}", dni);
        Optional<Socio> opt = sRepo.findById(dni);
        // UN SOLO RETURN: Usamos orElseThrow para lanzar la excepción personalizada
        return opt.orElseThrow(() -> new SocioNotFoundException("No existe el socio con DNI: " + dni));
    }

    @Override
    public Socio registrarSocio(Socio s) {
        logger.info("Service: Intentando registrar socio: {}", s.getNombre());
        
        // 1. Validación de longitud de DNI (Regla de negocio manual)
        if (s.getDni() == null || s.getDni().length() != 9) {
            logger.error("Service: DNI inválido detectado: {}", s.getDni());
            throw new DniInvalidoException("El DNI [" + s.getDni() + "] no tiene 9 caracteres");
        }
        
        // 2. Validación de duplicados (Imprescindible en ID manual String)
        if (sRepo.existsById(s.getDni())) {
            logger.warn("Service: Intento de registro duplicado para DNI: {}", s.getDni());
            throw new DuplicateIdException("Error: Ya existe un socio con este DNI.");
        }
        
        return sRepo.save(s);
    }
    
    

    @Override
    public Socio modificarSocio(Socio s) {
        logger.info("Service: Modificando socio con DNI: {}", s.getDni());
        // Verificamos existencia antes de modificar para no crear uno nuevo por error
        if (!sRepo.existsById(s.getDni())) {
        	throw new SocioNotFoundException("No se puede realizar la operación. No existe el socio con DNI: " + s.getDni());
        }
        return sRepo.save(s);
    }

    //Modificar solo el nombre
    
    @Override
    public Socio actualizarNombre(String dni, String nuevoNombre) {
        logger.info("Service: Intentando actualizar nombre del socio con DNI {}", dni);

        // 1. Buscamos al socio por su Clave Primaria (DNI)
        // Si no existe, lanza la excepción y corta la ejecución
        Socio socio = this.findSocioByDni(dni);

        // 2. Modificamos únicamente el campo del nombre
        socio.setNombre(nuevoNombre);

        // 3. Guardamos el objeto. Al tener el mismo DNI, JPA hace un UPDATE.
        logger.info("Service: Nombre cambiado a '{}' para el DNI {}", nuevoNombre, dni);
        return sRepo.save(socio);
    }
    
    //dni es id
    @Override
    public void eliminarSocio(String dni) {
        logger.info("Service: Eliminando socio DNI: {}", dni);
        if (!sRepo.existsById(dni)) {
        	throw new SocioNotFoundException("No se puede realizar la operación. No existe el socio con DNI: " + dni);
        }
        sRepo.deleteById(dni);
    }

    // ============================================================
    // SECCIÓN: SOCIO - FILTROS PERSONALIZADOS (SocioRepository)
    // ============================================================

    @Override
    public List<Socio> buscarSociosPorNombre(String trozo) {
        logger.info("Service: Filtrando socios por nombre que contenga: {}", trozo);
        return sRepo.findByNombreContaining(trozo);
    }

    @Override
    public List<Socio> buscarSociosActivos() {
        logger.info("Service: Recuperando lista de todos los socios activos");
        return sRepo.findByActivoTrue();
    }

    @Override
    public List<Socio> buscarSociosPorGenero(Genero genero) {
        logger.info("Service: Buscando socios por género: {}", genero);
        return sRepo.findByGenero(genero);
    }

    @Override
    public List<Socio> buscarSociosNacidosAntesDe(LocalDate fecha) {
        logger.info("Service: Buscando socios nacidos antes de: {}", fecha);
        return sRepo.findByFechaNacimientoBefore(fecha);
    }

    // ============================================================
    // SECCIÓN: CARNET - CRUD Y RELACIÓN 1:1
    // ============================================================

    @Override
    public Carnet findCarnetById(String codigo) {
        logger.info("Service: Localizando carnet por código: {}", codigo);
        Optional<Carnet> opt = cRepo.findById(codigo);
        return opt.orElseThrow(() -> new RuntimeException("Carnet no encontrado: " + codigo));
    }

    @Override
    public Carnet registrarCarnet(Carnet c) {
        logger.info("Service: Registrando carnet manual con código: {}", c.getCodigoBarras());
        if (cRepo.existsById(c.getCodigoBarras())) {
            throw new RuntimeException("Error: El código de carnet ya existe.");
        }
        return cRepo.save(c);
    }

    @Transactional
    @Override
    public Socio asociarCarnet(String dniSocio, Carnet c) {
        logger.info("Service: Vinculando carnet {} al socio con DNI {}", c.getCodigoBarras(), dniSocio);
        // Reutilizamos el método de búsqueda de socio para asegurar consistencia
        Socio s = this.findSocioByDni(dniSocio);
        c.setSocio(s);
        // Guardamos en carnet porque es el que tiene la @JoinColumn (dueño de la relación)
        s.setCarnet(c); //Para guardarlo en Java
        cRepo.save(c);
        return s;
        
        
    }

    // ============================================================
    // SECCIÓN: CARNET - FILTROS PERSONALIZADOS (CarnetRepository)
    // ============================================================

    @Override
    public List<Carnet> buscarCarnetsCuotaSuperior(double cuotaMinima) {
        logger.info("Service: Buscando carnets con cuota mayor a: {}", cuotaMinima);
        return cRepo.findByCuotaMensualGreaterThan(cuotaMinima);
    }

    @Override
    public Carnet buscarCarnetPorSocio(Socio s) {
        logger.info("Service: Localizando carnet asociado al socio: {}", s.getNombre());
        Optional<Carnet> opt = cRepo.findBySocio(s);
        return opt.orElseThrow(() -> new RuntimeException("El socio no tiene carnet asociado."));
    }
}