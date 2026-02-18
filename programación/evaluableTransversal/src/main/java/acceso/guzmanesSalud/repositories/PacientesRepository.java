package acceso.guzmanesSalud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import acceso.guzmanesSalud.models.ConstantesVitales;
import acceso.guzmanesSalud.models.Paciente;


public interface PacientesRepository extends JpaRepository<PacientesRepository,Long>{
	//Paciente findPacienteById(long idPaciente);
	//public  Paciente crearPaciente(Paciente paciente);
	//public ConstantesVitales agregarConstantesViatles(long idConstantes, ConstantesVitales constantes);
	//public Paciente save(Paciente paciente);

}
