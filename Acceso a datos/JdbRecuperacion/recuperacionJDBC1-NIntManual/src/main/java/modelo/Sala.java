package modelo;

public class Sala {
    private int id;
    private String nombre;
    private TipoAmbiente tipo;
    private int capacidad;

    public Sala() {}
    public Sala(int id, String nom, TipoAmbiente tipo, int cap) {
        this.id = id; this.nombre = nom; this.tipo = tipo; this.capacidad = cap;
    }
    // Getters y Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public TipoAmbiente getTipo() { return tipo; }
    public void setTipo(TipoAmbiente t) { this.tipo = t; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int c) { this.capacidad = c; }
    
	@Override
	public String toString() {
		return "Sala [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", capacidad=" + capacidad + "]";
	}
    
    
}
