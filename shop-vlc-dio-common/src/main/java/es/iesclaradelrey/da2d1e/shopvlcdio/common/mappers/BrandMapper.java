package es.iesclaradelrey.da2d1e.shopvlcdio.common.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewBrandDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewProductDto;

public class BrandMapper {
    private BrandMapper() {}

    public static NewBrandDto map(Brand brand) {
        return NewBrandDto.builder()
                .name(brand.getName())
                .build();
    }

    public static Brand map(NewBrandDto newBrandDto) {
        return Brand.builder()
                .name(newBrandDto.getName())
                .build();
    }
}
