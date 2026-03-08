package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private boolean termsAccepted;
}
