package com.isa.airlinebookingbackend.service.implementation.auth;

import com.isa.airlinebookingbackend.security.ConfigValues;
import com.isa.airlinebookingbackend.service.auth.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

import static com.isa.airlinebookingbackend.constant.Constants.JWT_ISSUER;


@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceImplValues extends ConfigValues implements JwtService {
    private final ConfigValues jwtConfig;


    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token) {
        return (!isTokenExpired(token));
    }

    public String generateToken(String email, String role) {
        return createToken(email,role);
    }

    private String createToken(String email, String role) {

        return Jwts.builder()
                .claim("role", role)
                .issuer(JWT_ISSUER)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getAccessExpiration()))
                .signWith(getSignKey(), Jwts.SIG.HS256).compact();

    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
