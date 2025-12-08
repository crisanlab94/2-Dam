
package mongoDB.Modelo;

import java.util.Comparator;



public class NombreAscendenteComparator implements Comparator<Estudiante> {

    @Override
    public int compare(Estudiante e1, Estudiante e2) {
   
        return e1.getNombre().compareToIgnoreCase(e2.getNombre());
    }
}
