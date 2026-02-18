package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewProductDto {
    private String code;
    private String name;
    private String description;
    private Double price;
    private Integer discount;
}
