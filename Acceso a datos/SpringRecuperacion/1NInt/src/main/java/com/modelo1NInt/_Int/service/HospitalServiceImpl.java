package com.modelo1NInt._Int.service;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelo1NInt._Int.modelo.Especialidad;
import com.modelo1NInt._Int.modelo.Hospital;
import com.modelo1NInt._Int.modelo.Medico;
import com.modelo1NInt._Int.repositorio.HospitalRepository;
import com.modelo1NInt._Int.repositorio.MedicoRepository;

// IMPORTS DE TUS EXCEPCIONES
import exceptions.HospitalNotFoundException;
import exceptions.MedicoNotFoundException;
import exceptions.DuplicateIdException;

@Service
public class HospitalServiceImpl implements HospitalService {

    private static final Logger logger = LoggerFactory.getLogger(HospitalServiceImpl.class);

    @Autowired private HospitalRepository hRepo;
    @Autowired private MedicoRepository mRepo;

    @Override
    public Hospital findHospitalById(int id) {
        logger.info("Service: Buscando hospital con ID {}", id);
        return hRepo.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));
    }

    @Override
    public Medico findMedicoById(int id) {
        logger.info("Service: Buscando médico con ID {}", id);
        return mRepo.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException(id));
    }

    @Override
    public Hospital registrarHospital(Hospital h) {
        logger.info("Service: Registrando hospital ID manual {}", h.getId());
        if (hRepo.existsById(h.getId())) {
            throw new DuplicateIdException(h.getId());
        }
        return hRepo.save(h);
    }

    @Override
    public Medico registrarMedico(Medico m) {
        logger.info("Service: Registrando médico ID manual {}", m.getId());
        if (mRepo.existsById(m.getId())) {
            throw new DuplicateIdException(m.getId());
        }
        return mRepo.save(m);
    }

    @Override
    public void asignarMedicoAHospital(int idMedico, int idHospital) {
        logger.info("Service: Asignando médico {} al hospital {}", idMedico, idHospital);
        
        // Reutilizamos findById que ya lanzan nuestras excepciones
        Hospital h = this.findHospitalById(idHospital);
        Medico m = this.findMedicoById(idMedico);
        
        m.setHospital(h);
        h.getMedicos().add(m); // <--- ¡Añade esto para que el Hospital lo sepa!
        mRepo.save(m);
    }

    @Override
    public List<Medico> buscarEspecialistasActivos(Especialidad esp) {
        return mRepo.findByEspecialidadAndActivoTrue(esp);
    }

    @Override
    public List<Medico> buscarMedicosEnHospitalesPrivados() {
        return mRepo.findByHospitalPublicoFalse();
    }

    @Override
    public List<Medico> buscarPorRangoFecha(LocalDate inicio, LocalDate fin) {
        return mRepo.findByFechaContratacionBetween(inicio, fin);
    }

    @Override
    public List<Hospital> buscarHospitalesPublicosPorNombre(String trozo) {
        return hRepo.findByPublicoTrueAndNombreContaining(trozo);
    }
}