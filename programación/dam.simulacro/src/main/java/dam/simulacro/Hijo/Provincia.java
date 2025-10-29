package dam.simulacro.Hijo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Provincia {
	private static final String rutaResources = "src/main/resources/";
	
	
	public static void main(String[] args) {
		
		
		String nombreProvincia = args[0];
		Provincia p = new Provincia();
		try {
			 // Analiza el fichero y obtiene las líneas de la provincia
			String contenidoProvincia=p.analizaProvincia(rutaResources + "pedidos.txt",nombreProvincia);
			   
			// Escribe el contenido en un fichero con el nombre de la provincia
            p.escribirProvincia(contenidoProvincia, rutaResources + nombreProvincia + ".txt");
            
            System.out.println(p.devuelveNumProvincia(rutaResources + nombreProvincia +".txt"));
 
          
          // apartados anteriores
            //System.out.println("Fichero generado: " + nombreProvincia + ".txt");
            
        } catch (IOException e) {
        	System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }
	
	// Método que filtra las líneas por provincia usando FileReader y Scanner
	public String analizaProvincia(String rutaFichero, String nombreProvincia) throws FileNotFoundException {
		File archivo = new File(rutaFichero);
		FileReader fichero = new FileReader(archivo);
		Scanner in = new Scanner(fichero);
		   String contenido = "";
		   
		while (in.hasNextLine()) {
			String lineas = in.nextLine();
			String [] atributos = lineas.split("#");
			String nombreArticulo =atributos[0];
			String numUnidades =atributos[1];
			String importe =atributos[2];
			String numReferencia =atributos[3];
			String direccion =atributos[4];
			String nomProvincia =atributos[5];
			String fechaPedido =atributos[6];
			
			
			if (nomProvincia.trim().equalsIgnoreCase(nombreProvincia.trim())) {
			    contenido += lineas + "\n";
			}

		}
		in.close();
		try {
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contenido;
	}

	public void escribirProvincia(String contenido, String rutaFichero) {
		FileWriter ficheroSalida = null;
		try {
			ficheroSalida = new FileWriter(rutaFichero);
			String encabezado ="Artículo\tNumUnidades\tImporte\tNumRerefencia\tDirección\tProvincia\tFechaPedido\n";
			ficheroSalida.write(encabezado);
			ficheroSalida.write(contenido);

		} catch (IOException e) {
			System.out.println("IOException");
		} finally {
			if (ficheroSalida != null)
				try {
					ficheroSalida.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
	public int devuelveNumProvincia(String ruta) {

		int provincia =0;

	    File archivo = new File(ruta);

	    try (FileReader fichero = new FileReader(archivo);

	         Scanner in = new Scanner(fichero)) {

	        while (in.hasNextLine()) {

	            String lineas = in.nextLine();

	            String[] atributos = lineas.split("#");

	            if (atributos.length > 5) {

	                provincia++;   

	            }

	        }

	    } catch (IOException e) {

	        e.printStackTrace();

	    }

		return provincia;

	}
	
	//public int devuelveTotalPedidos(String ruta) {
		
	//}

}
