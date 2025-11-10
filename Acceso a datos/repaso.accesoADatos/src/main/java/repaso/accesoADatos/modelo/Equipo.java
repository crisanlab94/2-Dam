package repaso.accesoADatos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipo {
	private int identificadorEquipo;
	private String nombreEquio;
	private int puntos;
	private List <Piloto> pilotos;
	
	public Equipo(int identificadorEquipo, String nombreEquio, int puntos, List<Piloto> pilotos) {
		super();
		this.identificadorEquipo = identificadorEquipo;
		this.nombreEquio = nombreEquio;
		this.puntos = puntos;
		this.pilotos = new ArrayList<Piloto>();
	}

	public int getIdentificadorEquipo() {
		return identificadorEquipo;
	}

	public void setIdentificadorEquipo(int identificadorEquipo) {
		this.identificadorEquipo = identificadorEquipo;
	}

	public String getNombreEquio() {
		return nombreEquio;
	}

	public void setNombreEquio(String nombreEquio) {
		this.nombreEquio = nombreEquio;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public List<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identificadorEquipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(identificadorEquipo, other.identificadorEquipo);
	}

	@Override
	public String toString() {
		return "Equipo [identificadorEquipo=" + identificadorEquipo + ", nombreEquio=" + nombreEquio + ", puntos="
				+ puntos + ", pilotos=" + pilotos + "]";
	}



	
	
	
	

}
