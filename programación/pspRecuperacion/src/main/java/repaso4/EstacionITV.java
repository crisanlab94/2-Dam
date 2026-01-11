package repaso4;

import java.util.concurrent.Semaphore;

public class EstacionITV {
    private Semaphore boxes;
    private int totalVehiculos;      // Todos los que llegan
    private int inspeccionesExito;   // Solo los que entran al box

    public EstacionITV(int numBoxes) {
        this.boxes = new Semaphore(numBoxes);
        this.totalVehiculos = 0;
        this.inspeccionesExito = 0;
    }

    // Método para los que NO pueden entrar
    public synchronized void registrarVehiculoRechazado(Vehiculo v) {
        totalVehiculos++; // Se cuenta como vehículo que pasó por la estación
        System.out.println("❌ " + v.getId() + " se marcha. Registro total: " + totalVehiculos);
    }

    public boolean intentarEntrar(Vehiculo v) {
        if (boxes.tryAcquire()) {
            System.out.println("✅ " + v.getId() + " ENTRA al box.");
            return true;
        }
        return false;
    }

    // Método para los que terminan la inspección
    public synchronized void salirYRegistrar(Vehiculo v) {
        boxes.release();
        totalVehiculos++;      // Se cuenta en el total
        inspeccionesExito++;   // Se cuenta en éxito
        System.out.println("⬅️ " + v.getId() + " SALE. [Totales: " + totalVehiculos + " | Éxitos: " + inspeccionesExito + "]");
    }
}