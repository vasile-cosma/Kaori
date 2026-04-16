package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CartRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.CartItemMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public CartServiceImpl(CartRepository cartItemRepository, CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public List<CartItem> findCartList(Integer AppUserid) {
        return cartItemRepository.findByUser_Id(AppUserid);
    }

    @Override
    @Transactional
    public void emptyCart(Integer id) {
        cartItemRepository.deleteByUser_Id(id);
    }

    @Override
    public void deleteItem(Integer userId, Integer productId) {
        cartItemRepository.deleteCartItemByUser_Id_AndProduct_Id(userId, productId);
    }

    @Override
    public CartItem createNew(NewCartItemDto newCartItemDto) {
        CartItem cartItem = cartItemMapper.map(newCartItemDto);
        cartItemRepository.save(cartItem);
        return cartItem;
    }


}
