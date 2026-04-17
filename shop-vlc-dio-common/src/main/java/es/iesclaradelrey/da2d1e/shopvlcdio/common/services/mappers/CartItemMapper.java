package es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.CartItemDto;
import org.springframework.stereotype.Component;

@Component
public interface CartItemMapper {

     default CartItemDto toDetailDto(CartItem item) {
        if (item == null || item.getProduct() == null) {
            return null;
        }

        Product product = item.getProduct();

        return CartItemDto.builder()
                .productName(product.getName())
                .unitPrice(product.getPrice())
                .discount(product.getDiscount())
                .discountedPrice(product.calculatePrice())
                .units(item.getUnits())
                .totalPrice(item.getUnits() * product.calculatePrice())
                .build();
    }
}
