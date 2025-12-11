package boletinRestauranteMenu;


import java.util.concurrent.Semaphore;



public class GestionaMenuCompartido {
	public static void main(String[] args) {
		
		//Un cliente 3 platos
		Semaphore hayClientes = new Semaphore(1);
        Semaphore hayPlato = new Semaphore(3);
        
        try {
        	//Aqui inicio el turno en 0
        	//Tanto de clientes como de cocinero
        	//Porque un cocinero no cocina hasta que no llega un cliente
			hayClientes.acquire();
			hayPlato.acquire(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //Solo hay un cocinero
        Cocinero cocinero = new Cocinero("Cocinero 1", hayClientes, hayPlato);
        //Me creo el hilo cocicnero
        Thread hiloCocinero = new Thread(cocinero);
        //Lo inicio
        hiloCocinero.start();
        
        //Clientes
        for (int i = 1; i <= 7; i++) {
            Cliente cliente = new Cliente("Cliente " + i, hayClientes, hayPlato);
            //Me creo hilo clientes
            Thread hiloCliente = new Thread(cliente);
            //Lo inicio
           hiloCliente.start();
        }
       
	}
 }

