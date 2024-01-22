package ru.taxiservice.authservice.security.jwtconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private Long jwtTokenLifeTime;

    public String generateToken(String username, Collection<? extends GrantedAuthority> roles) {
        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("name", username)
                .claim("role", roles)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(Instant.ofEpochSecond(System.currentTimeMillis() + jwtTokenLifeTime)))
                .signWith(generateKey())
                .compact();
        return jwtToken;
    }

    public String validateTokenAndRetrieveClaim(String token) {

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token);

        return jwt.getBody().getSubject();
    }

    private Key generateKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
    }

}
