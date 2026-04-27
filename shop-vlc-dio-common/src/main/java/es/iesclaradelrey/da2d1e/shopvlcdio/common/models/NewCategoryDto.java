package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.validation.UniqueNewCategoryName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCategoryDto {
    @NotEmpty(message = "Este campo no puede estar vacio")
    @UniqueNewCategoryName
    private String name;
    @NotEmpty(message = "Este campo no puede estar vacio")
    @Size.List({
            @Size(max = 4000, message = "La descripción no debe superar los 4000 caracteres"),
            @Size(min = 50, message = "La descripción debe ser mayor a 50 caracteres")
    })
    private String description;
}
