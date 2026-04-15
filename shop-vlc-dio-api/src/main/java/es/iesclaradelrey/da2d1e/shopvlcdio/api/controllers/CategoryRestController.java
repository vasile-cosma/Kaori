package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;


import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.mappers.ApiCategoryMapper;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.mappers.ApiProductMapper;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.CategoryDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.ProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestController {
    private final CategoryService categoryService;
    private final ApiCategoryMapper apiCategoryMapper;
    private final ProductService productService;
    private final ApiProductMapper apiProductMapper;

    public CategoryRestController(CategoryService categoryService, ApiCategoryMapper apiCategoryMapper, ProductService productService, ApiProductMapper apiProductMapper) {
        this.categoryService = categoryService;
        this.apiCategoryMapper = apiCategoryMapper;
        this.productService = productService;
        this.apiProductMapper = apiProductMapper;
    }

    @GetMapping
    ResponseEntity<List<CategoryDto>> findAll(){
        return ResponseEntity.ok(categoryService.findAllByAlphabeticalOrder().stream().map(apiCategoryMapper::map).toList());
    }

    @GetMapping("/{categoryId}/products")
    ResponseEntity<List<ProductDto>> findByCategory(@PathVariable("categoryId") Integer categoryId){

        return ResponseEntity.ok(productService.findByCategoryId(categoryId).stream().map(apiProductMapper::map).toList());
    }
}