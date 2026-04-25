package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import jakarta.validation.Payload;

public @interface UniqueNewBrandName {
    String message() default "Esta marca ya existe. Por favor, use otro nombre";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
