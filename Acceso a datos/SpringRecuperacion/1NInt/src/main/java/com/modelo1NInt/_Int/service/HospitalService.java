package com.modelo1NInt._Int.service;



import java.time.LocalDate;
import java.util.List;

import com.modelo1NInt._Int.modelo.Especialidad;
import com.modelo1NInt._Int.modelo.Hospital;
import com.modelo1NInt._Int.modelo.Medico;


public interface HospitalService {
    // Búsquedas por ID (Mandatorios)
    Hospital findHospitalById(int id);
    Medico findMedicoById(int id);

    // Registros (Validación ID manual)
    Hospital registrarHospital(Hospital h);
    Medico registrarMedico(Medico m);
    
    // Gestión 1:N
    void asignarMedicoAHospital(int idMedico, int idHospital);

    // Filtros solicitados y complejos
    List<Medico> buscarEspecialistasActivos(Especialidad esp);
    List<Medico> buscarMedicosEnHospitalesPrivados();
    List<Medico> buscarPorRangoFecha(LocalDate inicio, LocalDate fin);
    List<Hospital> buscarHospitalesPublicosPorNombre(String trozo);
}
