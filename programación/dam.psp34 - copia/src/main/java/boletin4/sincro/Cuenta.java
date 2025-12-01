package boletin4.sincro;

public class Cuenta {
	private int saldo;

	public Cuenta(int saldo) {
		super();
		this.saldo = saldo;
	}

	public int getSaldo() {
		return saldo;
	}


	
	public synchronized void retiraDinero(int importe) throws CuentaException {
		System.out.println("Inicio Retirando: " + importe+"," + " saldo final: "+ this.saldo);
		int saldoInicial=this.saldo;
		//System.out.println("Saldo inicial: "+ saldoInicial);
		
		int saldoFinal= saldoInicial-importe;
		if(saldoInicial < importe) {
			throw new CuentaException("El saldo es menor al importe");
		}else {
			this.saldo= saldoFinal;
			System.out.println("Fin Retirando, " + " saldo final: "+ this.saldo);
			//System.out.println("Saldo actual: " + saldoFinal);
			
		}
	
	}
	
	
	
	public synchronized void ingresaDinero(int importe) {
		System.out.println("Inicio Ingresando: " + importe+"," + " saldo final: "+ this.saldo);
		int saldoInicial = this.saldo;
		//System.out.println("Saldo inicial:" + saldoInicial);
		//System.out.println("Ingresando "+ importe);
		
		int saldoFinal= saldoInicial + importe; 
		this.saldo= saldoFinal;
		
		System.out.println("Fin Ingresando, " + " saldo final: "+ this.saldo);
		
			
		}

}
