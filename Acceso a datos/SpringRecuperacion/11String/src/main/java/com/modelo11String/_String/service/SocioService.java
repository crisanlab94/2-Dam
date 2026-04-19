package com.modelo11String._String.service;


import java.time.LocalDate;
import java.util.List;
import com.modelo11String._String.modelo.*;

/**
 * Interfaz que define todos los métodos de negocio para Socios y Carnets.
 */
public interface SocioService {

    // --- Operaciones de Socio ---
    Socio findSocioByDni(String dni);
    Socio registrarSocio(Socio s);
    Socio modificarSocio(Socio s);
    void eliminarSocio(String dni);
    
    // --- Búsquedas de Socio (del SocioRepository) ---
    List<Socio> buscarSociosPorNombre(String trozo);
    List<Socio> buscarSociosActivos();
    List<Socio> buscarSociosPorGenero(Genero genero);
    List<Socio> buscarSociosNacidosAntesDe(LocalDate fecha);

    // --- Operaciones de Carnet ---
    Carnet findCarnetById(String codigo);
    Carnet registrarCarnet(Carnet c);
    Socio asociarCarnet(String dniSocio, Carnet c);
    Socio actualizarNombre(String dni, String nuevoNombre);
    
    // --- Búsquedas de Carnet (del CarnetRepository) ---
    List<Carnet> buscarCarnetsCuotaSuperior(double cuotaMinima);
    Carnet buscarCarnetPorSocio(Socio s);
}
