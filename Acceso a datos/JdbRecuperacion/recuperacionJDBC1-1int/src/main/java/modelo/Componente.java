package modelo;

public class Componente {
    private double pesoGramos;
    private String material;
    private boolean gpsActivo;

    public Componente() {}
    public Componente(double peso, String mat, boolean gps) {
        this.pesoGramos = peso;
        this.material = mat;
        this.gpsActivo = gps;
    }
    // Getters y Setters
    public double getPesoGramos() { return pesoGramos; }
    public void setPesoGramos(double p) { this.pesoGramos = p; }
    public String getMaterial() { return material; }
    public void setMaterial(String m) { this.material = m; }
    public boolean isGpsActivo() { return gpsActivo; }
    public void setGpsActivo(boolean g) { this.gpsActivo = g; }

    @Override
    public String toString() {
        return "Peso: " + pesoGramos + "g | Mat: " + material + " | GPS: " + gpsActivo;
    }
}