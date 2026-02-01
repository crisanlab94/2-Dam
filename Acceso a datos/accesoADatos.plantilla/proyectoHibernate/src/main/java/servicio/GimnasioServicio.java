package servicio;

import java.util.List;
import modelo.*;
import repositorio.*;

public class GimnasioServicio {
    
    private RepositorioGimnasio repoGym;
    private RepositorioSocio repoSocio;
    private RepositorioEntrenador repoEntrenador;
    private RepositorioActividad repoActividad;
    private RepositorioDireccion repoDireccion;
    private RepositorioFichaMedica repoFicha;

    
    public GimnasioServicio() {
        this.repoGym = new RepositorioGimnasio();
        this.repoSocio = new RepositorioSocio();
        this.repoEntrenador = new RepositorioEntrenador();
        this.repoActividad = new RepositorioActividad();
        this.repoDireccion = new RepositorioDireccion();
        this.repoFicha = new RepositorioFichaMedica();
    }

  
    //Metodos crud
   
    
    //Socio 
    public void guardarSocio(Socio s) { 
    	repoSocio.create(s); 
    	}
    public void actualizarSocio(Socio s) { 
    	repoSocio.update(s);
    	}
    public void eliminarSocio(Socio s) { 
    	repoSocio.delete(s);
    	}
    public List<Socio> obtenerTodosLosSocios() {
    	return repoSocio.getAll(); 
    	}

    // Gimnasio
    public void guardarGimnasio(Gimnasio g) { 
    	repoGym.create(g);
    	}
    public void actualizarGimnasio(Gimnasio g) {  
    	repoGym.update(g);
    	}
    public void eliminarGimnasio(Gimnasio g) { 
    	repoGym.delete(g); 
    	}
    public List<Gimnasio> obtenerTodosLosGimnasios() {
    	return repoGym.getAll(); 
    	}
    // Entrenador
    public void guardarEntrenador(Entrenador e) { 
    	repoEntrenador.create(e); 
    	}
    public void actualizarEntrenador(Entrenador e) {  
    	repoEntrenador.update(e);
    	}
    public void eliminarEntrenador(Entrenador e) { 
    	repoEntrenador.delete(e); 
    	}
    public List<Entrenador> obtenerTodosLosEntrenadores() {
    	return repoEntrenador.getAll(); 
    	}
    //Actividad 

    public void guardarActividad(Actividad a) { 
    	repoActividad.create(a); 
    	}
    public void actualizarActividad(Actividad a) {  
    	repoActividad.update(a);
    	}
    public void eliminarActividad(Actividad a) { 
    	repoActividad.delete(a); 
    	}
    public List<Actividad> obtenerTodasLasActividades() {
    	return repoActividad.getAll(); 
    	}
    //Direccion
    public void guardarDireccion(Direccion d) { 
    	repoDireccion.create(d); 
    	}
    public void actualizarDireccion(Direccion d) { 
    	repoDireccion.update(d);
    	}
    public void eliminarDireccion(Direccion d) { 
    	repoDireccion.delete(d);
    	}
    public List<Direccion> obtenerTodasLasDirecciones() {
    	return repoDireccion.getAll(); 
    	}
    //FichaMedica
    public void guardarFichaMedica(FichaMedica f) {
    	repoFicha.create(f); 
    	}
    public void actualizarFichaMedica(FichaMedica f) {  
    	repoFicha.update(f); 
    	}
    public void eliminarFichaMedica(FichaMedica f) { 
    	repoFicha.delete(f); 
    	}
    public List<FichaMedica> obtenerTodasLasFichasMedicas() {
    	return repoFicha.getAll(); 
    	}
    
    //Otros métodos
    // --- Desde RepositorioSocio ---
    public List<String> obtenerNombresDeTodosLosSocios() {
    	return repoSocio.getAllNombresSocios();
    	}
    public List<Socio> obtener3UltimosSocios() { 
    	return repoSocio.getTresUltimosSocios(); 
    	}
    public Double obtenerPesoMedioSocios() { 
    	return repoSocio.getPesoMedioSocios(); 
    	}
    public List<Socio> buscarSociosPorGym(int idGym) { 
    	return repoSocio.getSociosPorGimnasio(idGym); 
    	}
    public List<Socio> buscarSociosPorNombreParcial(String trozo) { 
    	return repoSocio.buscarSociosPorNombre(trozo); 
    	}
    public Socio buscarSocioPorFicha(int idFicha) {
    	return repoSocio.getSocioPorFichaMedica(idFicha);
    	}
    public List<Socio> buscarSociosPorLetraInicial(String letra) { 
    	return repoSocio.buscarSociosPorComienzoLetra(letra); 
    	}
    public List<Socio> obtenerSociosPorSuscripcion(TipoSuscripcion tipo) {
        return repoSocio.getSociosPorTipoSuscripcion(tipo);
    }
    public List<Socio> obtenerSociosConDeudas() {
        return repoSocio.getSociosPendientesDePago();
    }
    public void borrarSocioPorNombreCriteria(String nombre) { 
    	repoSocio.borrarSocioPorNombreCriteria(nombre);
    	}
    public void inscribirSocioEnActividad(int idSocio, int idAct) {
    	repoSocio.inscribirSocioEnActividad(idSocio, idAct);
    	}
    public void borrarSocioDeActividad(int idSocio, int idAct) {
    	repoSocio.borrarSocioDeActividad(idSocio, idAct);
    	}

    // --- Desde RepositorioGimnasio ---
    public Gimnasio obtenerPrimerGimnasio() { 
    	return repoGym.getPrimerGimnasio(); 
    	}
    public Gimnasio obtenerGymCon3Entrenadores() {
	return repoGym.getPrimerGimnasioConTresEntrenadores(); 
}
    public List<Gimnasio> buscarGimnasiosPorCiudad(String ciudad) { 
    	return repoGym.getGimnasiosPorCiudad(ciudad);
    	}
    public void actualizarNombreGymCriteria(int id, String nuevoNombre) { 
    	repoGym.actualizarNombreCriteria(id, nuevoNombre); 
    	}

    // --- Desde RepositorioEntrenador ---
    public List<Entrenador> obtenerEntrenadoresPorTurno(Turno turno) {
        return repoEntrenador.getEntrenadoresPorTurno(turno);
    }
    public List<Entrenador> obtenerEntrenadoresActivos() {
        return repoEntrenador.getEntrenadoresEnPlantillaActiva();
    }
    public List<Entrenador> obtenerNombreYEspecialidadEntrenadores() { 
    	return repoEntrenador.getInfoBasicaEntrenadores();
    	}
    public List<Entrenador> buscarEntrenadoresPorEspecialidad(String esp) { 
    	return repoEntrenador.getEntrenadoresPorEspecialidad(esp);
    	}

    // --- Desde RepositorioActividad ---
    public Actividad buscarActividadPorNombre(String nombre) { 
    	return repoActividad.getActividadPorNombre(nombre); 
    	}
    public List<Actividad> obtenerActividadesPopulares(int min) { 
    	return repoActividad.getActividadesPopulares(min); 
    	}
    public Actividad buscarActividadPorId(int id) { 
    	return repoActividad.getActividadPorIdHql(id);
    }

    // --- Desde RepositorioDireccion ---
    public List<Direccion> buscarDireccionesPorCiudadOrdenadas(String ciudad) {
    	return repoDireccion.getDireccionesPorCiudadOrdenadas(ciudad); 
    	}
    public List<Direccion> buscarDireccionPorCalle(String calle) { 
    	return repoDireccion.buscarPorCalle(calle);
    	}

    // --- Desde RepositorioFichaMedica ---
    public Double obtenerPesoMaximoRegistrado() { 
    	return repoFicha.getPesoMaximo(); 
    	}
    public List<FichaMedica> buscarFichasPorRangoDePeso(double min, double max) { 
    	return repoFicha.getFichasPorRangoPeso(min, max); 
    	}
}