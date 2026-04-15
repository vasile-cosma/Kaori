
package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;


import es.iesclaradelrey.da2d1e.shopvlcdio.api.services.JwtService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.LoginRequestDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.LoginResponseDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.RefreshRequestDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.security.services.AppUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AppUserDetailsService appUserDetailsService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtService jwtService, AppUserDetailsService appUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.appUserDetailsService = appUserDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String accessToken = jwtService.generateAccessToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);

            return ResponseEntity.ok(LoginResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build());
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /* NOT WORKING:

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(@RequestBody RefreshRequestDto refreshDto){
        String token = refreshDto.getRefreshToken();

        String username = jwtService.extractUsername(token);

        try {
            UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);

            if(jwtService.isRefreshTokenValid(refreshDto.getRefreshToken(), userDetails)){
                String accessToken = jwtService.generateAccessToken(userDetails);

                return ResponseEntity.ok(LoginResponseDto.builder()
                        .accessToken(accessToken)
                        .build());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        } catch (UsernameNotFoundException e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }*/
}
