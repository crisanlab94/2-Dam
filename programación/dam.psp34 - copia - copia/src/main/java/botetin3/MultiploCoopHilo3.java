package botetin3;

public class MultiploCoopHilo3  extends Thread{
	private MultiploCooperativos multiplo;
	
	

	public MultiploCoopHilo3(MultiploCooperativos multiplo) {
		super();
		this.multiplo = multiplo;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		multiplo.multiplicar(3);
	}
	

}
