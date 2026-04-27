package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import jakarta.validation.Payload;

public @interface UniquePhoneNumber {
    String message() default "Este número ya está siendo utilizado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
