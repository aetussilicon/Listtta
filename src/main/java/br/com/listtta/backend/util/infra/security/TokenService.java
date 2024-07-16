package br.com.listtta.backend.util.infra.security;

import br.com.listtta.backend.model.entities.users.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Users users, boolean rememberMe) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("listtta")
                    .withSubject(users.getEmail())
                    .withClaim("puid", users.getPuid())
                    .withExpiresAt(generateExpirationDate(rememberMe))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro enquanto token era gerado" + e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("listtta")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return  "";
        }
    }

    public String extractPuidFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("listtta")
                    .build()
                    .verify(token)
                    .getClaim("puid").asString();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Erro ao verificar token: " + e);
        }
    }

    private Instant generateExpirationDate(boolean rememberMe) {
        if (rememberMe) {
            return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
        } else {
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        }
    }
}
