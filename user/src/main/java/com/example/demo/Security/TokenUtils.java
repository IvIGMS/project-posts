package com.example.demo.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    private final String ACCESS_TOKEN_SECRET = "mNajgf39sNvfDSjdnglo3129dfnLAS21nSlkl59";
    private final Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    // Enviar data adicional
    public String createToken(long id, String email, String name, String surname, String username, Date birthdate){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("email", email);
        extra.put("id", id);
        extra.put("name", name);
        extra.put("lastname", surname);
        extra.put("username", username);
        extra.put("birthdate", birthdate.getTime());

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claim = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claim.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e){
            return null;
        }
    }

    public TokenUtils() {
    }
}
