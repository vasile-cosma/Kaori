package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.BrandDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiBrandMapper {
    BrandDto map (Brand brand);
}
