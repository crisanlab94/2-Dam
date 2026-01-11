package modeloExamen;

public class Enfrentamiento {

	    private int id; // Atributo
	    private String fecha;
	    private String descripcion;
	    private Videojuego videojuego;
	    //Cuando veas que un dato (como "G2") aparece en un sitio como identificador y en otro sitio con nombres como Ref, idRef, id_equipo, o codigoGanador, 
	    //no es un String. Es una señal de que ese enfrentamiento "pertenece" o "está relacionado" con el objeto Equipo.
	    private EquipoExamen EquipoGanador; // El codigoRef del XML  atributo
		

		
		public Enfrentamiento(int id, String fecha, String descripcion, Videojuego videojuego, EquipoExamen equipoGanador) {
			super();
			this.id = id;
			this.fecha = fecha;
			this.descripcion = descripcion;
			this.videojuego = videojuego;
			EquipoGanador = equipoGanador;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		

		public Videojuego getVideojuego() {
			return videojuego;
		}

		public void setVideojuego(Videojuego videojuego) {
			this.videojuego = videojuego;
		}

		

		public EquipoExamen getEquipoGanador() {
			return EquipoGanador;
		}

		public void setEquipoGanador(EquipoExamen equipoGanador) {
			EquipoGanador = equipoGanador;
		}

		@Override
		public String toString() {
			return "Enfrentamiento [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion + ", videojuego="
					+ videojuego + ", EquipoGanador=" + EquipoGanador + "]";
		}

		

		

		

	    
	}


