package botetin3;

public class Multiplo implements Runnable {
	private int numero;

	
	
	public Multiplo(int numero) {
		super();
		this.numero = numero;
	}

	@Override
	public void run() {
	
			multiplicar(this.numero);
	}

	void multiplicar(int numero) {
		
		for ( int i=0; i < 11 ; i++) {
			System.out.println(i * numero);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
