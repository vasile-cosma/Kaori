package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewUserDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface AppUserService {
    AppUser save(AppUser user);

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findById(@PathVariable Integer id);

    Optional<AppUser> findByEmail(String email);

    AppUser createNew(NewUserDto newUserDto);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
