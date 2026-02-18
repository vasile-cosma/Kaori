package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;


import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCategoryDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CategoryService{
    List<Category> findAll();

    Optional<Category> findById(@PathVariable Integer id);

    Category save(Category item);

    void delete(Category item);

    Category createNew(NewCategoryDto newCategoryDto);
}
