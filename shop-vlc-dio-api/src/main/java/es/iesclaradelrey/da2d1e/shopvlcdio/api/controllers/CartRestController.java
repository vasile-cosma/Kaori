package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ClientNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.CartResponseDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRestController {
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final AppUserRepository appUserRepository;

    public CartRestController(ProductRepository productRepository, CartService cartService, AppUserRepository appUserRepository) {
        this.productRepository = productRepository;
        this.cartService = cartService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> addToCart(Authentication authenticacion, @RequestBody NewCartItemDto newCartItemDto, Integer units) {


        UserDetails user = (UserDetails) authenticacion.getPrincipal();
        AppUser appUser = appUserRepository.findByUsername(user.getUsername()).orElseThrow(ClientNotFoundException::new);


        return ResponseEntity.ok(cartService.addCartItem(appUser.getId(), newCartItemDto));

    }

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(Authentication authentication) {
        AppUser appUser = appUserRepository.findByUsername(authentication.getName()).orElseThrow(ClientNotFoundException::new);
        return ResponseEntity.ok(cartService.getCartList(appUser.getId()));

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<CartResponseDto> removeFromCart(@PathVariable Integer productId) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<CartResponseDto> emptyCart() {
        return null;
    }




}
