package com.modelo1NString._String.service;

import java.time.LocalDate;
import java.util.List;

import com.modelo1NString._String.modelo.Colegio;
import com.modelo1NString._String.modelo.Estudiante;

public interface ColegioService {
    // Registros
    Colegio registrarColegio(Colegio c);
    Estudiante registrarEstudiante(Estudiante e);
    void asignarEstudiante(String dni, String codigoCentro);

    // Búsquedas por ID
    Colegio findColegioById(String id);
    Estudiante findEstudianteById(String id);

    // Filtros Complejos
    List<Estudiante> buscarMujeresActivas();
    List<Estudiante> buscarIngresosRango(LocalDate inicio, LocalDate fin);
    List<Estudiante> buscarAlumnosEnCentrosPublicos();
    List<Colegio> buscarColegiosPublicosNuevos(LocalDate fecha);
}