package repaso.accesoADatos.modelo;

import java.util.List;
import java.util.Objects;

public class Receta {
	private String titulo;
    private List<Ingrediente> ingredientes;
    private String procedimiento;
    private String tiempo;
    
	public Receta(String titulo, List<Ingrediente> ingredientes, String procedimiento, String tiempo) {
		super();
		this.titulo = titulo;
		this.ingredientes = ingredientes;
		this.procedimiento = procedimiento;
		this.tiempo = tiempo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}


	@Override
	public String toString() {
		return "Receta [titulo=" + titulo + ", procedimiento=" + procedimiento + ", tiempo=" + tiempo + "]";
	}
    
    

}
