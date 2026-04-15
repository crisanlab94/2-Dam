package modelo;

import java.sql.Date;

public class Reproduccion {
	  private int id;
	    private Usuario usuario; // Referencia al objeto Usuario (FK en BD) 
	    private Date fecha;
	    private TipoContenido tipo;
	    private int minutosVistos;

	    public Reproduccion() {}

	    public Reproduccion(int id, Usuario usuario, Date fecha, TipoContenido tipo, int minutosVistos) {
	        this.id = id;
	        this.usuario = usuario;
	        this.fecha = fecha;
	        this.tipo = tipo;
	        this.minutosVistos = minutosVistos;
	    }

	    // GETTERS Y SETTERS (Un solo return)
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public Usuario getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(Usuario usuario) {
	        this.usuario = usuario;
	    }

	    public Date getFecha() {
	        return fecha;
	    }

	    public void setFecha(Date fecha) {
	        this.fecha = fecha;
	    }

	    public TipoContenido getTipo() {
	        return tipo;
	    }

	    public void setTipo(TipoContenido tipo) {
	        this.tipo = tipo;
	    }

	    public int getMinutosVistos() {
	        return minutosVistos;
	    }

	    public void setMinutosVistos(int minutosVistos) {
	        this.minutosVistos = minutosVistos;
	    }

	    @Override
	    public String toString() {
	        String info = "Reproducción [" + tipo + "] Fecha: " + fecha + " | Duración: " + minutosVistos + " min.";
	        return info;
	    }

}

	
