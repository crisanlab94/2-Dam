package repaso.accesoADatos.servicio;

import java.util.List;
import java.util.stream.Collectors;

import repaso.accesoADatos.modelo.Pelicula;
import repaso.accesoADatos.repositorio.PeliculaRepositorio;
import repaso.accesoADatos.utiles.PeliculaException;

public class PeliculaServicio {
    private PeliculaRepositorio repositorio;

    public PeliculaServicio() {
        this.repositorio = new PeliculaRepositorio();
    }

    // Valida que el título de la película sea válido
    public void validarTitulo(Pelicula pelicula) throws PeliculaException {
        if (pelicula.getTitulo() == null || pelicula.getTitulo().isEmpty()) {
            throw new PeliculaException("El título no puede estar vacío");
        }
    }

    // Valida que la fecha de estreno no sea futura
    public void validarFecha(Pelicula pelicula) throws PeliculaException {
        int anioActual = java.time.Year.now().getValue();
        if (pelicula.getFecha() > anioActual) {
            throw new PeliculaException("El año de la película no puede ser mayor que el actual");
        }
    }

    // Aplica reglas de negocio antes de guardar o actualizar
    public void aplicarReglasNegocio(Pelicula pelicula) throws PeliculaException {
        validarTitulo(pelicula);
        validarFecha(pelicula);
        // Aquí puedes añadir más reglas, como duración mínima o género permitido
    }

 
 

}
