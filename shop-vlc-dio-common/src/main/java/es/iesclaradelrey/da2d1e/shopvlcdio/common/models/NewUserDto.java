package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PasswordsMatch
public class NewUserDto {
    @NotBlank(message = "Debe introducir un nombre de usuario")
    @UniqueNewUserName
    @Size.List({
            @Size(max = 50, message = "El apodo no puede ser tan largo"),
            @Size(min = 3, message = "El apodo debe ser mayor a 3 caracteres")
    })
    private String username;
    @NotBlank(message = "Debe introducir una contraseña")
    @Size.List({
            @Size(max = 100, message = "La contraseña no puede ser tan larga"),
            @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    })
    private String password;
    @NotBlank(message = "Debe confirmar la contraseña")
    @Size.List({
            @Size(max = 100, message = "La contraseña no puede ser tan larga"),
            @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    })
    private String passwordConfirmation;
    @NotNull(message = "Debe indicar su nombre")
    @Size.List({
            @Size(max = 50, message = "Este campo debe ser más corto"),
            @Size(min = 3, message = "Este campo debe tener al menos 3 caracteres")
    })
    private String name;
    @NotNull(message = "Debe indicar su(s) apellido(s)")
    @Size.List({
            @Size(max = 50, message = "Este campo debe ser más corto"),
            @Size(min = 3, message = "Este campo debe tener al menos 3 caracteres")
    })
    private String lastName;
    @NotNull(message = "Debe introducir un email")
    @Email(message = "El formato de este campo no es el correcto")
    @UniqueNewEmail
    @Size.List({
            @Size(max = 50, message = "Este campo debe ser más corto"),
            @Size(min = 6, message = "Este campo debe tener al menos 6 caracteres")
    })
    private String email;
    @UniquePhoneNumber
    @Pattern(regexp = "^$|^\\+?[0-9]{10,15}$", message = "El número de teléfono introducido no es válido")
    private String phoneNumber;
    @Past(message = "Debe introducir una fecha de nacimiento válida")
    @MinAge(value = 14)
    private LocalDate birthDate;
    @AssertTrue(message = "Debe aceptar los términos y condiciones")
    private boolean termsAccepted;
}
