package com.modelo11IntAuto._IntAuto.service;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.modelo11IntAuto._IntAuto.modelo.Genero;
import com.modelo11IntAuto._IntAuto.modelo.Paciente;
import com.modelo11IntAuto._IntAuto.modelo.Expediente;

public interface PacienteService {
    
    // --- Operaciones base de Persistencia ---
    Paciente findPacienteById(long id);
    Expediente findExpedienteById(long id); 
    
    Paciente crearPaciente(Paciente p);
    Paciente agregarExpediente(long idPaciente, Expediente exp);
    
    /**
     * MODIFICAR PACIENTE:
     * Permite actualizar los datos personales de un paciente ya existente.
     */
    Paciente modificarPaciente(Paciente p);
    
    /**
     * MODIFICAR EXPEDIENTE:
     * Permite actualizar historial, peso, altura o fechas de un expediente.
     */
    Expediente modificarExpediente(Expediente exp);
    
    void eliminarPaciente(long id);
    
    /**
     * ELIMINAR EXPEDIENTE:
     * Borra el registro clínico sin eliminar al paciente de la base de datos.
     */
    void eliminarExpediente(long id);
    
    // --- Búsquedas personalizadas (Pacientes) ---
    List<Paciente> buscarPorNombre(String nombre);
    List<Paciente> buscarPorAsegurado(boolean asegurado);
    List<Paciente> buscarPorGenero(Genero genero);
    List<Paciente> buscarPorFechaNacimiento(LocalDate fecha);
    
    // --- Búsquedas personalizadas (Expediente) ---
    Expediente buscarExpedientePorPaciente(Paciente p);
    List<Expediente> buscarPorPeso(double peso);
    List<Expediente> buscarPorRangoDePeso(double min, double max);
    List<Expediente> buscarExpedientesRecientes(LocalDateTime desde);
}