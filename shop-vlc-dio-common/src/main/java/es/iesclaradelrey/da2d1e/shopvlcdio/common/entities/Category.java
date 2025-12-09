package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class Category implements Entity<Long> {
    private Long ID;
    private String name;
    private String description;
    private String image;
}
