package simulacion.modelo;


public class Estudiante {
		   private int id;
		   private String nombre;
		   private double notaMedia;
		   private int id_ciudad;
		
		   public Estudiante() {
			super();
			// TODO Auto-generated constructor stub
		}

		   public Estudiante(int id, String nombre, double notaMedia, int id_ciudad) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.notaMedia = notaMedia;
			this.id_ciudad = id_ciudad;
		   }

		   public int getId() {
			   return id;
		   }

		   public void setId(int id) {
			   this.id = id;
		   }

		   public String getNombre() {
			   return nombre;
		   }

		   public void setNombre(String nombre) {
			   this.nombre = nombre;
		   }

		   public double getNotaMedia() {
			   return notaMedia;
		   }

		   public void setNotaMedia(double notaMedia) {
			   this.notaMedia = notaMedia;
		   }

		   public int getId_ciudad() {
			   return id_ciudad;
		   }

		   public void setId_ciudad(int id_ciudad) {
			   this.id_ciudad = id_ciudad;
		   }

		   @Override
		   public String toString() {
			return "Estudiante [id=" + id + ", nombre=" + nombre + ", notaMedia=" + notaMedia + ", id_ciudad="
					+ id_ciudad + "]";
		   }

		 

}
