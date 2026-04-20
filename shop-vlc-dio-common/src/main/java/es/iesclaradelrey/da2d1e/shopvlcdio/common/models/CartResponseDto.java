package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDto {
    private List<CartItemDto> items;
    private long distinctProducts;
    private long totalUnits;
}
