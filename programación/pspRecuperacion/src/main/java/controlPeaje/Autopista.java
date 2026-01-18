package controlPeaje;

import java.util.concurrent.Semaphore;



public class Autopista {

	private Semaphore sem;
    private int totalAccesosExitosos = 0;
    private int totalRechazados = 0;
    private int totalFraudes = 0;
    
    public Autopista(int aforo) {
		super();
		this.sem = new Semaphore(aforo);
		
	}
    
    //  Lógica de autenticación
    public boolean autenticar(String id, String codigo) {
        return id.equals(codigo);
    }

    public boolean comprobarAutorizacion(Vehiculo vehiculo) {
        boolean haEntrado = false;
      
        if (sem.tryAcquire()) {
            registrarAcceso(); 
            System.out.println(" [ENTRADA] " + vehiculo.getCodigo() );
            haEntrado = true;
        } else {
        	registrarRechazo();
            System.out.println("[RECHAZO] " + vehiculo.getCodigo() + "(: Desvío a salida Manual)");
        }
        return haEntrado;
    }

   
    private synchronized void registrarAcceso() {
        totalAccesosExitosos++;
    }
    private synchronized void registrarRechazo() {
    	totalRechazados++;
    }
    
    public synchronized void registrarFraude() {
    	totalFraudes++;
    }

    public void salir(Vehiculo vehiculo) {
        sem.release();
        System.out.println(" [SALIDA] " + vehiculo.getCodigo());
    }

    public void mostrarResumen() {
        System.out.println("--- RESUMEN  ---");
        System.out.println("Total de accesos completados(pagados): " + totalAccesosExitosos);
        System.out.println(("Total de rechazos(desviados por saturación): "+ totalRechazados));
        System.out.println("Total de fraudes: " + totalFraudes);
    }

}
