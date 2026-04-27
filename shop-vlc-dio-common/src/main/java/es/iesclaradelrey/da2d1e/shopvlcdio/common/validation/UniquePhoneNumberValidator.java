package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.AppUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniquePhoneNumberValidator implements ConstraintValidator<UniqueNewUserName, String> {


    private final AppUserService appUserService;

    public UniquePhoneNumberValidator(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneNumber.isBlank()) return true;

        return  ! appUserService.existsByPhoneNumber(phoneNumber);
    }
}
