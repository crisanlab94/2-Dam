package boletin4.sincro;

public class HiloIngresa extends Thread {
	private int importe;
	private Cuenta cuenta;



	public HiloIngresa(Cuenta cuenta) {
		super();
		this.importe = ((int)(Math.random()*500+1));
		this.cuenta = cuenta;
	}

	public int getImporte() {
		return importe;
	}


	

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	


	@Override
	public  void  run() {
		cuenta.ingresaDinero(importe);
	}
	
	

}
