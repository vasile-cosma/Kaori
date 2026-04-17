package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ClientNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.InsufficientStockException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CartRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.CartItemMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final AppUserRepository appUserRepository;
    private final CartItemMapper cartItemMapper;

    public CartServiceImpl(CartRepository cartItemRepository, ProductRepository productRepository, AppUserRepository appUserRepository, CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.appUserRepository = appUserRepository;
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

    @Transactional
    @Override
    public CartItem addOrUpdateItem(Integer userId, NewCartItemDto newCartItemDto) {

        AppUser appUser = appUserRepository.findById(userId).orElseThrow(ClientNotFoundException::new);
        Product product = productRepository.findById(newCartItemDto.getProduct().getId()).orElseThrow(ProductNotFoundException::new);
        Optional<CartItem> originalCartItem = cartItemRepository.findByUser_IdAndProduct_Id(userId, product.getId());

        int stock = product.getStock();
        if (stock < 1) throw new InsufficientStockException();

        if (originalCartItem.isPresent()) {
            CartItem cartItem = originalCartItem.get();
            cartItem.setUnits(newCartItemDto.getUnits() + cartItem.getUnits());

            if (stock < cartItem.getUnits()) throw new InsufficientStockException();

            cartItem.setUpdatedAt(LocalDateTime.now());
            cartItemRepository.save(cartItem);

        } else {
            if (stock < newCartItemDto.getUnits()) throw new InsufficientStockException();

            CartItem cartItem = cartItemMapper.map(newCartItemDto);
            cartItem.setUser(appUser);

            cartItem.setUpdatedAt(LocalDateTime.now());
            cartItemRepository.save(cartItem);
        }





        return null;


    }




}
