package acceso.guzmanesSalud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import acceso.guzmanesSalud.models.ConstantesVitales;
import acceso.guzmanesSalud.models.Paciente;




public interface PacientesRepository extends JpaRepository<Paciente,Long>{
	Paciente findPacienteById(long idPaciente);
	//public  Paciente create (Paciente paciente);
	public ConstantesVitales agregarConstantesVitales(long idConstantes, ConstantesVitales constantes);
	public Paciente save(Paciente paciente);

}
