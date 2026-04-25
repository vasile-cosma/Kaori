package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueNewBrandNameValidator implements ConstraintValidator<UniqueNewBrandName, String> {

    private final BrandService brandService;

    public UniqueNewBrandNameValidator(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (name.isBlank()) return true;

        return  ! brandService.existsByName(name);
    }
}
