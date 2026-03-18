package proyectoSpring.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "actividad")
public class Actividad {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    
    private Dificultad dificultad;
    
 // Indicamos que el "dueño" de la relación es el socio
    @ManyToMany(mappedBy = "actividades")
    private List<Socio> socios = new ArrayList<Socio>();

    
    
 public Actividad() {
	super();
}



 public Actividad(String nombre) {
	super();
	this.nombre = nombre;
 }

 


 public Actividad(String nombre, Dificultad dificultad) {
	super();
	this.nombre = nombre;
	this.dificultad = dificultad;
}



 public long getId() {
	return id;
 }



 public void setId(long id) {
	this.id = id;
 }



 public String getNombre() {
	return nombre;
 }



 public void setNombre(String nombre) {
	this.nombre = nombre;
 }



 public List<Socio> getSocios() {
	return socios;
 }



 public void setSocios(List<Socio> socios) {
	this.socios = socios;
 }


 

 public Dificultad getDificultad() {
	return dificultad;
}



 public void setDificultad(Dificultad dificultad) {
	this.dificultad = dificultad;
 }



 @Override
 public String toString() {
	return "Actividad [id=" + id + ", nombre=" + nombre + "]";
 }



  
 
 
    

}
