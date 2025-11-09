package accesoADatos.plantilla;

import java.util.ArrayList;
import java.util.List;
import Modelo;

/**
 * Repositorio genérico
 * Maneja la lista de objetos en memoria
 * El CRUD funciona igual para XML, JSON o CSV
 */
public class Repositorio {

    private List<Modelo> lista;

    public Repositorio() {
        this.lista = new ArrayList<>(); // Inicializa la lista vacía
    }

    // Devuelve todos los objetos
    public List<Modelo> cargar() {
        return lista;
    }

    // Agrega un objeto a la lista
    public void agregar(Modelo obj) {
        lista.add(obj);
    }

    // Elimina un objeto de la lista
    public void eliminar(Modelo obj) {
        lista.remove(obj);
    }

    // Actualiza un objeto existente por uno nuevo
    //Esto se usa con el equals en la clase modelo
    public void actualizar(Modelo objViejo, Modelo objNuevo) {
        int index = lista.indexOf(objViejo);
        if (index != -1) {
            lista.set(index, objNuevo);
        }
    }
    
    //Entonces no puedes usar indexOf directamente. Necesitas buscar el objeto por algún atributo único 
    //(como el título en películas, nombre en Pokémon, etc.) 
    //y luego reemplazarlo manualmente:
    public void actualizarPorTitulo(String titulo, Pelicula peliculaNueva) {
        for (int i = 0; i < lista.size(); i++) {
            // Compara el título ignorando mayúsculas/minúsculas
            if (lista.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                lista.set(i, peliculaNueva); // reemplaza el objeto encontrado
                
            }
        }
    }
 //Ejemplo numerico
    
    public void actualizarPorFecha(int fecha, Pelicula peliculaNueva) {
        for (int i = 0; i < lista.size(); i++) {
            // Compara el año de estreno
            if (lista.get(i).getFecha() == fecha) {
                lista.set(i, peliculaNueva); // reemplaza todos los objetos que coincidan con la fecha
            }
        }
    }

    
}

