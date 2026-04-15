package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.ProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ApiCategoryMapper.class, ApiBrandMapper.class})
public interface ApiProductMapper {
    ProductDto map (Product product);
}
