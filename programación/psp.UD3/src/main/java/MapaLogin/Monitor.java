package MapaLogin;
import java.util.HashMap;
import java.util.Map;


public class Monitor {


	    private Map<String, Integer> intentosUsuarios;
	    private final int MAX_INTENTOS = 3;

	    public Monitor() {
	        this.intentosUsuarios = new HashMap<>();
	    }

	    // Método para registrar un fallo
	    public synchronized int registrarFallo(String usuario) {
	        int fallos = intentosUsuarios.getOrDefault(usuario, 0) + 1;
	        intentosUsuarios.put(usuario, fallos);
	        return fallos;
	    }

	    // Método para resetear fallos si acierta la contraseña
	    public synchronized void resetearIntentos(String usuario) {
	        intentosUsuarios.put(usuario, 0);
	    }

	    // Método para verificar si está bloqueado
	    public synchronized boolean estaBloqueado(String usuario) {
	        return intentosUsuarios.getOrDefault(usuario, 0) >= MAX_INTENTOS;
	    }
	}


