package es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.BrandRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final CategoryRepository  categoryRepository;
    private final BrandRepository brandRepository;
    public ProductMapper(CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    public NewProductDto map(Product product) {
        Integer brandId = product.getBrand().getId();
        Set<Integer> categoriesIds =  Optional.ofNullable(product.getCategories())
                .orElse(Collections.emptySet())
                .stream()
                .map(Category::getId)
                .collect(Collectors.toSet());

        return NewProductDto.builder()
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .stock(product.getStock())
                .brandId(brandId)
                .categoriesIds(categoriesIds)
                .build();
    }

    public Product map(NewProductDto newProductDto) {

        Brand brand = brandRepository.getReferenceById(newProductDto.getBrandId());
        Set<Category> categories = Optional.ofNullable(newProductDto.getCategoriesIds())
                .map(ids -> new HashSet<>(categoryRepository.findAllById(ids)))
                .orElse(new HashSet<>());
        //new HashSet<>(categoryRepository.findAllById(newProductDto.getCategoriesIds()));

        return Product.builder()
                .code(newProductDto.getCode())
                .name(newProductDto.getName())
                .description(newProductDto.getDescription())
                .price(newProductDto.getPrice())
                .discount(newProductDto.getDiscount())
                .stock(newProductDto.getStock())
                .brand(brand)
                .categories(categories)
                .build();
    }

}
