package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.CategoryMapper;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCategoryDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category item) {
        return categoryRepository.save(item);
    }

    @Override
    public void delete(Category item) {
        categoryRepository.delete(item);
    }

    @Override
    public Category createNew(NewCategoryDto newCategoryDto) {
        Category category = CategoryMapper.map(newCategoryDto);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Integer categoryId, NewCategoryDto newnewCategoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException(String.format("No se encuentra la marcan con id %d", categoryId)));
        if (!newnewCategoryDto.getName().isEmpty()){
            category.setName(newnewCategoryDto.getName());
        }
        if (!newnewCategoryDto.getDescription().isEmpty()){
            category.setDescription(newnewCategoryDto.getDescription());
        }
        return categoryRepository.save(category);
    }
}
