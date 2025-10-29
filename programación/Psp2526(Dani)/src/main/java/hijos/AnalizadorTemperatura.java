package hijos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AnalizadorTemperatura {
	private static final String rutaResoruces = "src/main/resources/";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombreFicheroTemp = args[0];
		int umbral = Integer.parseInt(args[1]);
		String nombreFicheroResult = args[2];
		AnalizadorTemperatura p = new AnalizadorTemperatura();
		try {
			p.analizaTemperatura(nombreFicheroTemp, umbral);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int analizaTemperaturaSinEscribir(String ficheroYRuta, int umbral) throws FileNotFoundException {
		int tempMayorUmbral = 0;
		FileReader fichero = new FileReader(ficheroYRuta);
		Scanner in = new Scanner(fichero);

		while (in.hasNextLine()) {
			String tempString = in.nextLine();
			int temperatura = Integer.parseInt(tempString);
			if (temperatura > umbral) {
				tempMayorUmbral += 1;
			}
		}

		in.close();
		System.out.println("Umbral " + umbral + " → " + tempMayorUmbral + " días");

		return tempMayorUmbral;
	}

	public int analizaTemperatura(String ficheroYRuta, int umbral) throws FileNotFoundException {
		int tempMayorUmbral = 0;
		File ficheroS = new File(ficheroYRuta);
		FileReader fichero = new FileReader(ficheroYRuta);
		Scanner in = new Scanner(fichero);
		while (in.hasNextLine()) {
			String tempString = in.nextLine();
			int temperatura = Integer.parseInt(tempString);
			if (temperatura > umbral) {
				tempMayorUmbral += 1;
				this.escribreTemperatura(tempMayorUmbral, rutaResoruces + umbral + ".txt");
			}

		}
		in.close();
		return tempMayorUmbral;
	}

	public void escribreTemperatura(int temperatura, String rutaFichero) {
		FileWriter ficheroSalida = null;
		try {
			ficheroSalida = new FileWriter(rutaFichero);
			String tempString = String.valueOf(temperatura);
			ficheroSalida.write(tempString);

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

	public int analizaTemperaturaConOutPrintF(String ficheroYRuta, int umbral) throws FileNotFoundException {
		int tempMayorUmbral = 0;
		FileReader fichero = new FileReader(ficheroYRuta);
		Scanner in = new Scanner(fichero);

		while (in.hasNextLine()) {
			int temperatura = Integer.parseInt(in.nextLine());
			if (temperatura > umbral) {
				tempMayorUmbral++;
			}
		}
		in.close();

		System.out.printf("Umbral: %d Temperaturas mayores: %d%n", umbral, tempMayorUmbral);
		escribreTemperaturaConOutPrintf(tempMayorUmbral, rutaResoruces + umbral + ".txt");

		return tempMayorUmbral;
	}

	public void escribreTemperaturaConOutPrintf(int temperatura, String rutaFichero) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(rutaFichero);
			out.printf("%d%n", temperatura); // Escribe el número con salto de línea
			System.out.printf("Resultado guardado en '%s': %d%n", rutaFichero, temperatura);
		} catch (IOException e) {
			System.out.printf("IOException al escribir en '%s'%n", rutaFichero);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
