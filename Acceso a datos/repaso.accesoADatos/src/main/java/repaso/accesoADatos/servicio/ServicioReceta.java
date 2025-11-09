package repaso.accesoADatos.servicio;

import java.util.List;

import repaso.accesoADatos.modelo.Receta;
import repaso.accesoADatos.repositorio.RepositorioReceta;

public class ServicioReceta {
	private RepositorioReceta repositorio;

    public ServicioReceta() {
        this.repositorio = new RepositorioReceta();
    }

    // Devuelve todas las recetas
    public List<Receta> getTodas() {
        return repositorio.leerLista();
    }

    // Agrega una nueva receta
    public void agregarReceta(Receta r) {
        repositorio.agregarReceta(r);
    }

    // Elimina una receta
    public void eliminarReceta(Receta r) {
        repositorio.eliminarReceta(r);
    }

    // Actualiza receta
    public void actualizarReceta(Receta recetaVieja, Receta recetaNueva) {
        repositorio.actualizarReceta(recetaVieja, recetaNueva);
    }

}
