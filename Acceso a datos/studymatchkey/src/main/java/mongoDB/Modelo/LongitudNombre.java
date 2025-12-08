package mongoDB.Modelo;

import java.util.Comparator;

public class LongitudNombre implements Comparator<Estudiante> {
	@Override
    public int compare(Estudiante e1, Estudiante e2) {
        // Compara la longitud del nombre del estudiante e1 con la de e2.
       
        return Integer.compare(e1.getNombre().length(), e2.getNombre().length());
    }

}
