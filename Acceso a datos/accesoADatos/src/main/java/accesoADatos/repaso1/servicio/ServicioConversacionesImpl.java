package accesoADatos.repaso1.servicio;

import java.time.LocalDate;

import accesoADatos.repaso.repositorio.RepositorioConversaciones;
import accesoADatos.repaso1.excepciones.ConversacionException;
import accesoADatos.repaso1.modelo.Conversacion;
import accesoADatos.repaso1.modelo.TipoAgente;

public class ServicioConversacionesImpl implements IServicioConversaciones {

	RepositorioConversaciones repoConver = new RepositorioConversaciones();
	
	@Override
	public void registraNuevaConveracion(TipoAgente tipo, String pregunta, String respuesta) {
		repoConver.agregaConversacion(tipo, pregunta, respuesta);
		
	}

	@Override
	public Conversacion getRecuperaConversacion(TipoAgente tipo, String pregunta, LocalDate fecha) {
		Conversacion conver = null;
		try {
			conver= repoConver.getConversacion(fecha, tipo, pregunta);
		} catch (ConversacionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo) throws ConversacionException {
	
		return false;
	}

	@Override
	public boolean incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta) {
	
		return false;
	}

	@Override
	public double getValoracionMediaParaHumanos() {
	
		return 0;
	}

}
