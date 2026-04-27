package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.AppUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueNewUserNameValidator implements ConstraintValidator<UniqueNewUserName, String> {

    private final AppUserService appUserService;

    public UniqueNewUserNameValidator(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username.isBlank()) return true;

        return  ! appUserService.existsByUsername(username);
    }
}
