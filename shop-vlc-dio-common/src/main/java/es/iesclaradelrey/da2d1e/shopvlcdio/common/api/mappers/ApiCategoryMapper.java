package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.CategoryDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ApiProductMapper.class)
public interface ApiCategoryMapper {
    CategoryDto map (Category category);
}
