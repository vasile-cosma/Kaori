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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


public class AppUserDetails implements UserDetails {
    private final AppUser appUser;
    private Integer id;
    //private boolean authenticated;

    public AppUserDetails(AppUser appUser) {
        this.appUser = appUser;
        id = appUser.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUser.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getId())).toList();
    }

    @Override
    public @Nullable String getPassword() { return appUser.getPassword(); }

    @Override
    public String getUsername() { return appUser.getUsername();}

    public Integer getId() { return appUser.getId(); }
}
