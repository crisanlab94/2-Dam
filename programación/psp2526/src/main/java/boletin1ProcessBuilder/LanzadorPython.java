package boletin1ProcessBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanzadorPython {
	public static void main(String[] args) {
		//Donde esta el fichero(ruta+nombreFichero)
        String nombreFichero = "src\\main\\resources\\fichero.py";
        LanzadorPython lanzador = new LanzadorPython();
        lanzador.ejecutarPython(nombreFichero);	
	}
	
	 public void ejecutarPython(String rutaFichero) {
	        // Comando para ejecutar python con el archivo como argumento
	        String[] comando = {"python", rutaFichero};
	       
	        try {
	        	ProcessBuilder pb = new ProcessBuilder(comando);
	        	Process proceso = pb.start();
	        	 //Con BufferedReader Leer la salida del proceso línea a línea
	        	//Con proceso.getInputStream() puedes leer desde Java
	        	//InputStream is = proceso.getInputStream();

	            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
	            //Leer linea a linea
	            String linea = null;
	            while ((linea = lector.readLine()) != null) {
	                System.out.println(linea);
	            }
	            int codigoSalida = proceso.waitFor();
	            System.out.println("Proceso finalizado con código: " + codigoSalida);

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
