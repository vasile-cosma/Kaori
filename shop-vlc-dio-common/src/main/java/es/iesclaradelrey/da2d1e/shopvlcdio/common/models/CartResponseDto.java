package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponseDto {
    private List<CartItemDto> items;
    private long distinctProducts;
    private long totalUnits;
}
