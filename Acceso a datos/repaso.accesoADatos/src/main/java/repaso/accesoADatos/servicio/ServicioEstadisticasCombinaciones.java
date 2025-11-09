package repaso.accesoADatos.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import repaso.accesoADatos.modelo.Combinaciones;
import repaso.accesoADatos.repositorio.HistoricoCombinaciones;

public class ServicioEstadisticasCombinaciones {

    // Repositorio de combinaciones histórico
    private HistoricoCombinaciones repoHistorico;

    // Constructor: recibe el repositorio para trabajar sobre él
    public ServicioEstadisticasCombinaciones(HistoricoCombinaciones repo) {
        this.repoHistorico = repo;
    }

  
    // Número más repetido en todas las combinaciones
    public int numeroMasRepetido() {
        // Creamos un mapa para contar cuántas veces aparece cada número
        Map<Integer, Integer> contador = new HashMap<>();
        // Obtenemos la lista completa de combinaciones del repositorio
        List<Combinaciones> lista = repoHistorico.getListaHistorico();

        // Recorremos cada combinación
        for (int i = 0; i < lista.size(); i++) {
            Combinaciones c = lista.get(i);
            // Recorremos los 5 números de cada combinación
            for (int n : c.getNumeros()) {
                // Si el número ya está en el mapa, incrementamos su contador
                if (contador.containsKey(n)) {
                    contador.put(n, contador.get(n) + 1);
                } else {
                    // Si es la primera vez que aparece, lo ponemos a 1
                    contador.put(n, 1);
                }
            }
        }

        // Ahora buscamos el número con más repeticiones
        int max = -1;          // máximo contador encontrado
        int numeroMas = -1;    // número que se repite más
        for (Map.Entry<Integer, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                numeroMas = entry.getKey();
            }
        }

        // Devolvemos el número más repetido
        return numeroMas;
    }

    //  Estrella más repetida en todas las combinaciones
    public int estrellaMasRepetida() {
        Map<Integer, Integer> contador = new HashMap<>();
        List<Combinaciones> lista = repoHistorico.getListaHistorico();

        for (int i = 0; i < lista.size(); i++) {
            Combinaciones c = lista.get(i);
            // Recorremos las 2 estrellas de cada combinación
            for (int e : c.getEstrellas()) {
                if (contador.containsKey(e)) {
                    contador.put(e, contador.get(e) + 1);
                } else {
                    contador.put(e, 1);
                }
            }
        }

        int max = -1;
        int estrellaMas = -1;
        for (Map.Entry<Integer, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                estrellaMas = entry.getKey();
            }
        }

        return estrellaMas;
    }

    // Número de veces que ha salido cada combinación de números
    public Map<String, Integer> vecesCadaCombinacion() {
        // Creamos un mapa donde la clave será una cadena que representa los números "1-2-3-4-5"
        Map<String, Integer> contador = new HashMap<>();
        List<Combinaciones> lista = repoHistorico.getListaHistorico();

        for (int i = 0; i < lista.size(); i++) {
            Combinaciones c = lista.get(i);
            // Generamos la clave como string
            String key = "" + c.getNumeros()[0] + "-" + c.getNumeros()[1] + "-" +
                         c.getNumeros()[2] + "-" + c.getNumeros()[3] + "-" + c.getNumeros()[4];
            // Si ya existe, incrementamos el contador
            if (contador.containsKey(key)) {
                contador.put(key, contador.get(key) + 1);
            } else {
                // Si es la primera vez que aparece, lo ponemos a 1
                contador.put(key, 1);
            }
        }

        return contador;
    }

    // Combinación más frecuente (números + estrellas)
    public Combinaciones combinacionMasFrecuente() {
        // Contador donde la clave es la combinación completa
        Map<Combinaciones, Integer> contador = new HashMap<>();
        List<Combinaciones> lista = repoHistorico.getListaHistorico();

        for (int i = 0; i < lista.size(); i++) {
            Combinaciones c = lista.get(i);
            // Si ya existe en el mapa, incrementamos
            if (contador.containsKey(c)) {
                contador.put(c, contador.get(c) + 1);
            } else {
                // Primera vez que aparece
                contador.put(c, 1);
            }
        }

        // Buscamos la combinación con más repeticiones
        int max = -1;
        Combinaciones masFrecuente = null;
        for (Map.Entry<Combinaciones, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                masFrecuente = entry.getKey();
            }
        }

        return masFrecuente;
    }
}
