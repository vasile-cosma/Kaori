package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueNewCategoryNameValidator implements ConstraintValidator<UniqueNewCategoryName, String> {

    private final CategoryService categoryService;

    public UniqueNewCategoryNameValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (name.isBlank()) return true;

        return !categoryService.existsByName(name);
    }

}
