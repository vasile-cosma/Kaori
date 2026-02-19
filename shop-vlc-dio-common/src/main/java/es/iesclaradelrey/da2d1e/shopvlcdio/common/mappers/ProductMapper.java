package es.iesclaradelrey.da2d1e.shopvlcdio.common.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewProductDto;

public class ProductMapper {
    private ProductMapper() {}

    public static NewProductDto map(Product product) {
        return NewProductDto.builder()
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDesc())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .build();
    }

    public static Product map(NewProductDto newProductDto) {
        return Product.builder()
                .code(newProductDto.getCode())
                .name(newProductDto.getName())
                .desc(newProductDto.getDescription())
                .price(newProductDto.getPrice())
                .discount(newProductDto.getDiscount())
                .build();
    }

}
