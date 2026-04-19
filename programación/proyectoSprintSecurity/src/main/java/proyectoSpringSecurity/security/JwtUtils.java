package proyectoSpringSecurity.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // Definimos una clave secreta para firmar los tokens.
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    // El token durará 24 horas 
    private final long jwtExpirationMs = 86400000;

    // MÉTODO: Generar el token (Se usa en el Login)
    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    // MÉTODO : Obtener el nombre de usuario del token
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // MÉTODO : Validar que el token es legal y no ha caducado
    public boolean validateJwtToken(String authToken) {
        boolean esValido = false;
        try {
            // Si esta línea no lanza excepción, el token es bueno
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            esValido = true;
        } catch (JwtException e) {
            // Si hay cualquier error (caducado, manipulado...), se queda en false
            esValido = false;
        }
        return esValido;
    }
}