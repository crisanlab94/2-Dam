package servicios;

import modelo.Usuario;
import repositorio.UsuarioRepository;

public class UsuarioService {

	private UsuarioRepository repo;
	
	public UsuarioService () {
		super();
		this.repo= new UsuarioRepository();
	}
	
	public void agregarUsuario (Usuario usuario)
	{
		repo.agregarUsuario(usuario);
	}

	public UsuarioRepository getRepo() {
		return repo;
	}

	public void setRepo(UsuarioRepository repo) {
		this.repo = repo;
	}


	
	
}
