package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;


import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category item);
    void delete(Category item);
}
