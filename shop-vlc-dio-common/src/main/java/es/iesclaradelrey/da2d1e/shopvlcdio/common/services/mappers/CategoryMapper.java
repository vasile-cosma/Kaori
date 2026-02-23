package es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewBrandDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCategoryDto;

public class CategoryMapper {
    private CategoryMapper() {}

    public static NewCategoryDto map(Category category) {
        return NewCategoryDto.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static Category map(NewCategoryDto newCategoryDto) {
        return Category.builder()
                .name(newCategoryDto.getName())
                .description(newCategoryDto.getDescription())
                .build();
    }
}
