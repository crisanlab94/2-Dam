package servicio;

import java.util.List;

import modelo.Alumno;
import repositorio.RepositorioAlumno;

public class ServicioAlumno {
	RepositorioAlumno repoAlumno = new RepositorioAlumno();

	public ServicioAlumno() {
		super();
		this.repoAlumno = repoAlumno;
	}

	public RepositorioAlumno getRepoAlumno() {
		return repoAlumno;
	}

	public void setRepoAlumno(RepositorioAlumno repoAlumno) {
		this.repoAlumno = repoAlumno;
	}
	
	public List<Alumno> devolverTodos(){
		return this.repoAlumno.todosLosAlumno();
	}
	
	public List<Alumno> devolverNombreEmail(){
		return this.repoAlumno.getNombreYEmail();
	}

}
