package modelo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
	@Table(name = "reunion")
	public class Reunion {
		// Este campo es la clave primaria
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int idReunion; 
	//Es obligatorio usar la notación @Column(name="nombreCampo")
		// Si las columnas de la tablas se llaman diferentes que los atributos
		// @Column(name="fecha")
		private LocalDateTime fecha;
		// @Column(name="asunto")
		private String asunto;
		
		@ManyToOne(cascade = CascadeType.PERSIST)
		@JoinColumn(name="idSala")
		private Sala sala;
		
		@ManyToMany(mappedBy="reuniones", cascade = CascadeType.PERSIST)
		private Set<Persona> personas;
		
		
		
		
		public Reunion(int idReunion, LocalDateTime fecha, String asunto, Sala sala) {
			super();
			this.idReunion = idReunion;
			this.fecha = fecha;
			this.asunto = asunto;
			this.sala = sala;
			this.personas =new HashSet <Persona>();
		}



		public Reunion(LocalDateTime fecha, String asunto) {
			super();
			this.idReunion = idReunion;
			this.fecha = fecha;
			this.asunto = asunto;
			this.personas =new HashSet <Persona>();
		}

		
		
		public Reunion(LocalDateTime fecha, String asunto, Sala sala) {
			super();
			this.idReunion = idReunion;
			this.fecha = fecha;
			this.asunto = asunto;
			this.sala = sala;
			this.personas =new HashSet <Persona>();
		}


		public Reunion() {
			super();
			this.personas =new HashSet <Persona>();
		}


		public int getIdReunion() {
			return idReunion;
		}
		public void setIdReunion(int idReunion) {
			this.idReunion = idReunion;
		}
		public LocalDateTime getFecha() {
			return fecha;
		}
		public void setFecha(LocalDateTime fecha) {
			this.fecha = fecha;
		}
		public String getAsunto() {
			return asunto;
		}
		public void setAsunto(String asunto) {
			this.asunto = asunto;
		}


		public Sala getSala() {
			return sala;
		}


		public void setSala(Sala sala) {
			this.sala = sala;
		}

		

		public Set<Persona> getPersonas() {
			return personas;
		}



		public void addPersona (Persona p) {
			this.personas.add(p);
			if(!p.getReuniones().contains(this))
			{
				p.getReuniones().add(this);
			}
		}
		
		public void removePersona (Persona p) {
			this.personas.remove(p);
			if(!p.getReuniones().contains(this))
			{
				p.getReuniones().remove(this);
			}
		}
		

		@Override
		public String toString() {
			return "Reunion [idReunion=" + idReunion + ", fecha=" + fecha + ", asunto=" + asunto + ", sala=" + sala
					+ "]";
		} 
	
		
		
		
		
	}



