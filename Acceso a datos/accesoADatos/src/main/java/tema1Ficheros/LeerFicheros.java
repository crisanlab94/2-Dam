package tema1Ficheros;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Boletin1.PorbandoLog;

public class LeerFicheros {
	private static final Logger logger = LogManager.getLogger(PorbandoLog.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String rutaDirectorio = "C:\\Users\\alumno";
		logger.debug("hello world");
		File directorio = new File(rutaDirectorio);
		//logger.debug(archivos.toString());
 		// Referencio a un fichero dentro del directorio soraya
		File fichero = new File(directorio, "fichero.txt");
		fichero.mkdir();
		try {
			boolean creado = fichero.createNewFile(); // Aquí Sí creo fichero
			File file = new File("folder");
			boolean isCrear = file.mkdir();
			
			
			
			File f = new File("C:\\Users\\alumno");
			String [] archivos = f.list();
			for (String string : archivos) {
				logger.debug(string);
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error al crear fichero:" + e.getMessage());
		}
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	

}
