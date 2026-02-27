package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewProductDto {
    private String code;
    private String name;
    private String description;
    private Double price;
    private Integer discount;
    private Integer brandId;
    private List<Integer> categoriesIds;
}
