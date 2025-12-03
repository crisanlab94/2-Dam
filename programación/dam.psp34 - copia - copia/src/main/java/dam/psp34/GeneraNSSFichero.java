package dam.psp34;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GeneraNSSFichero { 
	public static void main(String[] args) { 
		String rutaFichero = args[0]; 
		GeneraNSSFichero p = new GeneraNSSFichero();
		List<String> listaNss = p.analizaInformacion(rutaFichero);
		for (Iterator iterator = listaNss.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			p.escribirTodosNSS(listaNss, "src/main/resources/NSSs.txt");
		}
		
		System.out.println("NSSs Tratados: "+ listaNss.size());
	}

	public List<String> analizaInformacion(String ruta) { 
		FileReader mira = null;
		Scanner lineaFcihero = null;
		boolean hayNSS = false;
		List<String> nssLista = new ArrayList<String>();

		try {
			mira = new FileReader(ruta);
			lineaFcihero = new Scanner(mira);
			while (lineaFcihero.hasNext()) {
				String string = (String) lineaFcihero.nextLine();
				String[] partes = string.split(",");
				if (partes[0].startsWith("AN") && !(partes[0].length() < 12)) {
					hayNSS = true;
					nssLista.add(partes[0]);
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
		return nssLista;

	}

	public void escribirTodosNSS(List<String> lista, String rutaDestino) {
		PrintWriter pw = null;

		try { 
			pw = new PrintWriter(rutaDestino); 

			if (lista != null && !lista.isEmpty()) {
				for (String nss : lista) {
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
