package accesoADatos.repaso1.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Conversacion {

	private String identificador;
	private String pregunta;
	private String respuesta;
	private LocalDate fechaConversacion;
	private int numValoracionesPositivas;
	private TipoAgente tipo;
	
	
	
	public Conversacion() {
		super();
	}


	public Conversacion(String pregunta, String respuesta, TipoAgente tipo) {
		super();
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.tipo = tipo;
	}




	public Conversacion(String respuesta, LocalDate fechaConversacion, TipoAgente tipo) {
		super();
		this.respuesta = respuesta;
		this.fechaConversacion = fechaConversacion;
		this.tipo = tipo;
	}


	public String getIdentificador() {
		return identificador;
	}


	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}


	public String getPregunta() {
		return pregunta;
	}


	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	public LocalDate getFechaConversacion() {
		return fechaConversacion;
	}


	public void setFechaConversacion(LocalDate fechaConversacion) {
		this.fechaConversacion = fechaConversacion;
	}


	public int getNumValoracionesPositivas() {
		return numValoracionesPositivas;
	}


	public void setNumValoracionesPositivas(int numValoracionesPositivas) {
		this.numValoracionesPositivas = numValoracionesPositivas;
	}


	


	public TipoAgente getTipo() {
		return tipo;
	}


	public void setTipo(TipoAgente tipo) {
		this.tipo = tipo;
	}


	@Override
	public int hashCode() {
		return Objects.hash(fechaConversacion, identificador, numValoracionesPositivas, pregunta, respuesta);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conversacion other = (Conversacion) obj;
		return Objects.equals(fechaConversacion, other.fechaConversacion)
				&& Objects.equals(identificador, other.identificador)
				&& numValoracionesPositivas == other.numValoracionesPositivas
				&& Objects.equals(pregunta, other.pregunta) && Objects.equals(respuesta, other.respuesta);
	}


	@Override
	public String toString() {
		return "Conversacion [identificador=" + identificador + ", pregunta=" + pregunta + ", respuesta=" + respuesta
				+ ", fechaConversacion=" + fechaConversacion + ", numValoracionesPositivas=" + numValoracionesPositivas
				+ "]";
	}
	
	private String calculaIdentificador() {
		String id = this.getFechaConversacion().toString() + Math.random()*10;
		this.identificador=id;
		return identificador;
		
	}
	
}
