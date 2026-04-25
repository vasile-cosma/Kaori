package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueNewProductCodeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNewProductCode {
    String message() default "Este código ya existe. Por favor, use otro código";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
