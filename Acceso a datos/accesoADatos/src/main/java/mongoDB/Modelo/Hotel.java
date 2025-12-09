package mongoDB.Modelo;

import java.util.List;

public class Hotel {
	private String idHotel;
	private String nombre;
	private int estrellas;
	private boolean admiteMascotas;
	private String fechaApertura;
	private Ubicacion ubicacion;
	private List<Habitaciones>habitaciones;
	
	public Hotel() {
		super();
	}

	public Hotel(String idHotel, String nombre, int estrellas, boolean admiteMascotas, String fechaApertura,
			Ubicacion ubicacion, List<Habitaciones> habitaciones) {
		super();
		this.idHotel = idHotel;
		this.nombre = nombre;
		this.estrellas = estrellas;
		this.admiteMascotas = admiteMascotas;
		this.fechaApertura = fechaApertura;
		this.ubicacion = ubicacion;
		this.habitaciones = habitaciones;
	}

	public String getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(String idHotel) {
		this.idHotel = idHotel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public boolean isAdmiteMascotas() {
		return admiteMascotas;
	}

	public void setAdmiteMascotas(boolean admiteMascotas) {
		this.admiteMascotas = admiteMascotas;
	}

	public String getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Habitaciones> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<Habitaciones> habitaciones) {
		this.habitaciones = habitaciones;
	}

	@Override
	public String toString() {
		return "Hotel [idHotel=" + idHotel + ", nombre=" + nombre + ", estrellas=" + estrellas + ", admiteMascotas="
				+ admiteMascotas + ", fechaApertura=" + fechaApertura + "]";
	}
	
	
	
	
}
