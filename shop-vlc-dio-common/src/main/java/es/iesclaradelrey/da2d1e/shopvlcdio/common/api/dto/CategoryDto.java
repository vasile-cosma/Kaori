package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.dto;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Integer id;

    private String name;

    private String description;

    private String image;

    private Set<ProductDto> products = new HashSet<>();;
}
