package tallerReparaciones;

import java.util.concurrent.Semaphore;

public class Taller {

	    private Semaphore semElevadores;
	    private Semaphore semPintura;
	    
	    // Contadores para el monitor
	    private int reparacionesOK = 0;
	    private int fallosSeguridad = 0;

	    // El constructor recibe los semáforos creados en el Gestiona
	    public Taller(Semaphore semElevadores, Semaphore semPintura) {
	        this.semElevadores = semElevadores;
	        this.semPintura = semPintura;
	    }

	    public boolean validarAcceso(String matricula, String token) {
	        return matricula.equals(token);
	    }

	    // Métodos Sincronizados para estadísticas
	    public synchronized void registrarExito() { reparacionesOK++; }
	    public synchronized void registrarFallo() { fallosSeguridad++; }

	    // Getters para que los hilos usen los semáforos
	    public Semaphore getSemElevadores() { return semElevadores; }
	    public Semaphore getSemPintura() { return semPintura; }

	    public void mostrarResumen() {
	        System.out.println("\n--- ESTADÍSTICAS TALLER SPEEDY ---");
	        System.out.println("Vehículos reparados con éxito: " + reparacionesOK);
	        System.out.println("Vehículos rechazados por seguridad: " + fallosSeguridad);
	    }
	}

