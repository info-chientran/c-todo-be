package com.c_todo_be.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key signingKey;
    public final long expirationALong = 1000L * 60 * 60 * 24;

    public JwtUtil(@Value("${spring.secret.key}") String secretKey) {
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }


    public String generateToken(String username) {
        return Jwts.builder()
                .claims().subject(username).and()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationALong))
                .signWith(signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith((SecretKey) signingKey)
                .build()
                .parseSignedClaims(token);

        return claimsJws.getPayload().getSubject();
    }
}
