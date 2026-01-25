package Ejercicio0;



public class LanzadorClientes {
	public static void main(String[] args) {
		System.out.println("Iniciando lanzamiento de 10 clientes...");
		
		
        for (int i = 1; i <= 10; i++) {
        	ClienteHilo cliente = new ClienteHilo();
        	cliente.setName("Cliente-" + i);
            cliente.start();
        }
		
	}

}
