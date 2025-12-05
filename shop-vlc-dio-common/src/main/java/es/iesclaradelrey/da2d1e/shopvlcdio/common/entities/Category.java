package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String description;
    private String image;
}
