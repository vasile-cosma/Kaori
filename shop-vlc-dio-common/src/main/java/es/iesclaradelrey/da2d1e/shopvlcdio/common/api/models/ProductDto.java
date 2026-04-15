package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private String img;
    private Double price;
    private Integer discount;
    private BrandDto brand;
    private Set<CategoryDto> categories;

}
