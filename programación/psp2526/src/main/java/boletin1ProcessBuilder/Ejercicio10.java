package boletin1ProcessBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio10 {
	 public static void main(String[] args) {
	        Ejercicio10 ej = new Ejercicio10();
	        ej.creaFicheroPython();  // Solo se crea el fichero
	    }

	    public void creaFicheroPython() {
	        // Ruta al fichero que se creará
	        String ruta = "src\\main\\resources\\fichero.py";
	        File archivo = new File(ruta);

	        // Contenido del fichero Python
	        String mensaje = "print(\"Hola desde el fichero de python\")";

	        try {
	            FileWriter fw = new FileWriter(archivo);
	            fw.write(mensaje);
	            fw.close();
	            System.out.println("Fichero creado correctamente en: " + archivo.getAbsolutePath());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


