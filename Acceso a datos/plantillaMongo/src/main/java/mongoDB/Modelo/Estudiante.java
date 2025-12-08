package mongoDB.Modelo;
import java.util.List;

public class Estudiante {
	//Clase principal (nombre de la coleccion)
	//Añadir el documento anidado en este caso Entidad(para ello hacer una clase con sus atributos)
	//Añadir la lista anidada(en este caso Asignatura) crear su clase con sus atributos
	   private String id_Estudiante;
	   private String nombre;
	   private String fecha_de_nacimiento;
	   private String email;
	   private int edad;
	   private Double nota;
	   private boolean turnoManana;
	   private Curso curso;
	   private Entidad entidad;
	   private List<Asignatura> asignatura;
	   
	   //Crear un constructor vacio
	   public Estudiante() {
		super();
	   }


	   public Estudiante(String id_Estudiante, String nombre, String fecha_de_nacimiento, String email, int edad,
			Double nota, boolean turnoManana, Curso curso, Entidad entidad, List<Asignatura> asignatura) {
		super();
		this.id_Estudiante = id_Estudiante;
		this.nombre = nombre;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.email = email;
		this.edad = edad;
		this.nota = nota;
		this.turnoManana = turnoManana;
		this.curso = curso;
		this.entidad = entidad;
		this.asignatura = asignatura;
	   }


	   public String getId_Estudiante() {
		   return id_Estudiante;
	   }


	   public void setId_Estudiante(String id_Estudiante) {
		   this.id_Estudiante = id_Estudiante;
	   }


	   public String getNombre() {
		   return nombre;
	   }


	   public void setNombre(String nombre) {
		   this.nombre = nombre;
	   }


	   public String getFecha_de_nacimiento() {
		   return fecha_de_nacimiento;
	   }


	   public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
		   this.fecha_de_nacimiento = fecha_de_nacimiento;
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


	   public Double getNota() {
		   return nota;
	   }


	   public void setNota(Double nota) {
		   this.nota = nota;
	   }


	   public boolean isTurnoManana() {
		   return turnoManana;
	   }


	   public void setTurnoManana(boolean turnoManana) {
		   this.turnoManana = turnoManana;
	   }


	   public Curso getCurso() {
		   return curso;
	   }


	   public void setCurso(Curso curso) {
		   this.curso = curso;
	   }


	   public Entidad getEntidad() {
		   return entidad;
	   }


	   public void setEntidad(Entidad entidad) {
		   this.entidad = entidad;
	   }


	   public List<Asignatura> getAsignatura() {
		   return asignatura;
	   }


	   public void setAsignatura(List<Asignatura> asignatura) {
		   this.asignatura = asignatura;
	   }


	   @Override
	   public String toString() {
		return "Estudiante [id_Estudiante=" + id_Estudiante + ", nombre=" + nombre + ", fecha_de_nacimiento="
				+ fecha_de_nacimiento + ", email=" + email + ", edad=" + edad + ", nota=" + nota + ", turnoManana="
				+ turnoManana + ", curso=" + curso + ", entidad=" + entidad + ", asignatura=" + asignatura + "]";
	   }
	   
	   
	   
	
	
	   
}
	   
	   
	   
	 