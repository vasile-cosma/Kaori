package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CartItemRepository;

import java.util.List;

public interface CartItemService{
    List<CartItem> findCartList(Integer AppUserid);

    void emptyCart(Integer id);

    void deleteItem(Integer id);



}
