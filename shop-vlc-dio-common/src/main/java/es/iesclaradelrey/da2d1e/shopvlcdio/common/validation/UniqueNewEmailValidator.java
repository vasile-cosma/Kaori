package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.AppUserService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueNewEmailValidator implements ConstraintValidator<UniqueNewEmail, String> {

    private final AppUserService appUserService;

    public UniqueNewEmailValidator(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email.isBlank()) return true;

        return  ! appUserService.existsByEmail(email);
    }
}
