package br.com.opussoftware.desafio.config.security;

import br.com.opussoftware.desafio.model.Author;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${desafio.jwt.secret}")
    private String secret;

    public String generate(Authentication authentication) {
        Author author = (Author) authentication.getPrincipal();
        Instant iat = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant exp = iat.plus(30, ChronoUnit.MINUTES);

        return Jwts.builder()
                .setIssuer("Desafio Opus Software")
                .setSubject(author.getId().toString())
                .setIssuedAt(Date.from(iat))
                .setExpiration(Date.from(exp))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getAuthorId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());

    }
}
