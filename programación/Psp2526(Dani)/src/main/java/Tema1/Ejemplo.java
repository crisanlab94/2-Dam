package Tema1;

import java.io.IOException;

public class Ejemplo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runtime kernel = Runtime.getRuntime();
		System.out.println(kernel.freeMemory());
		System.out.println();
		System.out.println(kernel.maxMemory());
		System.out.println();
 
		System.out.println(kernel.totalMemory());

		// abrir un archivo notepadd
		String[] argumentos = { "notepad", "C:\\adios2.txt" };
		String[] notepad = { "notepad" };
		String[] google = { "C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe", "youtube.com" };
		try {
			Process proceso = kernel.exec(google);
			try {
				int codigoRetorno = proceso.waitFor();
				System.out.println("eer");
				System.out.println("Después del chrome" + codigoRetorno);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
