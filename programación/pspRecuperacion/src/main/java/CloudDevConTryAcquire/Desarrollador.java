package CloudDevConTryAcquire;

import java.time.LocalDateTime;

public class Desarrollador implements Runnable {
	private String id;
	private String login;
    private String password;
    private EntornoCloud entorno;

    
    public Desarrollador(String id, String login, String password, EntornoCloud entorno) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.entorno = entorno;
	}

    /*
	@Override
    public void run() {
        // Intentamos entrar. El tryAcquire ocurre dentro de este método.
        if (entorno.intentarIniciarSesion(this)) {
            try {
                // Si pudo entrar, simula el tiempo de trabajo
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // IMPORTANTE: Solo liberamos la plaza si realmente entramos
                entorno.salir(this);
            }
        } 
        // Si el 'if' es false, el hilo simplemente llega al final y muere (Rechazo)
    }
*/
    
    public String getHoraActual() {
        LocalDateTime locaDate = LocalDateTime.now();
        int horas = locaDate.getHour();
        int minutos = locaDate.getMinute();
        int segundos = locaDate.getSecond();
        
        return horas + ":" + minutos + ":" + segundos;
    }
    
    @Override
    public void run() {
        // CAPA 2: Primero validamos quién es (Apartado 2)
        if (entorno.autenticar(this.login, this.password)) {
            
            // CAPA 1: Si es quien dice ser, intentamos entrar al entorno (Apartado 1)
            if (entorno.intentarIniciarSesion(this)) {
                try {
                    Thread.sleep(2000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    entorno.salir(this);
                }
            }
            // Si no hay sitio, el hilo termina aquí (Rechazo de aforo)

        } else {
            // Si el login falla, el hilo termina aquí (Fallo de autenticación)
            System.out.println("[SEGURIDAD] " + id + " bloqueado: Credenciales incorrectas.");
        }
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EntornoCloud getEntorno() {
		return entorno;
	}

	public void setEntorno(EntornoCloud entorno) {
		this.entorno = entorno;
	}
   

}
