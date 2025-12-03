package BoletinSemaforos;

public class SemaforoCoche {
	private int surtidor;

	public SemaforoCoche(int surtidor) {
		super();
		this.surtidor = surtidor;
	}

	public synchronized void adquirir() throws InterruptedException {
		while (surtidor == 0) {
			wait();
		} 
		surtidor--;
	}

	public synchronized void liberar() {
		surtidor++;
		notifyAll();
	}
}