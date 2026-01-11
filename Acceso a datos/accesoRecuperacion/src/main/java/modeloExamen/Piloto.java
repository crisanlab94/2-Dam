package modeloExamen;

public class Piloto {
	private String identificadorPiloto;
	private String nombrePiloto;
	private int puntos;
	private String identificadorEquipo;
	private String pais;
	
	

	public Piloto(String identificadorPiloto, String nombrePiloto, int puntos, String identificadorEquipo,
			String pais) {
		super();
		this.identificadorPiloto = identificadorPiloto;
		this.nombrePiloto = nombrePiloto;
		this.puntos = puntos;
		this.identificadorEquipo = identificadorEquipo;
		this.pais = pais;
	}

	public String getIdentificadorPiloto() {
		return identificadorPiloto;
	}

	public void setIdentificadorPiloto(String identificadorPiloto) {
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

	
	
	public String getIdentificadorEquipo() {
		return identificadorEquipo;
	}

	public void setIdentificadorEquipo(String identificadorEquipo) {
		this.identificadorEquipo = identificadorEquipo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Piloto [identificadorPiloto=" + identificadorPiloto + ", nombrePiloto=" + nombrePiloto + ", puntos="
				+ puntos + ", identificadorEquipo=" + identificadorEquipo + ", pais=" + pais + "]";
	}

	
	
	

}
