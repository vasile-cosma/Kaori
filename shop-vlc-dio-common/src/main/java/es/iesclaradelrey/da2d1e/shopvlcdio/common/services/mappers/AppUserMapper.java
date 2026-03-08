package es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers;


import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewUserDto;

import java.util.Objects;

public class AppUserMapper {

    public static NewUserDto map(AppUser appUser){

        return NewUserDto.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .name(appUser.getName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .phoneNumber(Objects.requireNonNullElse(appUser.getPhoneNumber(), null))
                .birthDate(Objects.requireNonNullElse(appUser.getBirthDate(), null))
                .build();
    }

    public static AppUser map(NewUserDto newUserDto){
        if (newUserDto.getBirthDate()==null){
            return AppUser.builder()
                    .username(newUserDto.getUsername())
                    .password(newUserDto.getPassword())
                    .name(newUserDto.getName())
                    .lastName(newUserDto.getLastName())
                    .email(newUserDto.getEmail())
                    .phoneNumber(Objects.requireNonNullElse(newUserDto.getPhoneNumber(), null))
                    .build();
        } else {
            return AppUser.builder()
                    .username(newUserDto.getUsername())
                    .password(newUserDto.getPassword())
                    .name(newUserDto.getName())
                    .lastName(newUserDto.getLastName())
                    .email(newUserDto.getEmail())
                    .phoneNumber(Objects.requireNonNullElse(newUserDto.getPhoneNumber(), null))
                    .birthDate(newUserDto.getBirthDate())
                    .build();
        }

    }
}
