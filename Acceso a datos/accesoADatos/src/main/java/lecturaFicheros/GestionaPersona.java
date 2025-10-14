package lecturaFicheros;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lecturaFicheros.utiles.ManejaFicheroPersona;


public class GestionaPersona {
	private static final Logger logger = LogManager.getLogger(GestionaPersona.class);
	
	public static void main(String[] args) {
		List<Persona> lista = new ArrayList<>();
		String rutaFichero = "src\\main\\resource\\fichero1.txt";
		ManejaFicheroPersona p = new ManejaFicheroPersona();
		try {
			p.muestraContenido(rutaFichero);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void muestraContenido(String rutaFichero) throws FileNotFoundException {
			
			Scanner in = null;
			try {
				// abre el fichero
				FileReader fichero = new FileReader(rutaFichero);
				//Se crea el flujo
				in = new Scanner(fichero);
				// lee el fichero
				
				in.useDelimiter(","); //delimito por comas
				
				while (in.hasNext()) { //Lectura palabra a palabra
					// Aquí se hará la lectura in.next() palabra por palabra
					//logger.info(in.next());
					
					//Aqui linea por linea
					String linea = in.nextLine();
					logger.info("Palabra: "+ linea);
					Persona p = cargaPersona(linea);
					logger.info(p.toString());
					
					//
				
				}
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}
	
	
	public Persona cargaPersona(String linea) {
		String [] cadenas = linea.split(" ");
		List<String> lista = new ArrayList<String>();
		for(int i =1; i< cadenas.length; i++) {
			Persona p = new Persona(cadenas[i]);
			lista.add(p);
		}
		Persona p = new Persona(cadena[0],)
		return null;
		
	}
	
	}


