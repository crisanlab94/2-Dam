package boletin2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ContarProceso {
	    public static void main(String[] args) {
	        if (args.length == 0) {
	            System.out.println("No se indicó fichero.");
	            return;
	        }
	        File fichero = new File(args[0]);
	        int cuenta = 0;
	        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
	            int c;
	            while ((c = br.read()) != -1) {
	                cuenta++;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Proceso contó " + cuenta + " caracteres en " + fichero.getName());
	    }
	}



