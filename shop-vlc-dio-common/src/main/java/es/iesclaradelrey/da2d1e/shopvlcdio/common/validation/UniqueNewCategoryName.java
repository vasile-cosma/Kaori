package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = UniqueNewCategoryNameValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNewCategoryName {
    String message() default "Esta categoría ya existe. Por favor, use otro nombre";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
