package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.CartResponseDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartService {
    List<CartItem> findCartList(Integer AppUserid);

    CartResponseDto emptyCart(Integer id);

    CartResponseDto deleteItem(Integer userId, Integer productId);

    @Transactional
    CartResponseDto addCartItem(Integer userId, NewCartItemDto newCartItemDto);

    CartResponseDto getCartList(Integer userId);
}
