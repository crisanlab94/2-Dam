package modeloExamen;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	private String identificadorEquipo;
	private String nombreEquipo;
	private int puntos;
	private List<Piloto> listaPilotos;
	
	public Equipo(String identificadorEquipo, String nombreEquipo, int puntos) {
		super();
		this.identificadorEquipo = identificadorEquipo;
		this.nombreEquipo = nombreEquipo;
		this.puntos = puntos;
		this.listaPilotos = new ArrayList<Piloto>();
	}

	public String getIdentificadorEquipo() {
		return identificadorEquipo;
	}

	public void setIdentificadorEquipo(String identificadorEquipo) {
		this.identificadorEquipo = identificadorEquipo;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public List<Piloto> getListaPilotos() {
		return listaPilotos;
	}

	public void setListaPilotos(List<Piloto> listaPilotos) {
		this.listaPilotos = listaPilotos;
	}

	@Override
	public String toString() {
		return "Equipo [identificadorEquipo=" + identificadorEquipo + ", nombreEquipo=" + nombreEquipo + ", puntos="
				+ puntos + ", listaPilotos=" + listaPilotos + "]";
	}

	
	
	

}
