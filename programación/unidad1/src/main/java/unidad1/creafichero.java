package unidad1;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class creafichero {
	//Me creo una clase donde guardo el logger pongo private static final Logger logger = LogManager.getLogger(nombreClase.class)
	private static final Logger logger = LogManager.getLogger(PorbanLog.class) ;
	
	public static void main(String[] args) {
		String rutaDirectorio = "C:\\Users\\alumno";
		File directorio = new File(rutaDirectorio);
		// Referencio a un fichero dentro del directorio soraya
		File fichero = new File(directorio, "fichero1.txt");
		try {
			boolean creado = directorio.mkdir();
			boolean creado1 = fichero.createNewFile(); // Aquí Sí creo fichero
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error al crear fichero:" + e.getMessage());
		}
		
		String rutaDirectorio1 = "C:\\Users\\alumno";
		File directorio1 = new File(rutaDirectorio);
		// Referencio a un fichero dentro del directorio soraya
		File fichero1 = new File(directorio, "fichero1.txt");
			boolean creado = directorio.mkdir();
			
			
			
		File f = new File ("C:\\Users\\alumno");
		String [] archivos = f.list();
			for (String ficheros : archivos) {
			System.out.println(ficheros);
		}
	}
	
	
}


	

