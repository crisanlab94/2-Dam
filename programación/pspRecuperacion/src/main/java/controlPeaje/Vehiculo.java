package controlPeaje;



public class Vehiculo implements Runnable {
	
	private String id;
	private String codigo;
    private Autopista autopista;
    
	public Vehiculo(String id, String codigo, Autopista autopista) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.autopista = autopista;
	}
    
	  @Override
	    public void run() {
	        
	        if (autopista.autenticar(this.id, this.codigo)) {
	            
	          
	            if (autopista.comprobarAutorizacion(this)) {
	                try {
	                    Thread.sleep(2500); 
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                } finally {
	                    autopista.salir(this);
	                }
	            }
	           

	        } else {
	            // Si el login falla, el hilo termina aquí (Fallo de autenticación)
	            autopista.registrarFraude();
	        	System.out.println("[SEGURIDAD] " + id + " bloqueado: Credenciales incorrectas.");
	        }
	    }

	  public String getId() {
		  return id;
	  }

	  public void setId(String id) {
		  this.id = id;
	  }

	  public String getCodigo() {
		  return codigo;
	  }

	  public void setCodigo(String codigo) {
		  this.codigo = codigo;
	  }

	  public Autopista getAutopista() {
		  return autopista;
	  }

	  public void setAutopista(Autopista autopista) {
		  this.autopista = autopista;
	  }
	  
	  

}
