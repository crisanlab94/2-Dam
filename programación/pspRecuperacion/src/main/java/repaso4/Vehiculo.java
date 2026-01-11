package repaso4;

public class Vehiculo implements Runnable {
    private String id;
    private EstacionITV estacion;

    public Vehiculo(String id, EstacionITV estacion) {
        this.id = id;
        this.estacion = estacion;
    }

    @Override
    public void run() {
        if (estacion.intentarEntrar(this)) {
            try {
                Thread.sleep((long) (Math.random() * 2000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                estacion.salirYRegistrar(this);
            }
        } else {
            // REQUISITO CLAVE: Aunque no entre, debe registrarse en el libro
            estacion.registrarVehiculoRechazado(this);
        }
    }
    public String getId() { return id; }
}