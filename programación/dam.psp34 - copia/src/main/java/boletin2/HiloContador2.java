package boletin2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


	class HiloContador2 extends Thread {
	    private File fichero;
	    private ContadorCaracteres contador;

	    public HiloContador2(File fichero, ContadorCaracteres contador) {
	        this.fichero = fichero;
	        this.contador = contador;
	    }

	    @Override
	    public void run() {
	        int cuenta = contarCaracteres(fichero);
	        System.out.println("Fichero " + fichero.getName() + " tiene " + cuenta + " caracteres.");
	        contador.sumar(cuenta);
	    }

	    private int contarCaracteres(File fichero) {
	        int cuenta = 0;
	        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
	            int c;
	            while ((c = br.read()) != -1) {
	                cuenta++;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return cuenta;
	    }
	}


