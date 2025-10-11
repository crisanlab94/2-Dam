package Boletin1Controlador;

import java.time.LocalDate;

import Boletin1Modelo.Conversacion;
import Boletin1Modelo.TipoAgente;
import Boletin1Servicio.ServicioConversacionesImpl;



public class ControladorConversaciones {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServicioConversacionesImpl s = new ServicioConversacionesImpl();
		Conversacion a = new Conversacion("Mensajitos", "pregunta", "respuesta", LocalDate.now(),TipoAgente.HUMANO, 10);
		Conversacion a2 = new Conversacion("Mensajitos112", "pregunta", "respuesta", LocalDate.now(),TipoAgente.HUMANO, 10);
		Conversacion a3= new Conversacion("Mensajitos2121", "pregunta", "respuesta", LocalDate.now(),TipoAgente.HUMANO, 10);
		
 
	}

}
