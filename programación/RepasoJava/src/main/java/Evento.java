import java.time.LocalDate;

public class Evento {
	private int id;
	private String nombre;
	private final LocalDate fecha ;
	private int entradasVendidas;
	private int capMaxAsistentes;
	private static int contador;
	
	public Evento(String nombre, LocalDate fecha, int entradasVendidas, int capMaxAsistentes) {
		super();
		this.id = contador+1;
		this.nombre = nombre;
		this.fecha = fecha;
		this.entradasVendidas = entradasVendidas;
		this.capMaxAsistentes = capMaxAsistentes;
		
	}
	
	
	

}
