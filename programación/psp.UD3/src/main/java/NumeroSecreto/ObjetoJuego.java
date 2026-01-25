package NumeroSecreto;

	public class ObjetoJuego {
	    private int numeroSecreto;
	    private boolean juegoTerminado;
	    private int ganadorId;

	    public ObjetoJuego(int numero) {
	        this.numeroSecreto = numero;
	        this.juegoTerminado = false;
	    }

	    // El método clave que todos los hilos consultan
	    public synchronized int comprobarNumero(int intento, int idHilo) {
	        if (juegoTerminado) {
	            return 1; // 1 significa: El juego ya acabó, perdiste.
	        }
	        
	        if (intento == numeroSecreto) {
	            juegoTerminado = true;
	            ganadorId = idHilo;
	            return 0; // 0 significa: ¡HAS GANADO!
	        } else {
	            return 2; // 2 significa: Sigue intentándolo.
	        }
	    }

	    public boolean isJuegoTerminado() {
	        return juegoTerminado;
	    }
	}


