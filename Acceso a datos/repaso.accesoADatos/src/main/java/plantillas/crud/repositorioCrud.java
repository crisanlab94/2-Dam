package plantillas.crud;

public class repositorioCrud {
	/** 
    Lista privada que almacena los objetos: List<T> o Set<T>

Constructor: inicializa la lista vacía

CRUD básico:

agregar → añade objeto si no es nulo

getLista → devuelve la lista completa

actualizar → reemplaza objeto existente

eliminar → quita objeto existente

contiene → comprueba si la lista tiene el objeto
**/
	  // Lista que almacena todos los eventos
    private List<Evento> nombreDeLaLista;

    // Constructor: inicializa la lista vacía
    public RepositorioEventos() {
        this.nombreDeLaLista = new ArrayList<>();
    }
	
/**con indice numerico 	**//
	   // Agrega una combinación a la lista si no es nula
    public void agregarCombinacion(Combinaciones combinacion) {
        if (combinacion != null) {
            listaCombinaciones.add(combinacion);
        }
    }

    // Devuelve la lista completa de combinaciones
    public List<Combinaciones> getListaCombinaciones() {
        return listaCombinaciones;
    }

    // Actualiza una combinación según su índice (número)
    // Devuelve true si se actualizó correctamente
    public boolean actualizarCombinacion(int indice, Combinaciones nuevaCombinacion) {
        boolean actualizado = false;
        if (indice >= 0 && indice < listaCombinaciones.size() && nuevaCombinacion != null) {
            listaCombinaciones.set(indice, nuevaCombinacion);
            actualizado = true;
        }
        return actualizado;
    }

    // Elimina una combinación según su índice (número)
    public boolean eliminarCombinacion(int indice) {
        boolean eliminado = false;
        if (indice >= 0 && indice < listaCombinaciones.size()) {
            listaCombinaciones.remove(indice);
            eliminado = true;
        }
        return eliminado;
    }

    // Comprueba si la lista contiene la combinación
    public boolean contieneCombinacion(Combinaciones combinacion) {
        boolean encontrado = false;
        if (listaCombinaciones.contains(combinacion)) {
            encontrado = true;
        }
        return encontrado;
    }
}


/**con id numerico**/

// Agrega una nueva interacción si no es nula
public void agregarInteraccion(InteraccionAgente interaccion) {
    if (interaccion != null) {
        registros.add(interaccion);
    }
}

// Devuelve la lista completa
public List<InteraccionAgente> getRegistros() {
    return registros;
}

// Actualiza la interacción buscando por id (número)
public boolean actualizarPorId(int id, InteraccionAgente nuevaInteraccion) {
    boolean actualizado = false;
    for (int i = 0; i < registros.size(); i++) {
        InteraccionAgente actual = registros.get(i);
        if (actual.getIdentificador() == id && nuevaInteraccion != null) {
            registros.set(i, nuevaInteraccion);
            actualizado = true;
        }
    }
    return actualizado;
}

// Elimina la interacción según su id
public boolean eliminarPorId(int id) {
    boolean eliminado = false;
    List<InteraccionAgente> nuevaLista = new ArrayList<>();
    for (InteraccionAgente inter : registros) {
        if (inter.getIdentificador() != id) {
            nuevaLista.add(inter);
        } else {
            eliminado = true;
        }
    }
    registros = nuevaLista;
    return eliminado;
}

// Comprueba si la lista contiene la interacción
public boolean contieneInteraccion(InteraccionAgente interaccion) {
    boolean encontrado = false;
    if (registros.contains(interaccion)) {
        encontrado = true;
    }
    return encontrado;
}
}

/**Repositorio con dni (numero+letra)**/

// Agrega una persona a la lista si no es nula
public void agregarPersona(Persona p) {
    if (p != null) {
        listaPersonas.add(p);
    }
}

// Devuelve la lista completa de personas
public List<Persona> getListaPersonas() {
    return listaPersonas;
}

// Actualiza una persona según su DNI (8 números + 1 letra)
// Devuelve true si se actualizó correctamente
public boolean actualizarPorDni(String dni, Persona nuevaPersona) {
    boolean actualizado = false;
    for (int i = 0; i < listaPersonas.size(); i++) {
        Persona p = listaPersonas.get(i);
        if (p.getDni().equalsIgnoreCase(dni) && nuevaPersona != null) {
            listaPersonas.set(i, nuevaPersona);
            actualizado = true;
        }
    }
    return actualizado;
}

// Elimina una persona según su DNI (8 números + 1 letra)
// Devuelve true si se eliminó correctamente
public boolean eliminarPorDni(String dni) {
    boolean eliminado = false;
    List<Persona> nuevas = new ArrayList<>();
    for (Persona p : listaPersonas) {
        if (p.getDni().equalsIgnoreCase(dni)) {
            eliminado = true;
        } else {
            nuevas.add(p);
        }
    }
    listaPersonas = nuevas;
    return eliminado;
}

// Comprueba si la persona está en la lista según su DNI
public boolean contienePersona(Persona p) {
    boolean encontrado = false;
    if (listaPersonas.contains(p)) {
        encontrado = true;
    }
    return encontrado;
}

/** Por nombre y apellidos**//

// Agrega una persona a la lista si no es nula
public void agregarPersona(Persona p) {
    if (p != null) {
        listaPersonas.add(p);
    }
}

// Devuelve la lista completa de personas
public List<Persona> getListaPersonas() {
    return listaPersonas;
}

// Actualiza una persona según su nombre y apellidos (String)
// Devuelve true si se actualizó correctamente
public boolean actualizarPorNombreApellido(String nombre, String apellidos, Persona nuevaPersona) {
    boolean actualizado = false;
    for (int i = 0; i < listaPersonas.size(); i++) {
        Persona p = listaPersonas.get(i);
        if (p.getNombre().equalsIgnoreCase(nombre) && p.getApellidos().equalsIgnoreCase(apellidos)
                && nuevaPersona != null) {
            listaPersonas.set(i, nuevaPersona);
            actualizado = true;
        }
    }
    return actualizado;
}

// Elimina una persona según su nombre y apellidos
// Devuelve true si se eliminó correctamente
public boolean eliminarPorNombreApellido(String nombre, String apellidos) {
    boolean eliminado = false;
    List<Persona> nuevas = new ArrayList<>();
    for (Persona p : listaPersonas) {
        if (p.getNombre().equalsIgnoreCase(nombre) && p.getApellidos().equalsIgnoreCase(apellidos)) {
            eliminado = true;
        } else {
            nuevas.add(p);
        }
    }
    listaPersonas = nuevas;
    return eliminado;
}

// Comprueba si la persona está en la lista según nombre y apellidos
public boolean contienePersona(Persona p) {
    boolean encontrado = false;
    if (listaPersonas.contains(p)) {
        encontrado = true;
    }
    return encontrado;
}

/**Por nombre y fecha **//

// Agrega un evento a la lista si no es nulo
public void agregarEvento(Evento e) {
    if (e != null) {
        listaEventos.add(e);
    }
}

// Devuelve la lista completa de eventos
public List<Evento> getListaEventos() {
    return listaEventos;
}

// Actualiza un evento según su nombre y fecha
// Devuelve true si se actualizó correctamente
public boolean actualizarPorNombreYFecha(String nombre, LocalDate fecha, Evento nuevoEvento) {
    boolean actualizado = false;
    for (int i = 0; i < listaEventos.size(); i++) {
        Evento e = listaEventos.get(i);
        if (e.getNombre().equalsIgnoreCase(nombre) && e.getFecha().equals(fecha) && nuevoEvento != null) {
            listaEventos.set(i, nuevoEvento);
            actualizado = true;
        }
    }
    return actualizado;
}

// Elimina un evento según su nombre y fecha
// Devuelve true si se eliminó correctamente
public boolean eliminarPorNombreYFecha(String nombre, LocalDate fecha) {
    boolean eliminado = false;
    List<Evento> nuevas = new ArrayList<>();
    for (Evento e : listaEventos) {
        if (e.getNombre().equalsIgnoreCase(nombre) && e.getFecha().equals(fecha)) {
            eliminado = true;
        } else {
            nuevas.add(e);
        }
    }
    listaEventos = nuevas;
    return eliminado;
}

// Comprueba si un evento está en la lista
public boolean contieneEvento(Evento e) {
    boolean encontrado = false;
    if (listaEventos.contains(e)) {
        encontrado = true;
    }
    return encontrado;
}
	
}
