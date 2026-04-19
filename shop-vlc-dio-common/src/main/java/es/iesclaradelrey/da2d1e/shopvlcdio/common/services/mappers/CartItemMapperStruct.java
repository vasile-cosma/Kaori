package es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapperStruct {
    CartItem map(NewCartItemDto newCartItemDto);
}

