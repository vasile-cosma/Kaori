package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ClientNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.InsufficientStockException;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final AppUserRepository appUserRepository;
    private final CartItemMapper cartItemMapper;

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
    public CartResponseDto emptyCart(Integer userId) {
        cartItemRepository.deleteByUser_Id(userId);
        return getCartList(userId);
    }

    @Override
    @Transactional
    public CartResponseDto deleteItem(Integer userId, Integer productId) {
        AppUser appUser = appUserRepository.findById(userId).orElseThrow(ClientNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        List<CartItem> cart = cartItemRepository.findByUser_Id(userId);

        cartItemRepository.deleteCartItemByUser_Id_AndProduct_Id(userId, productId);

        return getCartList(userId);

    }

    @Transactional
    @Override
    public CartResponseDto addCartItem(Integer userId, NewCartItemDto newCartItemDto) {

        AppUser appUser = appUserRepository.findById(userId).orElseThrow(ClientNotFoundException::new);
        Product product = productRepository.findById(newCartItemDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        Optional<CartItem> originalCartItem = cartItemRepository.findByUser_IdAndProduct_Id(userId, product.getId());

        int stock = product.getStock();
        if (stock < 1) throw new InsufficientStockException();

        if (originalCartItem.isPresent()) {
            CartItem cartItem = originalCartItem.get();
            cartItem.setUnits(newCartItemDto.getUnits() + cartItem.getUnits());

            if (stock < cartItem.getUnits()) throw new InsufficientStockException();

            cartItem.setUpdatedAt(LocalDateTime.now());
            cartItemRepository.save(cartItem);
            System.out.printf("GUARDADO: %s", cartItem);

        } else {
            if (stock < newCartItemDto.getUnits()) throw new InsufficientStockException();
            CartItem cartItem = CartItem.builder()
                    .product(product)
                    .user(appUser)
                    .units(newCartItemDto.getUnits())
                    .updatedAt(LocalDateTime.now())
                    .build();


            cartItemRepository.save(cartItem);
            System.out.printf("GUARDADO: %s", cartItem);
        }

        return getCartList(userId);

    }

    @Override
    public CartResponseDto getCartList(Integer userId) {
        List<CartItem> items =  cartItemRepository.findByUser_Id(userId);
        List<CartItemDto> itemsDetails = items.stream().map(cartItemMapper::toDetailDto).toList();


        return CartResponseDto.builder()
                .items(itemsDetails)
                .distinctProducts(cartItemRepository.countDistinctProductsByUser(userId))
                .totalUnits(cartItemRepository.sumTotalUnitsByUser(userId))
                .build();
    }







}
