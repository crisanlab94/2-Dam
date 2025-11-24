package botetin3;

public class MultiploCoopHilo7  extends Thread{
	private MultiploCooperativos multiplo;
	
	

	public MultiploCoopHilo7(MultiploCooperativos multiplo) {
		super();
		this.multiplo = multiplo;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		multiplo.multiplicar(7);
	}
	

}
