package Tema1;

public class CalculaSuma {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculaSuma cal = new CalculaSuma();
		boolean par = Boolean.parseBoolean(args[0]);
		int numero = Integer.parseInt(args[1]);
		//System.out.println(cal.suma(par, numero));
        System.out.println(cal.suma(par, numero));

	}
 
	public Integer suma(Boolean parImpar, Integer n2) {
	    Integer suma = 0;
	    for (int i = 1; i <= n2; i++) {
	        if (parImpar && i % 2 == 0) { 
	            System.out.println(suma);

	            suma += i;
	        } else if (!parImpar && i % 2 != 0) { 
	            System.out.println(suma);
	            suma += i;
	        }
	    }
	    return suma; 
	}

}
