package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.CartItemDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.CartResponseDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CartRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.CartItemMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Transactional
    public void deleteItem(Integer userId, Integer productId) {
        cartItemRepository.deleteCartItemByUser_Id_AndProduct_Id(userId, productId);
    }

    @Override
    public CartItem addOrUpdateItem(NewCartItemDto newCartItemDto) {
        return null;
    }


    @Override
    public CartItem addProduct(Product product) {
        return null;
    }

    @Override
    public CartResponseDto getCartList(Integer userid) {
        List<CartItem> items =  cartItemRepository.findByUser_Id(userid);
        List<CartItemDto> itemsDetails = items.stream().map(cartItemMapper::toDetailDto).toList();


        return CartResponseDto.builder()
                .items(itemsDetails)
                .distinctProducts(cartItemRepository.countDistinctProductsByUser(userid))
                .totalUnits(cartItemRepository.sumTotalUnitsByUser(userid))
                .build();
    }







}
