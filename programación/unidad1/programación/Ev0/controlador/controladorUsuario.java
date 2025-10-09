package controlador;

import java.time.LocalDate;

import modelo.Usuario;
import repositorio.UsuarioRepository;
import servicios.UsuarioService;

public class controladorUsuario {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		/*Usuario u = new Usuario("123","Maria","aaa@aaa",LocalDate.now(),false);
		UsuarioService usuarioServicio = new UsuarioService();
		
		usuarioServicio.agregarUsuario(u);
		System.out.println(usuarioServicio.getRepo());*/
		
		 // Crear un nuevo usuario
        Usuario u = new Usuario("123", "Maria", "aaa@aaa", LocalDate.now(), false);

        // Crear el servicio de usuario
        UsuarioService usuarioService = new UsuarioService();

        // Agregar el usuario al repositorio
        usuarioService.agregarUsuario(u);

        // Mostrar los usuarios en el repositorio
        System.out.println(usuarioService.getRepo().obtenerUsuarios());
		

		/*Usuario u1= new Usuario("123", "Pepe", "ejemplo.com", LocalDate.now()); 

		a.agregarUsuario(u1);

		System.out.println(a); */

	     u. buscarPorId("123"); 

	}



}


