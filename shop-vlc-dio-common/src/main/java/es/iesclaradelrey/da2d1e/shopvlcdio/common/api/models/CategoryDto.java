package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models;

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

}
