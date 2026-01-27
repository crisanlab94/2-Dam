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
	        int resultado = 2; // Por defecto: Sigue intentándolo.

	        if (juegoTerminado) {
	            resultado = 1; // El juego ya acabó.
	        } else if (intento == numeroSecreto) {
	            juegoTerminado = true;
	            ganadorId = idHilo;
	            resultado = 0; // ¡HAS GANADO!
	        }

	        return resultado;
	    }
	    public boolean isJuegoTerminado() {
	        return juegoTerminado;
	    }
	}


