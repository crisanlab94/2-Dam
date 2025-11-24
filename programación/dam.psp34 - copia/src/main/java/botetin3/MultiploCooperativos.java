package botetin3;

public class MultiploCooperativos  {
	

	//si lo pongo estatico y sincronizado entra de uno en uno, por lo que primero sacaria los multiplos de 2, luego de 3...
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

}
