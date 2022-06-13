package br.com.paulotrevizan.score.auth.services;

import br.com.paulotrevizan.score.auth.domains.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JWTService {

    @Value("${security.jwt.expiration}")
    private String expiration = "360000";

    @Value("${security.jwt.signkey}")
    private String signKey = "bWl5YW1vdG8gbXVzYXNoaQ";

    public String gerarToken(Usuario usuario) {
        long exp = Long.parseLong(expiration);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(exp);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, signKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime localDateTime =  expirationDate
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            return !LocalDateTime.now().isAfter(localDateTime);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserName(String token) throws ExpiredJwtException{
        return (String) getClaims(token).getSubject();
    }

    public static void main(String[] args) {
        JWTService service = new JWTService();
    }

}