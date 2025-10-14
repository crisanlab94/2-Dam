package boletin1ProcessBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreoyEjecuto {
public static void main(String[] args) {
	CreoyEjecuto ej = new CreoyEjecuto();
	ej.creaFicheroPython(); //Llamada al método
	ej.ejecutaPython(); //Llamada al método
	
}
public void creaFicheroPython() {
	//Define la ruta donde se quiere crear el archivo y como se llamará con la extension
	String ruta = "src\\main\\resources\\fichero.py";
	//Representa ubicacion del archivo que quiero crear
	File archivo = new File(ruta);
	//Cadena de texto dentro del archivo
	String mensaje ="print(\"Hola desde el fichero de python\")";
	
	try {
		//Crear un archivo y escribir texto (FileWriter)
		FileWriter fw = new FileWriter(archivo);
		fw.write(mensaje); //Escribo el mensaje
		fw.close();//Cierro el archivo
		System.out.println("Fichero creado correctamente en : " + archivo.getAbsolutePath()); //Confirmacion en consola	
	} catch (IOException e){
		e.printStackTrace();
	}
}

public void ejecutaPython() {
	//Ejecuta el programa python con el argumento src\main\resources\fichero.py
	String [] comando = {"python","src\\main\\resources\\fichero.py"};
	ProcessBuilder pb = new ProcessBuilder(comando);
	pb.redirectErrorStream(true); //Esto lo ponemos siempre
	pb.inheritIO(); //Esto lo ponemos siempre
	
	try {
		Process proceso = pb.start(); //Siempre tb
		int exitCode = proceso.waitFor(); //Siempre tb
		System.out.println("Python terminó con código: " + exitCode);	
	} catch (IOException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}

/*Diferencias entre Ejercicio10 y LanzadorPython
 * LanzadorPython:
Solo ejecuta un script Python ya existente (recibe la ruta) y muestra la salida línea a línea en consola.

Ejercicio10:
Primero crea un archivo Python con código dentro, y después ejecuta ese archivo.
LanzadorPython:
No crea ni modifica el fichero .py. Se asume que el fichero ya existe.

Ejercicio10:
Tiene el método creaFicheroPython() que genera el archivo .py con un mensaje específico (código Python simple).

LanzadorPython:

Usa ProcessBuilder para ejecutar el comando python archivo.py.

Usa BufferedReader para leer línea a línea la salida estándar (getInputStream()).

Imprime línea a línea la salida, permitiendo ver lo que imprime el script Python en tiempo real.

Espera a que el proceso termine y muestra el código de salida.
Ejercicio10:

También usa ProcessBuilder
No lee la salida línea a línea manualmente, sino que deja que la consola Java muestre directamente lo que Python imprime.

Espera a que el proceso termine y muestra el código de salida.*/