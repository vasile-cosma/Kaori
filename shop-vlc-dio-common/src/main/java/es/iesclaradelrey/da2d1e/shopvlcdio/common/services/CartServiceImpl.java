package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CartRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.CartItemMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final ProductRepository productRepository;
    private final AppUserRepository appUserRepository;

    public CartServiceImpl(CartRepository cartItemRepository, CartItemMapper cartItemMapper, ProductRepository productRepository, AppUserRepository appUserRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
        this.productRepository = productRepository;
        this.appUserRepository = appUserRepository;
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

    @Override
    public CartItem addProduct(Integer userId, NewCartItemDto newCartItemDto) {
        Product product = productRepository.findById(newCartItemDto.getProduct().getId()).orElseThrow();
        AppUser user = appUserRepository.findById(userId).orElseThrow();



        return null;
    }




}
