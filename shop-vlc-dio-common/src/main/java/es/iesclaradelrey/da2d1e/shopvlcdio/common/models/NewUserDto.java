package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto {
    @NotBlank
    @Size(min = 4, max = 50)
    private String username;
    @NotBlank
    @Size(min = 4, max = 50)
    private String password;
    @NotBlank
    @Size(min = 8, max = 100)
    private String passwordConfirmation;
    @NotBlank
    @Size(min = 4, max = 50)
    private String name;
    @NotBlank
    @Size(min = 4, max = 50)
    private String lastName;
    @NotBlank
    @Email
    @Size(min = 6, max = 50)
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "El número de teléfono introducido no es válido")
    private String phoneNumber;
    @Past
    private LocalDate birthDate;
    @AssertTrue
    private boolean termsAccepted;
}
