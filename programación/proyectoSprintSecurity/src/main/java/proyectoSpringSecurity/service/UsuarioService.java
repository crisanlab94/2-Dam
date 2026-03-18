package proyectoSpringSecurity.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import proyectoSpringSecurity.models.Usuario;

public interface UsuarioService extends UserDetailsService {
   
    public boolean existsByUsername(String username);
    public void saveUsuario(Usuario u);
}