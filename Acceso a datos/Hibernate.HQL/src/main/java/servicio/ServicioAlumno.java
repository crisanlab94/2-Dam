package servicio;

import java.util.List;

import modelo.Alumno;
import modelo.Curso;
import repositorio.RepositorioAlumno;

public class ServicioAlumno {
	RepositorioAlumno repoAlumno = new RepositorioAlumno();

	public ServicioAlumno() {
		super();
		this.repoAlumno = repoAlumno;
	}

public List<Alumno> devolverTodos() { 
	return this.repoAlumno.todosLosAlumno();
	}
    
  
    public List<Alumno> devolverNombreEmail() { 
    	return this.repoAlumno.getNombreYEmail();
    	}
    
    public List<Alumno> devolverMayores18() { 
    	return this.repoAlumno.getMayores18();
    	}
    public List<Alumno> devolverAlumnosAna() { 
    	return this.repoAlumno.getAlumnosAna(); 
    	}
    public List<Alumno> devolverOrdenadosEdad() { 
    	return this.repoAlumno.getAlumnosOrdenadosEdad();
    	}
    public List<Alumno> devolverMayoresQue(int edad) {
    	return this.repoAlumno.getAlumnosMayoresQue(edad); 
    	}
    public Alumno devolverPorEmail(String email) {
    	return this.repoAlumno.getAlumnoPorEmail(email); 
    	}
    public List<Alumno> devolverNombreContiene(String cadena) { 
    	return this.repoAlumno.getAlumnosNombreContiene(cadena); 
    	}
    public List<Alumno> devolverAlumnosDAM() { 
    	return this.repoAlumno.getAlumnosCursoDAM();
    	}
    public List<Object[]> devolverNombreAlumnoYCurso() { 
    	return this.repoAlumno.getNombreAlumnoYCurso(); 
    	}
    public List<Curso> devolverCursosConAlumnos() {
    	return this.repoAlumno.getCursosConAlumnos(); 
    	}
    public List<Alumno> devolverAlumnosNivelSuperior() {
    	return this.repoAlumno.getAlumnosNivelSuperior();
    	}
    public Long devolverContarAlumnos() { 
    	return this.repoAlumno.contarAlumnos(); 
    	}
    public Double devolverEdadMedia() { 
    	return this.repoAlumno.getEdadMedia();
    	}
    public List<Object[]> devolverNumeroAlumnosPorCurso() {
    	return this.repoAlumno.getNumeroAlumnosPorCurso(); 
    	}
    public Curso devolverCursoConMasAlumnos() {
    	return this.repoAlumno.getCursoConMasAlumnos(); 
    	}
    public List<Alumno> devolverAlumnosSinCurso() { 
    	return this.repoAlumno.getAlumnosSinCurso(); 
    	}
    public List<Curso> devolverCursosSinAlumnos() { 
    	return this.repoAlumno.getCursosSinAlumnos(); 
    	}
    public List<Alumno> devolverAlumnosEdadMayorMedia() { 
    	return this.repoAlumno.getAlumnosEdadMayorMedia(); 
    	}
    public List<String> devolverNombresCursosMayores25() {
    	return this.repoAlumno.getNombresCursosMayores25();
    	}
    
 

    public Integer devolverEdadMaxima() {
        return this.repoAlumno.getEdadMaxima();
    }

    public Alumno devolverAlumnoMasJoven() {
        return this.repoAlumno.getAlumnoMasJoven();
    }

    public List<Alumno> devolverAlumnosConCursosOptimizados() {
        return this.repoAlumno.getAlumnosConCursosOptimizados();
    }

    public int subirEdadCurso(String nombreCurso) {
        return this.repoAlumno.incrementarEdadCurso(nombreCurso);
    }

    public int eliminarAlumnosSinEmail() {
        return this.repoAlumno.borrarAlumnosSinEmail();
    }

    public List<Alumno> devolverAlumnosCursoEdadRango(String nombreCurso, int min, int max) {
        return this.repoAlumno.getAlumnosCursoEdadRango(nombreCurso, min, max);
    }

    public List<Alumno> devolverAlumnosEmailYNivel(String patron, String nivel) {
        return this.repoAlumno.getAlumnosEmailYNivel(patron, nivel);
    }

    public List<Object[]> devolverNombreEdadDeVariosCursos(List<String> nombresCursos) {
        return this.repoAlumno.getNombreEdadDeVariosCursos(nombresCursos);
    }

    public List<Alumno> devolverAlumnosFiltradoEspecial(String nombreProhibido, int edadMin) {
        return this.repoAlumno.getAlumnosFiltradoEspecial(nombreProhibido, edadMin);
    }

    public List<Alumno> devolverTop3JovenesPorCurso(String nombreCurso) {
        return this.repoAlumno.getTop3JovenesPorCurso(nombreCurso);
    }

    public List<Alumno> devolverAlumnosConCursosFetch() {
        return this.repoAlumno.getAlumnosConCursosFetch();
    }

    public List<Curso> devolverCursosDondeEstaElAlumno(Alumno alumno) {
        return this.repoAlumno.getCursosDondeEstaElAlumno(alumno);
    }

    public List<Alumno> devolverAlumnosPaginados(int inicio, int cantidad) {
        return this.repoAlumno.getAlumnosPaginados(inicio, cantidad);
    }

    public Long devolverSumaEdades() {
        return this.repoAlumno.getSumaEdades();
    }

    public List<Object[]> devolverCursosConMediaSuperiorA(Double media) {
        return this.repoAlumno.getCursosConMediaSuperiorA(media);
    }

    public List<Alumno> devolverAlumnosNombreMinusculas(String nombre) {
        return this.repoAlumno.getAlumnosNombreMinusculas(nombre);
    }

    public List<String> devolverNivelesDistintos() {
        return this.repoAlumno.getNivelesDistintos();
    }
    
    
    public List<String> devolverContactosUnidos() {
        return this.repoAlumno.getNombresYEmailsUnidos();
    }

    public List<Alumno> devolverNombresLargos() {
        return this.repoAlumno.getAlumnosNombreLargo();
    }

    public List<Curso> devolverCursosConDAW() {
        return this.repoAlumno.getCursosConAlumnosDeDAW();
    }


    public RepositorioAlumno getRepoAlumno() {
    	return repoAlumno; 
    	}
    public void setRepoAlumno(RepositorioAlumno repoAlumno) {
    	this.repoAlumno = repoAlumno;
    	}
}
