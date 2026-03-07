package proyectoSpringSecurity.controller;

<<<<<<< HEAD
=======


>>>>>>> 6768c0b (07-03-2026)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import proyectoSpringSecurity.models.Usuario;
import proyectoSpringSecurity.service.UsuarioService;
import proyectoSpringSecurity.security.JwtUtil;
=======

import proyectoSpringSecurity.models.Usuario;
import proyectoSpringSecurity.repository.UsuarioRepository;
import proyectoSpringSecurity.security.JwtUtils;

>>>>>>> 6768c0b (07-03-2026)
import java.util.HashMap;
import java.util.Map;

@RestController
<<<<<<< HEAD
@RequestMapping("/auth") 
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager; 

    @Autowired
    private UsuarioService usuarioService; 

    @Autowired
    private PasswordEncoder encoder; 

    @Autowired
    private JwtUtil jwtUtils; 

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
        ResponseEntity<?> respuesta;

        if (usuarioService.existsByUsername(usuario.getUsername())) {
         
            respuesta = ResponseEntity.badRequest().body("Error: El nombre de usuario ya existe.");
        } else {
          
            usuario.setPassword(encoder.encode(usuario.getPassword()));
            
           
            if (usuario.getRole() != null && !usuario.getRole().startsWith("ROLE_")) {
                usuario.setRole("ROLE_" + usuario.getRole());
            }
            
            usuarioService.saveUsuario(usuario);
            respuesta = ResponseEntity.ok("Usuario registrado con éxito.");
        }

        return respuesta; 
    }

    @PostMapping("/login") 
    public ResponseEntity<?> authenticateUser(@RequestBody Usuario loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())); 

        String jwt = jwtUtils.generateJwtToken(authentication.getName()); 
=======
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: El nombre de usuario ya existe.");
        }

        usuario.setPassword(encoder.encode(usuario.getPassword()));
     
        if(!usuario.getRole().startsWith("ROLE_")) {
            usuario.setRole("ROLE_" + usuario.getRole());
        }
        
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado con éxito.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Usuario loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String jwt = jwtUtils.generateJwtToken(authentication.getName());
>>>>>>> 6768c0b (07-03-2026)

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return ResponseEntity.ok(response);
    }
}