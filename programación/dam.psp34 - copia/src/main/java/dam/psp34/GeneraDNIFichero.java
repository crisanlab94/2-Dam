package dam.psp34;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GeneraDNIFichero {
	public static void main(String[] args) {
		String rutaDatos = args[0];
		GeneraDNIFichero gestion = new GeneraDNIFichero();
		List<String> listaNss = gestion.analizaInformacion(rutaDatos);
		for (Iterator iterator = listaNss.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			gestion.escribirTodosDni(listaNss, "src/main/resources/DNIs.txt");
		}

		System.out.println("DNIs Tratados:" + listaNss.size());

	}

	public List<String> analizaInformacion(String ruta) {
		FileReader mira = null;
		Scanner lineaFcihero = null;
		boolean hayNSS = false;
		List<String> dniLista = new ArrayList<String>();

		try {
			mira = new FileReader(ruta);
			lineaFcihero = new Scanner(mira);
			while (lineaFcihero.hasNext()) {
				String string = (String) lineaFcihero.nextLine();
				String[] partes = string.split(",");
				if (!partes[0].startsWith("AN")) {
					hayNSS = true;
					dniLista.add(partes[0]);
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (lineaFcihero != null) {
				lineaFcihero.close();
			}

			if (mira != null) {
				try {
					mira.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dniLista;

	}

	public void escribirTodosDni(List<String> lista, String rutaDestino) {
		PrintWriter pw = null;
		try { 
			pw = new PrintWriter(rutaDestino);

			if (lista != null && !lista.isEmpty()) {
				for (String nss : lista) {
					//para listas porque pasa de una linea a otra
					pw.println(nss);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
	}
}
