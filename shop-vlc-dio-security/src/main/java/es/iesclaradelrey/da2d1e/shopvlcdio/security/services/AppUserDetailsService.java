package es.iesclaradelrey.da2d1e.shopvlcdio.security.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.AppUserService;
import es.iesclaradelrey.da2d1e.shopvlcdio.security.AppUserDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {


    private final AppUserService appUserService;

    public AppUserDetailsService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AppUserDetails(appUserService.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado")));
    }
}
