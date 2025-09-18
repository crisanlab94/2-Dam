package repositorio;

import java.util.ArrayList;
import java.util.List;

import modelo.Canal;
import modelo.Usuario;


public class CanalRepository {
	List <Canal> canales= new ArrayList<Canal>();
	//Añadimos canal
		public void agregarUsuario(Canal canal) {
			canales.add(canal);
		}
		
		//Eliminamos canal
		 public void eliminarUsuario (Canal canalEliminado) {
			 canales.remove(canalEliminado);
		 }

}
