package CloudDevConTryAcquire;

import java.util.concurrent.Semaphore;

public class EntornoCloud {
    private Tipo tipo;
    private Semaphore sem;
    // Monitor para integridad de datos (Apartado 2: Resumen)
    private int totalAccesosExitosos = 0;

    public EntornoCloud(Tipo tipo, int aforo) {
        this.tipo = tipo;
        this.sem = new Semaphore(aforo);
    }

    // Apartado 2: Lógica de autenticación
    public boolean autenticar(String user, String pass) {
        // Ejemplo simple: login debe ser igual al password
        return user.equals(pass);
    }

    public boolean intentarIniciarSesion(Desarrollador ds) {
        boolean haEntrado = false;
        // Aforo con rechazo (Modelo 2)
        if (sem.tryAcquire()) {
            registrarAcceso(); // Monitor para el resumen
            System.out.println("[" + ds.getHoraActual() + "] [ENTRADA] " + ds.getId() + " en " + tipo);
            haEntrado = true;
        } else {
            System.out.println("[" + ds.getHoraActual() + "] [RECHAZO] " + ds.getId() + " en " + tipo + " (Lleno)");
        }
        return haEntrado;
    }

    // Monitor (synchronized) para asegurar integridad del contador de resumen [cite: 857, 869]
    private synchronized void registrarAcceso() {
        totalAccesosExitosos++;
    }

    public void salir(Desarrollador ds) {
        sem.release();
        System.out.println("[" + ds.getHoraActual() + "] [SALIDA] " + ds.getId() + " de " + tipo);
    }

    public void mostrarResumen() {
        System.out.println("--- RESUMEN ENTORNO " + tipo + " ---");
        System.out.println("Total de accesos completados: " + totalAccesosExitosos);
    }

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Semaphore getSem() {
		return sem;
	}

	public void setSem(Semaphore sem) {
		this.sem = sem;
	}

	public int getTotalAccesosExitosos() {
		return totalAccesosExitosos;
	}

	public void setTotalAccesosExitosos(int totalAccesosExitosos) {
		this.totalAccesosExitosos = totalAccesosExitosos;
	}
    
    
    
}