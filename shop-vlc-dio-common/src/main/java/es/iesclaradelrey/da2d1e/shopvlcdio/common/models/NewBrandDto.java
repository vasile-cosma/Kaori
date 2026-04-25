package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.validation.UniqueNewBrandName;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewBrandDto {
    @NotBlank(message = "Este campo no puede estar vacio")
    @UniqueNewBrandName
    private String name;
}
