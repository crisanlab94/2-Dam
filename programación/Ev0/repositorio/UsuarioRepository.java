package repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Usuario;

public class UsuarioRepository {
	//añade usuarios (add) a repositorios, una lista...
	
	//Creamos lista de usuarios
	
	List <Usuario> usuarios;
	
	
	
   public UsuarioRepository() {
		super();
		this.usuarios = new ArrayList<Usuario>();
	}

   

	//Añadimos usuario
	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	//Eliminamos usuario
	 public void eliminarUsuario (Usuario usuarioEliminado) {
		 usuarios.remove(usuarioEliminado);
	 }
	 
	 //Leer (buscar por id)
	 
	 public void buscarPorId (String IdUsuario) {
	
		 usuarios.contains(IdUsuario);
	 }

	 public List<Usuario> obtenerUsuarios() {
		// TODO Auto-generated method stub
		return this.usuarios;
	 }
	
	
}
