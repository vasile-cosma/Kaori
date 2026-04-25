package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Ean13Validator implements ConstraintValidator<Ean13, String> {

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {

        if (code.isBlank()) return true;

        code = code.trim();

        if (!code.matches("\\d{13}")) {
            return false;
        }

        return validateCode(code);


    }

    private boolean validateCode(String code) {
        int sum = 0, digit;
        for (int i = 0; i < 12; i++) {
            digit = Character.getNumericValue(code.charAt(i));

            sum+= (i % 2 == 0) ? digit : digit * 3;
        }

        return (10 - (sum % 10)) % 10 == Character.getNumericValue(code.charAt(12));
    }
}
