package Boletin1Modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Conversacion {
	private String indetificador;
	private String pregunta;
	private String respuesta;
	private LocalDate fechaConversacion;
	private TipoAgente tipo;
	private int numValoracionesPositivas;

	public Conversacion() {
		super(); 
	}

	public Conversacion(String pregunta, String respuesta, TipoAgente tipo) {
		super();
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.tipo = tipo;
	}

	public Conversacion(String indetificador, String pregunta, String respuesta, LocalDate fechaConversacion,
			TipoAgente tipo, int numValoracionesPositivas) {
		super();
		this.indetificador = indetificador;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.fechaConversacion = fechaConversacion;
		this.tipo = tipo;
		this.numValoracionesPositivas = numValoracionesPositivas;
	}

	public String getIndetificador() {
		return indetificador;
	}

	public void setIndetificador(String indetificador) {
		this.indetificador = indetificador;
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

	public TipoAgente getTipo() {
		return tipo;
	}

	public void setTipo(TipoAgente tipo) {
		this.tipo = tipo;
	}

	public int getNumValoracionesPositivas() {
		return numValoracionesPositivas;
	}

	public void setNumValoracionesPositivas(int numValoracionesPositivas) {
		this.numValoracionesPositivas = numValoracionesPositivas;
	}

	@Override
	public String toString() {
		return "Conversacion [indetificador=" + indetificador + ", pregunta=" + pregunta + ", respuesta=" + respuesta
				+ ", fechaConversacion=" + fechaConversacion + ", numValoracionesPositivas=" + numValoracionesPositivas
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(indetificador);
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
		return Objects.equals(indetificador, other.indetificador);
	}

	public String calcularIdentificador() {
		String id = this.getFechaConversacion().toString() + Math.random() * 10;
		this.indetificador = id;
		return indetificador;
	}

}
