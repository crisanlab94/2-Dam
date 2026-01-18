package biblioteca;

import java.time.LocalDateTime;

public class Estudiante implements Runnable {
	private String carnetId;
	private String clave;
    private String nombre;
    private SalaEstudio salaEstudio;
    
	public Estudiante(String carnetId, String clave, String nombre, SalaEstudio salaEstudio) {
		super();
		this.carnetId = carnetId;
		this.clave = clave;
		this.nombre = nombre;
		this.salaEstudio = salaEstudio;
	}
    
	  public String getHoraActual() {
	        LocalDateTime locaDate = LocalDateTime.now();
	        int horas = locaDate.getHour();
	        int minutos = locaDate.getMinute();
	        int segundos = locaDate.getSecond();
	        
	        return horas + ":" + minutos + ":" + segundos;
	    }
	    
	    @Override
	    public void run() {
	        
	        if (salaEstudio.autenticar(this.carnetId, this.clave)) {
	            
	          
	            if (salaEstudio.intentarIniciarSesion(this)) {
	                try {
	                    Thread.sleep(3000); 
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                } finally {
	                    salaEstudio.salir(this);
	                }
	            }
	           

	        } else {
	            // Si el login falla, el hilo termina aquí (Fallo de autenticación)
	            System.out.println("[SEGURIDAD] " + carnetId + " bloqueado: Credenciales incorrectas.");
	        }
	    }

		public String getCarnetId() {
			return carnetId;
		}

		public void setCarnetId(String carnetId) {
			this.carnetId = carnetId;
		}

		public String getClave() {
			return clave;
		}

		public void setClave(String clave) {
			this.clave = clave;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public SalaEstudio getSalaEstudio() {
			return salaEstudio;
		}

		public void setSalaEstudio(SalaEstudio salaEstudio) {
			this.salaEstudio = salaEstudio;
		}
    

}
