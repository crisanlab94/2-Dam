package modelo;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="jugador")
public class Jugador {
		@Id 
		private String dni;
		@Column(nullable = false)
		private String nombre;
		private String email;
		
		//Este es N por eso es many
		//Por ser fuerte no lleva mappedBy
		@ManyToOne
		@JoinColumn(name = "idEquipo")
		private Equipo equipo;

		public Jugador() {
			super();
		}

		public Jugador(String dni, String nombre, String email, Equipo equipo) {
			super();
			this.dni = dni;
			this.nombre = nombre;
			this.email = email;
			this.equipo = equipo;
		}



		public String getDni() {
			return dni;
		}

		public void setDni(String dni) {
			this.dni = dni;
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

		public Equipo getEquipo() {
			return equipo;
		}

		public void setEquipo(Equipo equipo) {
			this.equipo = equipo;
		}

		@Override
		public String toString() {
			return "Jugador [dni=" + dni + ", nombre=" + nombre + ", email=" + email + "]";
		}

	
		
	
		

}
