package tema1Ficheros;

import java.io.File;

public class Ejercicio3Clase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ejercicio3Clase a = new Ejercicio3Clase();
		File archivo = new File(
				"C:\\Users\\alumno\\Documents\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\AccesoADatosValido\\miDirectorio");
		a.getPropResursivo(archivo);
	}

	public void getPropResursivo(File padre) {
		if (padre.exists()) {
			if (padre.isDirectory()) {
				File[] listaficheros = padre.listFiles();

				for (File fichero : listaficheros) {
					if (fichero.isFile()) {
						System.out.println(fichero.getName());
					} else {
						System.out.println(fichero.getName());
						getPropResursivo(fichero);
					}
				}
			} else {
				System.out.println(padre.getName());
			}
		} else {
			System.out.println("no se puede encontrar");
		}
	}

}
