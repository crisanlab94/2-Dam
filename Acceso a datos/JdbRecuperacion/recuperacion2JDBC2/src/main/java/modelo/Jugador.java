package modelo;



public class Jugador {
    private int id; // ID numérico 
    private String nombre;
    private String email;
    private int eloPuntos; // Representa los puntos totales 

    // Constructor por defecto
    public Jugador() {}

    // Constructor completo
    public Jugador(int id, String nombre, String email, int eloPuntos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.eloPuntos = eloPuntos;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getEloPuntos() { return eloPuntos; }
    public void setEloPuntos(int eloPuntos) { this.eloPuntos = eloPuntos; }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Nombre: %-20s | ELO: %d", id, nombre, eloPuntos);
    }
}
