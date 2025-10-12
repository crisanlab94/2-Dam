package Boletin2FicherosFoldes;

import java.io.File;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionaDiffFolder {
	private static final Logger logger = Logger.getLogger(GestionaDiffFolder.class.getName());
	
	public static void main(String[] args) {
		try {
			 File folder1 = new File("C:\\Users\\Cristina\\Desktop\\2ºDAM\\2-Dam\\Acceso a datos\\AccesoADatos\\Prueba1");
	         File folder2 = new File("C:\\Users\\Cristina\\Desktop\\2ºDAM\\2-Dam\\Acceso a datos\\AccesoADatos\\Prueba2");
	         DiffFolder comparador = new DiffFolder(folder1, folder2);
	         Collection<ResultadoComparacion> resultados = comparador.compare();
	         for (ResultadoComparacion resultado : resultados) {
	                System.out.println(resultado);
		}
	
	}catch (Exception e) {
		logger.log(Level.SEVERE, "Error inesperado", e);
		e.printStackTrace();
	}
		
	}

}
