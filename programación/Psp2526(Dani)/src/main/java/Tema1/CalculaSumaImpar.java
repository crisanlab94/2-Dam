package Tema1;

public class CalculaSumaImpar {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculaSumaImpar cal = new CalculaSumaImpar();
		boolean par = Boolean.parseBoolean(args[0]);
		int numero = Integer.parseInt(args[1]);
		//System.out.println(cal.suma(par, numero));
        System.out.println(cal.sumaImpar(par, numero));

	}
 
	public Integer sumaImpar(Boolean parImpar, Integer n2) {
	    Integer suma = 0;
	    for (int i = 1; i <= n2; i++) {
	        if (!parImpar && i % 2 != 0)  
	            System.out.println(suma);
	            suma += i;
	       
	    }
	    return suma; 
	}

}
