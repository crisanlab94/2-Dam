package proyectoSpringSecurity.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import proyectoSpringSecurity.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   
    Optional<Usuario> findByUsername(String username);
    
  
    boolean existsByUsername(String username);
=======

import proyectoSpringSecurity.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
>>>>>>> 6768c0b (07-03-2026)
}