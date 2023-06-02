package puj.quickparked.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import puj.quickparked.domain.Usuario;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtProvider {


    @Value("${quickparked.seguridad.semilla}")
    private String SECRET_KEY;

    @Value("${quickparked.seguridad.duracion}")
    private Long TOKEN_DURATION;

    Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String generateToken(UserDetails userDetails, Usuario usuario) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(usuario.getTipoUsuario().getRol());

        Map<String, Object> claims = new HashMap<>();
        claims.put("nombres", usuario.getNombres());
        claims.put("apellidos", usuario.getApellidos());
        claims.put("usuarioId", usuario.getId());
        claims.put("rolId", usuario.getTipoUsuario().getId());
        claims.put("rolNombre", usuario.getTipoUsuario().getRol());
        claims.put("username", usuario.getUsername());

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(userDetails.getUsername())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_DURATION * 1000))
                .signWith(SignatureAlgorithm.HS256,
                        SECRET_KEY.getBytes()).compact();

        return token;
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("token vacio");
        } catch (SignatureException e) {
            logger.error("fail en la firma");
        }
        return false;
    }
}
