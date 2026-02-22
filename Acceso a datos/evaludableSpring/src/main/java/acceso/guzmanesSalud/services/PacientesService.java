package acceso.guzmanesSalud.services;

import acceso.guzmanesSalud.models.ConstantesVitales;
import acceso.guzmanesSalud.models.Paciente;





public interface PacientesService {

	public Paciente findPacienteById(long idPaciente);
	public  Paciente crearPaciente(Paciente paciente);
	public ConstantesVitales agregarConstantesViatales(long idConstantes, ConstantesVitales constantes);
	
	
	public  ConstantesVitales crearConstante(ConstantesVitales constantesVitales);
	

}
