package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumnos")

public class Alumno {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idAlumno;
	    private String nombre;
	    private String email;
	    private int edad;
	    @ManyToOne
	    @JoinColumn(name = "idCurso")
	    private Curso curso;
	    
	    
		public Long getIdAlumno() {
			return idAlumno;
		}
		public void setIdAlumno(Long idAlumno) {
			this.idAlumno = idAlumno;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getEdad() {
			return edad;
		}
		public void setEdad(int edad) {
			this.edad = edad;
		}
		public Curso getCurso() {
			return curso;
		}
		public void setCurso(Curso curso) {
			this.curso = curso;
		}
		public Alumno(Long idAlumno, String nombre, String email, int edad, Curso curso) {
			super();
			this.idAlumno = idAlumno;
			this.nombre = nombre;
			this.email = email;
			this.edad = edad;
			this.curso = curso;
		}
		public Alumno() {
			super();
		}
		@Override
		public String toString() {
			return "Alumno [idAlumno=" + idAlumno + ", nombre=" + nombre + ", email=" + email + ", edad=" + edad;
		}
		public Alumno(String nombre, String email) {
			super();
			this.nombre = nombre;
			this.email = email;
		}


	    
	    

}
