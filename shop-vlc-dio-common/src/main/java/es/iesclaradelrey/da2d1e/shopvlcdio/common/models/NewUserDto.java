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
    @NotBlank(message = "Debe introducir un nombre de usuario")
    @Size(min = 4, max = 50)
    private String username;
    @NotBlank(message = "Debe introducir una contraseña")
    @Size(min = 4, max = 50)
    private String password;
    @NotBlank(message = "Debe confirmar la contraseña")
    @Size(min = 8, max = 100)
    private String passwordConfirmation;
    @NotBlank(message = "Debe indicar su nombre")
    @Size(min = 4, max = 50)
    private String name;
    @NotBlank(message = "Debe indicar su(s) apellido(s)")
    @Size(min = 4, max = 50)
    private String lastName;
    @NotBlank(message = "Debe introducir un email")
    @Email
    @Size(min = 6, max = 50)
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "El número de teléfono introducido no es válido")
    private String phoneNumber;
    @Past
    private LocalDate birthDate;
    @AssertTrue(message = "Debe aceptar los términos y condiicones")
    private boolean termsAccepted;
}
