package proyectoSpringSecurity.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
<<<<<<< HEAD
import proyectoSpringSecurity.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   
    Optional<Usuario> findByUsername(String username);
    
  
    boolean existsByUsername(String username);
=======

=======
>>>>>>> ac57a00 (modificación 07-03-2026)
import proyectoSpringSecurity.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   
    Optional<Usuario> findByUsername(String username);
<<<<<<< HEAD
>>>>>>> 6768c0b (07-03-2026)
=======
    
  
    boolean existsByUsername(String username);
>>>>>>> ac57a00 (modificación 07-03-2026)
}