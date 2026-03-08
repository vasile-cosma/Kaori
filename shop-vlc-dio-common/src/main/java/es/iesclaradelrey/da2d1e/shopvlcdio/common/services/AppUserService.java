package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewUserDto;

import java.util.Optional;

public interface AppUserService {
    AppUser save(AppUser user);

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    AppUser createNew(NewUserDto newUserDto);
}
