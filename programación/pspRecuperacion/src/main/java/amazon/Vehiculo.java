package amazon;

public class Vehiculo implements Runnable {
    private String id_vehiculo;
    private String codigo_carga;
    private Centro centro;
    private TipoVehiculo tipo; 

    public Vehiculo(String id, String codigo, Centro centro, TipoVehiculo tipo) {
        this.id_vehiculo = id;
        this.codigo_carga = codigo;
        this.centro = centro;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        // 1. Seguridad
        if (centro.autenticar(id_vehiculo, codigo_carga)) {
            
            // 2. Lógica según tipo
            if (this.tipo == TipoVehiculo.CAMION) {
                // El camión entra sí o sí (esperando)
                centro.entrarCamion(this);
                trabajarYSalir();
            } else {
                // La furgoneta intenta entrar (rebota si no hay sitio)
                if (centro.entrarFurgoneta(this)) {
                    trabajarYSalir();
                }
            }
        } else {
            centro.registrarFraude();
            System.out.println("[ALERTA] Fraude detectado: " + id_vehiculo);
        }
    }

    private void trabajarYSalir() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            centro.salir(this);
        }
    }

	public String getId_vehiculo() {
		return id_vehiculo;
	}

	public void setId_vehiculo(String id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}

	public String getCodigo_carga() {
		return codigo_carga;
	}

	public void setCodigo_carga(String codigo_carga) {
		this.codigo_carga = codigo_carga;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

    
}