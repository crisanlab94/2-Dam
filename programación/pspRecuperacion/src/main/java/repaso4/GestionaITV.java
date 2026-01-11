package repaso4;

public class GestionaITV {
    public static void main(String[] args) {
        EstacionITV estacion = new EstacionITV(3); // 3 boxes

        for (int i = 1; i <= 12; i++) {
            new Thread(new Vehiculo("Vehiculo-" + i, estacion)).start();
        }
    }
}