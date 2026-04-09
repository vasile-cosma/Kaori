package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.ProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiProductMapper {
    ProductDto map (Product product);
}
