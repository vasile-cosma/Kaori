package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;

import java.util.List;

public interface CartService {
    List<CartItem> findCartList(Integer AppUserid);

    void emptyCart(Integer id);

    void deleteItem(Integer userId, Integer productId);

    CartItem createNew(NewCartItemDto newCartItemDto);



}
