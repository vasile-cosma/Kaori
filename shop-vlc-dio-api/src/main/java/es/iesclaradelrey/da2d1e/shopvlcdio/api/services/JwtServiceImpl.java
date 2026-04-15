package es.iesclaradelrey.da2d1e.shopvlcdio.api.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.api.enums.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.access-token.expiration}")
    long accessTokenExpiration;

    @Value("${jwt.refresh-token.expiration}")
    long refreshTokenExpiration;

    @Override
    public String generateAccessToken(UserDetails user) {
        return Jwts.builder()
                .signWith(getKey())
                .subject(user.getUsername())
                .claim("type", TokenType.ACCESS)
                .issuedAt( new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .compact();
    }

    @Override
    public String generateRefreshToken(UserDetails user) {
        return Jwts.builder()
                .signWith(getKey())
                .subject(user.getUsername())
                .claim("type", TokenType.REFRESH)
                .issuedAt( new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }
    @Override
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
    }

    @Override
    public boolean isAccessTokenValid(String token, UserDetails user) {
        Claims claims = getClaims(token);
        String tokenType = claims.get("type", String.class);

        if (!tokenType.equals(TokenType.ACCESS)) return false;
        if (isTokenExpired(token)) return false;
        if (!extractUsername(token).equals(user.getUsername())) return false;

        return true;
    }

    @Override
    public boolean isRefreshTokenValid(String token, UserDetails user){
        Claims claims = getClaims(token);
        String tokenType = claims.get("type", String.class);

        if (!tokenType.equals(TokenType.REFRESH)) return false;
        if (isTokenExpired(token)) return false;
        if (!extractUsername(token).equals(user.getUsername())) return false;

        return true;
    }

    private SecretKey getKey(){
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
