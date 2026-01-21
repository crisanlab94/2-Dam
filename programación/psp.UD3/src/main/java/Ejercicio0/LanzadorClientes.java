package Ejercicio0;



public class LanzadorClientes {
	public static void main(String[] args) {
		 
		ClienteHilo cliente = new ClienteHilo();
		
        for (int i = 1; i <= 10; i++) {
            Thread hiloCliente = new Thread(cliente);
            hiloCliente.start();
        }
		
	}

}
