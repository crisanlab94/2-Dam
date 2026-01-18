package tallerReparaciones;


	public class Vehiculo implements Runnable {
	    private String matricula;
	    private String token;
	    private String tipo; // "RAPIDO" o "COMPLETO"
	    private Taller taller;

	    public Vehiculo(String matricula, String token, String tipo, Taller taller) {
	        this.matricula = matricula;
	        this.token = token;
	        this.tipo = tipo;
	        this.taller = taller;
	    }

	    @Override
	    public void run() {
	        // 1. Validar Seguridad
	        if (taller.validarAcceso(matricula, token)) {
	            
	            try {
	                // LOGICA SEGÚN TIPO
	                if (tipo.equals("RAPIDO")) {
	                    usarElevador();
	                } else {
	                    // SERVICIO COMPLETO
	                    usarElevador();
	                    usarPintura();
	                }
	                
	                taller.registrarExito();
	                
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	        } else {
	            taller.registrarFallo();
	            System.err.println("[SEGURIDAD] " + matricula + " bloqueado. Token incorrecto.");
	        }
	    }

	    private void usarElevador() throws InterruptedException {
	        taller.getSemElevadores().acquire(); // BLOQUEO: Espera si no hay hueco
	        System.out.println("[ELEVADOR] -> " + matricula + " entrando.");
	        Thread.sleep(2000);
	        taller.getSemElevadores().release();
	        System.out.println("[ELEVADOR] <- " + matricula + " saliendo.");
	    }

	    private void usarPintura() throws InterruptedException {
	        taller.getSemPintura().acquire(); // BLOQUEO: Espera si está ocupado
	        System.out.println("[PINTURA]  -> " + matricula + " entrando.");
	        Thread.sleep(2000);
	        taller.getSemPintura().release();
	        System.out.println("[PINTURA]  <- " + matricula + " saliendo.");
	    }

	    public String getMatricula() { return matricula; }
	}


