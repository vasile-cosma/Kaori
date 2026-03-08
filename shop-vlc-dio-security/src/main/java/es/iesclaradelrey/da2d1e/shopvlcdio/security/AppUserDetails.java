package es.iesclaradelrey.da2d1e.shopvlcdio.security;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {
    private String username;
    private String password;

    public AppUserDetails(AppUser appUser) {
        this.username = appUser.getUsername();
        this.password = appUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO en un futuro
        return List.of();
    }

    @Override
    public @Nullable String getPassword() { return password; }

    @Override
    public String getUsername() { return username;}
}
