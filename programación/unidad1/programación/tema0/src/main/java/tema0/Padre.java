package tema0;

import java.util.List;

public class Padre {
	 int metodo1(int [] tabla) 
	{
		 int suma = 0;
		for (int i : tabla)
		{
			suma = suma+i;
		}
		return suma;
	}

	 int metodo2(int [] tabla) throws MiException 
	{
		 int suma = 0;
		for (int i : tabla)
		{
			suma = suma+i;
		}
		
		throw new MiException ("Viva España");
		
	}

	 
	 int metodo1(List<Integer> tabla) 
		{
			 int suma = 0;
			for (int i : tabla)
			{
				suma = suma+i;
			}
			return suma;
		}
	 
}
