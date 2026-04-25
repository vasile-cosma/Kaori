package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueNewProductCodeValidator implements ConstraintValidator<UniqueNewProductCode, String> {

    private final ProductService productService;

    public UniqueNewProductCodeValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        if (code.isBlank()) return true;

        return  ! productService.existsByCode(code);
    }
}
