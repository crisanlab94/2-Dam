package Boletin1Servicio;

import java.time.LocalDate;

import Boletin1Excepcion.ConversacionException;
import Boletin1Modelo.Conversacion;
import Boletin1Modelo.TipoAgente;
import Boletin1Repositorio.RepositorioConversaciones;

public class ServicioConversacionesImpl implements IServicioConversaciones {
	RepositorioConversaciones repoConvers = new RepositorioConversaciones();

	@Override
	public void registraNuevaConveracion(TipoAgente tipo, String pregunta, String respuesta) {
		repoConvers.agregaConversacion(tipo, pregunta, respuesta);
		// TODO Auto-generated method stub
	}

	@Override
	public Conversacion getRecuperaConversacion(TipoAgente tipo, String pregunta, LocalDate fecha) {
		// TODO Auto-generated method stub
		Conversacion p = null;
		try {
			p = repoConvers.getConversacion(fecha, tipo, pregunta);
		} catch (ConversacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	@Override
	public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo) throws ConversacionException {
		// TODO Auto-generated method stub
		repoConvers.eliminaConversacion(fecha, tipo, null); 
		return false;
	}

	@Override
	public boolean incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta) { 
		// TODO Auto-generated method stub
		boolean incrementa = false;
		Conversacion ejemplillo = getRecuperaConversacion(tipo, pregunta, fecha);

		if (ejemplillo != null) {
			try {
				double valora = ejemplillo.getNumValoracionesPositivas();
				repoConvers.incrementaNumeroValoraciones(fecha, tipo, pregunta, valora);
				incrementa = true;
			} catch (ConversacionException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

		}

		return incrementa;
	}

	@Override
	public double getValoracionMediaParaHumanos() { 
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValoracionMedidaParaBots() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "ServicioConversacionesImpl [repoConvers=" + repoConvers + "]";
	}

}
