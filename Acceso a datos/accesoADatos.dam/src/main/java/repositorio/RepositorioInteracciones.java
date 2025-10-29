package repositorio;

import java.util.ArrayList;
import java.util.List;

import modelo.InteraccionAgente;

public class RepositorioInteracciones {
	private List<InteraccionAgente> registros;

	public RepositorioInteracciones() {
		super();
		this.registros = new ArrayList<>();
	}
	
	

    public void agregaInteraccionARegistro(InteraccionAgente interaccion) {
        registros.add(interaccion);
    }	
    
    public boolean actualizaPorcentajeInteraccion(int id, double porcentajeNuevo) {
        boolean actualizado = false;
        for (InteraccionAgente i : registros) {
            if (i.getIdentificador() == id) {
                i.setPorcentajeAcierto(porcentajeNuevo);
                actualizado = true;
            }
        }
        return actualizado;
    }
    
    
    public boolean eliminarInteraccion(int identificador) {
        boolean eliminada = false;
        List<InteraccionAgente> nuevas = new ArrayList<>();

        for (InteraccionAgente i : registros) {
            if (!(i.getIdentificador() == identificador)) {
                nuevas.add(i);
            } else {
                eliminada = true;
            }
        }

        registros = nuevas;
        return eliminada;
    }
    
    public void incrementaNumeroValoraciones(int id, int nuevaValoracion) {
        for (int i = 0; i < registros.size(); i++) {
            InteraccionAgente inter = registros.get(i);
            if (inter.getIdentificador() == id) {
                inter.setNumValoracionesPositivas(nuevaValoracion);
            }
        }
    }
    
    public List<InteraccionAgente> getRegistros() {
        return registros;
    }
    
}
