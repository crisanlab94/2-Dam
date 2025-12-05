package boletinRestauranteMenu;


import java.util.concurrent.Semaphore;



public class GestionaMenuCompartido {
	public static void main(String[] args) {
		//Empiezan en 0, porque el cocinero no cocina hasta que no hay clientes
		//Hasta que no hay un plato no se come
		Semaphore hayClientes = new Semaphore(0);
        Semaphore hayPlato = new Semaphore(0);
        
        //Solo hay un cocinero
        Cocinero cocinero = new Cocinero("Cocinero 1", hayClientes, hayPlato);
        //Me creo el hilo cocicnero
        Thread hiloCocinero = new Thread(cocinero);
        //Lo inicio
        hiloCocinero.start();
        
        //Clientes
        for (int i = 1; i <= 4; i++) {
            Cliente cliente = new Cliente("Cliente " + i, hayClientes, hayPlato);
            //Me creo hilo clientes
            Thread hiloCliente = new Thread(cliente);
            //Lo inicio
           hiloCliente.start();
        }
       
	}
 }

