package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.CartResponseDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
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

    public CartRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> addToCart(Authentication authenticacion, Integer id, Integer units) {


        UserDetails user = (UserDetails) authenticacion.getPrincipal();


        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return null;

    }

    @GetMapping
    public ResponseEntity<List<NewCartItemDto>> getCart() {
        return null;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<NewCartItemDto> removeFromCart(@PathVariable Integer productId) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<NewCartItemDto> emptyCart() {
        return null;
    }




}
