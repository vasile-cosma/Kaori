package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewUserDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.AppUserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser save(AppUser user) {
        return appUserRepository.save(user);
    }

    @Override
    public Optional<AppUser> findByUsername(String username) { return appUserRepository.findByUsername(username);}

    @Override
    public Optional<AppUser> findByEmail(String email) { return appUserRepository.findByEmail(email);}

    @Override
    public AppUser createNew(NewUserDto newUserDto){
        AppUser user = AppUserMapper.map(newUserDto);
        return appUserRepository.save(user);
    }
}
