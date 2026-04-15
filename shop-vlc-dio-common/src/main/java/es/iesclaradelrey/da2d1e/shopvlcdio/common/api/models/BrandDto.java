package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandDto {
    private Integer id;
    private String name;
    private String image;
}
