package biblioteca;

import java.util.concurrent.Semaphore;



public class SalaEstudio {
	private Semaphore sem;
    private int totalAccesosExitosos = 0;
    private int totalRechazados = 0;
    
	public SalaEstudio(int aforo) {
		super();
		this.sem = new Semaphore(aforo);
		
	}
    
	
	  //  Lógica de autenticación
    public boolean autenticar(String user, String pass) {
        // Ejemplo simple: login debe ser igual al password
        return user.equals(pass);
    }

    public boolean intentarIniciarSesion(Estudiante estudiante) {
        boolean haEntrado = false;
      
        if (sem.tryAcquire()) {
            registrarAcceso(); 
            System.out.println("[" + estudiante.getHoraActual() + "] [ENTRADA] " + estudiante.getCarnetId() );
            haEntrado = true;
        } else {
        	registrarRechazo();
            System.out.println("[" + estudiante.getHoraActual() + "] [RECHAZO] " + estudiante.getCarnetId() + "(Lleno)");
        }
        return haEntrado;
    }

   
    private synchronized void registrarAcceso() {
        totalAccesosExitosos++;
    }
    private synchronized void registrarRechazo() {
    	totalRechazados++;
    }

    public void salir(Estudiante estudiante) {
        sem.release();
        System.out.println("[" + estudiante.getHoraActual() + "] [SALIDA] " + estudiante.getCarnetId());
    }

    public void mostrarResumen() {
        System.out.println("--- RESUMEN  ---");
        System.out.println("Total de accesos completados: " + totalAccesosExitosos);
        System.out.println(("Total de rechazos: "+ totalRechazados));
    }

    

}
