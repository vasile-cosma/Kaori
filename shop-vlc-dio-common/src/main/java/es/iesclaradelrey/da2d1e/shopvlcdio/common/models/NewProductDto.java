package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.validation.Ean13;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.validation.UniqueNewProductCode;
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
    @Ean13
    @UniqueNewProductCode
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
    @NotNull(message = "Debe rellenar este campo")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double price;
    @NotNull(message = "Debe rellenar este campo")
    @Min(value = 0, message = "El descuento no puede ser negativo")
    @Max(value = 100, message = "El descuento no puede ser superior al 100%")
    private Integer discount;
    private Integer stock = 100;
    @NotNull(message = "Debes seleccionar una marca")
    private Integer brandId;
    @NotEmpty(message = "Debes seleccionar al menos una categoría")
    private Set<Integer> categoriesIds;
}
