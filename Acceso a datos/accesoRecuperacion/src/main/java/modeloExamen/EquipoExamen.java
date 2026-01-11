package modeloExamen;

public class EquipoExamen {
	
		// Clase Equipo
		    private String codigo; // Atributo
		    private String nombre;
		    private String email;
		    private int numJugadores;
		    
			public EquipoExamen(String codigo, String nombre, String email, int numJugadores) {
				super();
				this.codigo = codigo;
				this.nombre = nombre;
				this.email = email;
				this.numJugadores = numJugadores;
			}

			public String getCodigo() {
				return codigo;
			}

			public void setCodigo(String codigo) {
				this.codigo = codigo;
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

			public int getNumJugadores() {
				return numJugadores;
			}

			public void setNumJugadores(int numJugadores) {
				this.numJugadores = numJugadores;
			}

			@Override
			public String toString() {
				return "Equipo [codigo=" + codigo + ", nombre=" + nombre + ", email=" + email + ", numJugadores="
						+ numJugadores + "]";
			}

		   
		    
		    
		}






