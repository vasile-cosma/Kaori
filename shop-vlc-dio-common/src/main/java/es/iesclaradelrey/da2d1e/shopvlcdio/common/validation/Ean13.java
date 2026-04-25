package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Ean13Validator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Ean13 {
    String message() default "El código EAN-13 no es válido (dígito de control incorrecto)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
