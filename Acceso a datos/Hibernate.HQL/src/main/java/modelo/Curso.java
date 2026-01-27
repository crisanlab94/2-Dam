package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")

public class Curso {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;
    private String nombre;
    private String nivel;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Alumno> alumnos ;
    
    
    
	public Curso() {
		super();
		this.alumnos = new ArrayList<>();
	}

	public Curso(Long idCurso, String nombre, String nivel) {
		super();
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.nivel = nivel;
		this.alumnos = new ArrayList<>();
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nombre=" + nombre + ", nivel=" + nivel + ", alumnos=" + alumnos + "]";
	}

	

    
}
