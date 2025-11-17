package Boletin;

public class Mascota implements Runnable {
	private String nombre;
	private int numVecesCome;
	
	
	public Mascota() {
		super();
	}


	public Mascota(String nombre, int numVecesCome) {
		super();
		this.nombre = nombre;
		this.numVecesCome = numVecesCome;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getNumVecesCome() {
		return numVecesCome;
	}


	public void setNumVecesCome(int numVecesCome) {
		this.numVecesCome = numVecesCome;
	}


	@Override
	public String toString() {
		return "Mascota [nombre=" + nombre + ", numVecesCome=" + numVecesCome + "]";
	}

	public void comer() {
		this.numVecesCome +=1;
	}

	@Override
	public void run() {
		try {
			System.out.println(this.nombre+ " comienza a comer " + Thread.currentThread().getName());
			this.comer();
			
			Thread.sleep(1000); // 1 seg = 1000
			System.out.println("La mascota "+ this.nombre +" ha terminado de comer " + Thread.currentThread().getName());
		} catch  (InterruptedException e){
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated method stub
		
	}
	
	

}
