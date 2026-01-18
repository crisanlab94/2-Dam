package amazon;

import java.util.concurrent.Semaphore;

public class Centro {
    private TipoVehiculo tipoZona; // Para saber si este objeto Centro es de Camiones o Furgonetas
    private Semaphore sem;
    private int totalAccesosExitosos = 0;
    private int totalRechazados = 0;
    private int totalFraudes = 0;
    
    public Centro(TipoVehiculo tipo, int aforo) {
        this.tipoZona = tipo;
        this.sem = new Semaphore(aforo);
    }
    
    public boolean autenticar(String id_vehiculo, String codigo_carga) {
        return id_vehiculo.equals(codigo_carga);
    }

    // MÉTODO PARA CAMIONES (Espera infinita)
    public void entrarCamion(Vehiculo v) {
        try {
            sem.acquire(); // Se queda "congelado" aquí si está lleno
            registrarAcceso();
            System.out.println(" [ENTRADA CAMIÓN] " + v.getId_vehiculo());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // MÉTODO PARA FURGONETAS (Modelo de rechazo)
    public boolean entrarFurgoneta(Vehiculo v) {
        if (sem.tryAcquire()) {
            registrarAcceso();
            System.out.println(" [ENTRADA FURGONETA] " + v.getId_vehiculo());
            return true;
        } else {
            registrarRechazo();
            System.out.println(" [RECHAZO] " + v.getId_vehiculo() + " se va a otro centro.");
            return false;
        }
    }

    // Métodos sincronizados para los contadores
    public synchronized void registrarAcceso() { 
    	totalAccesosExitosos++; 
    	}
    public synchronized void registrarRechazo() { 
    	totalRechazados++;
    	}
    public synchronized void registrarFraude() { 
    	totalFraudes++; 
    	}

    public void salir(Vehiculo v) {
        sem.release();
        System.out.println(" [SALIDA] " + v.getId_vehiculo());
    }

    public void mostrarResumen() {
        System.out.println("\n--- RESUMEN ZONA " + tipoZona + " ---");
        System.out.println("Cargas exitosas: " + totalAccesosExitosos);
        System.out.println("Desviados (solo furgos): " + totalRechazados);
        System.out.println("Fraudes detectados: " + totalFraudes);
    }
}