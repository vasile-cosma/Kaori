package es.iesclaradelrey.da2d1e.shopvlcdio.api.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateAccessToken(UserDetails user);
    String generateRefreshToken(UserDetails user);
    String extractUsername(String token);
    boolean isTokenExpired(String token);
    boolean isAccessTokenValid(String token, UserDetails user);
    boolean isRefreshTokenValid(String token, UserDetails user);
}
