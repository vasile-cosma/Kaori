package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewProductDto {
    @NotBlank(message = "Debe rellenar este campo")
    @Size(min = 13, max = 13, message = "El código debe tener exactamente 13 caracteres")
    private String code;
    @NotBlank(message = "Debe rellenar este campo")
    @Size(min = 3, max = 200, message = "El nombre debe tener al menos 3 caracteres")
    private String name;
    @NotBlank(message = "Debe rellenar este campo")
    @Size.List({
            @Size(max = 4000, message = "La descripción no debe superar los 4000 caracteres"),
            @Size(min = 50, message = "La descripción debe ser mayor a 50 caracteres")
    })
    private String description;
    @NotBlank(message = "Debe rellenar este campo")

    private Double price;
    @NotBlank(message = "Debe rellenar este campo")
    private Integer discount;
    private Integer stock = 100;
    @NotBlank(message = "Debe rellenar este campo")
    private Integer brandId;
    @NotBlank(message = "Debe rellenar este campo")
    private Set<Integer> categoriesIds;
}
