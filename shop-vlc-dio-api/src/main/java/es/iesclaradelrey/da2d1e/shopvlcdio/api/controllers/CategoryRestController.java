package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;


import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.CategoryDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    ResponseEntity<List<CategoryDto>> findAll(Sort sort) {
//        return ResponseEntity.notFound().build();
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
      //  return ResponseEntity.ok(categoryService.findAll(Sort.by(Sort.Direction.ASC, "name")).stream().map(categoryMapper::map).toList());
        return null;
    }
}
