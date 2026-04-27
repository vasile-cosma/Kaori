package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewUserDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, NewUserDto> {

    @Override
    public boolean isValid(NewUserDto dto, ConstraintValidatorContext context) {

        System.out.printf("Validando contraseñas: %s - %s.\n", dto.getPassword(), dto.getPasswordConfirmation());
        // Si se recibe null o vacío, se considera válido.
        // Eso se debe controla con @NotNull, @NotEmpty o @NotBlank
        if (dto.getPassword() == null || dto.getPasswordConfirmation() == null ||
                dto.getPassword().isBlank() || dto.getPasswordConfirmation().isBlank()) {
            return true; // otras validaciones se encargan
        }

        boolean valid = dto.getPassword().equals(dto.getPasswordConfirmation());

        if (!valid) {
            // Como la validación es a nivel de clase, si no se hace nada,
            // el error será "global", no se asociará a un atributo específico.

            // Se desactiva el error por defecto, para que no haya error global.
            context.disableDefaultConstraintViolation();

            // Añadir un error asociado a un atributo específico del DTO.
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("passwordConfirmation")
                    .addConstraintViolation();
        }

        return valid;
    }
}
