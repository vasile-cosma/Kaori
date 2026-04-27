package es.iesclaradelrey.da2d1e.shopvlcdio.common.validation;

import jakarta.validation.Payload;

public @interface UniqueNewEmail {
    String message() default "Este email ya está siendo utilizado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
