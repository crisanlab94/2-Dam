package mongoDB.Modelo;
import java.util.List;

public class Estudiante {
	   private int id;
	   private String name;
	   private double notaMedia;
	   private List<String> aficiones;
	   private Adress direcciones;
	   private List<Score> puntuaciones;
	   
	   
	   public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}


	   
	   public Estudiante(int id, String name, double notaMedia, List<String> aficiones, Adress direcciones,
			List<Score> puntuaciones) {
		super();
		this.id = id;
		this.name = name;
		this.notaMedia = notaMedia;
		this.aficiones = aficiones;
		this.direcciones = direcciones;
		this.puntuaciones = puntuaciones;
	   }



	   public int getId() {
		   return id;
	   }



	   public void setId(int id) {
		   this.id = id;
	   }



	   public String getName() {
		   return name;
	   }



	   public void setName(String name) {
		   this.name = name;
	   }



	   public double getNotaMedia() {
		   return notaMedia;
	   }



	   public void setNotaMedia(double notaMedia) {
		   this.notaMedia = notaMedia;
	   }



	   public List<String> getAficiones() {
		   return aficiones;
	   }



	   public void setAficiones(List<String> aficiones) {
		   this.aficiones = aficiones;
	   }



	   public Adress getDirecciones() {
		   return direcciones;
	   }



	   public void setDirecciones(Adress direcciones) {
		   this.direcciones = direcciones;
	   }



	   public List<Score> getPuntuaciones() {
		   return puntuaciones;
	   }



	   public void setPuntuaciones(List<Score> puntuaciones) {
		   this.puntuaciones = puntuaciones;
	   }



	   @Override
	   public String toString() {
		return "Estudiante [id=" + id + ", name=" + name + ", notaMedia=" + notaMedia + ", aficiones=" + aficiones
				+ ", direcciones=" + direcciones + ", puntuaciones=" + puntuaciones + "]";
	   }



	   
	   
}
	   
	   
	   
	 