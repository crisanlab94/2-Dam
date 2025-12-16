package modelo;

import java.time.LocalDate;

public class SandovalCristinaPartida {
	private int id;
    private SandovalCristinaJugador narrador;
    private int torneo_id;
    private LocalDate fecha;
    private Resultado resultado;
    
	public SandovalCristinaPartida() {
		super();
	}
	
	public SandovalCristinaPartida(int id, SandovalCristinaJugador narrador, int torneo_id, LocalDate fecha,
			Resultado resultado) {
		super();
		this.id = id;
		this.narrador = narrador;
		this.torneo_id = torneo_id;
		this.fecha = fecha;
		this.resultado = resultado;
	}


	
	
	public SandovalCristinaPartida(int id, SandovalCristinaJugador narrador, LocalDate fecha, Resultado resultado) {
		super();
		this.id = id;
		this.narrador = narrador;
		this.fecha = fecha;
		this.resultado = resultado;
	}

	public SandovalCristinaPartida(SandovalCristinaJugador narrador, int torneo_id, LocalDate fecha,
			Resultado resultado) {
		super();
		this.narrador = narrador;
		this.torneo_id = torneo_id;
		this.fecha = fecha;
		this.resultado = resultado;
	}

	public int getTorneo_id() {
		return torneo_id;
	}

	public void setTorneo_id(int torneo_id) {
		this.torneo_id = torneo_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SandovalCristinaJugador getNarrador() {
		return narrador;
	}

	public void setNarrador(SandovalCristinaJugador narrador) {
		this.narrador = narrador;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "SandovalCristinaPartida [id=" + id + ", narrador=" + narrador + ", fecha=" + fecha + ", resultado="
				+ resultado + "]";
	}
    
    

}
