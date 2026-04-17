package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDto {
    private String productName;
    private Double unitPrice;
    private int discount;
    private Double discountedPrice;
    private int units;
    private Double totalPrice;
}
