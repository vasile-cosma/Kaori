package es.iesclaradelrey.da2d1e.shopvlcdio.security;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class AppUserDetails implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    //private boolean authenticated;

    public AppUserDetails(AppUser appUser) {
        this.id = appUser.getId();
        this.username = appUser.getUsername();
        this.password = appUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );
    }

    @Override
    public @Nullable String getPassword() { return password; }

    @Override
    public String getUsername() { return username;}

    public Integer getId() { return id;}
}
