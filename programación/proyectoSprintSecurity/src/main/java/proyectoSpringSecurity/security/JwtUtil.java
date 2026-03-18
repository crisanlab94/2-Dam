package proyectoSpringSecurity.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
    private final long jwtExpirationMs = 86400000; 

    public String generateJwtToken(String username) { 
        return Jwts.builder()
                .setSubject(username) 
                .setIssuedAt(new Date()) 
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) 
                .signWith(key)
                .compact(); 
    }

    public String getUsernameFromToken(String token) { 
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject(); 
    }

    public boolean validateJwtToken(String authToken) {
        boolean esValido = false; 
        try {
         
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken); 
            esValido = true; 
        } catch (JwtException e) {
           
            esValido = false;
        }
        return esValido; 
    }
    }
