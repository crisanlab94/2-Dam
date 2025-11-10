package repaso.accesoADatos.modelo;

import java.util.Objects;

public class Piloto {
	private int identificadorPiloto;
	private String nombrePiloto;
	private int puntos;
	private int identificadorEquipo;
	private String pais;
	
	
	public Piloto(int identificadorPiloto, String nombrePiloto, int puntos, int identificadorEquipo,
			String pais) {
		super();
		this.identificadorPiloto = identificadorPiloto;
		this.nombrePiloto = nombrePiloto;
		this.puntos = puntos;
		this.identificadorEquipo = identificadorEquipo;
		this.pais = pais;
	}


	public int getIdentificadorPiloto() {
		return identificadorPiloto;
	}


	public void setIdentificadorPiloto(int identificadorPiloto) {
		this.identificadorPiloto = identificadorPiloto;
	}


	public String getNombrePiloto() {
		return nombrePiloto;
	}


	public void setNombrePiloto(String nombrePiloto) {
		this.nombrePiloto = nombrePiloto;
	}


	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


	public int getIdentificadorEquipo() {
		return identificadorEquipo;
	}


	public void setIdentificadorEquipo(int identificadorEquipo) {
		this.identificadorEquipo = identificadorEquipo;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	@Override
	public int hashCode() {
		return Objects.hash(identificadorPiloto);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piloto other = (Piloto) obj;
		return Objects.equals(identificadorPiloto, other.identificadorPiloto);
	}


	@Override
	public String toString() {
		return "Piloto [identificadorPiloto=" + identificadorPiloto + ", nombrePiloto=" + nombrePiloto + ", puntos="
				+ puntos + ", identificadorEquipo=" + identificadorEquipo + ", pais=" + pais + "]";
	}
	
	
	

}
