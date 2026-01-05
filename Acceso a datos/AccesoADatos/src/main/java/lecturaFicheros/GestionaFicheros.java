package lecturaFicheros;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class GestionaFicheros {
	
	static String rutaYnombre="src\\main\\resources\\fichero.txt";
	private static final Logger logger = LogManager.getLogger(GestionaFicheros.class);

	public static void main(String[] args)  {
		GestionaFicheros f = new GestionaFicheros();
		try {
			f.muestraContenidoFich(rutaYnombre);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	void muestraContenidoFich(String rutaYNombre) throws FileNotFoundException {
		
		Scanner in = null;
		try {
			// abre el fichero
			FileReader fichero = new FileReader(rutaYNombre);
			//Se crea el flujo
			in = new Scanner(fichero);
			// lee el fichero
			
			in.useDelimiter(","); //delimito por comas
			
			while (in.hasNext()) { //Lectura palabra a palabra
				// Aquí se hará la lectura in.next() palabra por palabra
				logger.info(in.next());
				
				//Aqui linea por linea
				logger.info(in.nextLine());
				
				//
			
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
