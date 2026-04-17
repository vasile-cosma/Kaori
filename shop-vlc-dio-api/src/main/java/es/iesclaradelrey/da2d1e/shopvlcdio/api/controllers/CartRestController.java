package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ProductNotFoundException;
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
    public ResponseEntity<NewCartItemDto> addToCart(Integer id, Integer units) {

        // TODO
        /*UserDetails user = (UserDetails) authentication.getPrincipal();

        Optional<Product> product = productRepository.findById(id).orElseThrow(new ProductNotFoundException());*/
        return null;

    }

    @GetMapping
    public ResponseEntity<List<NewCartItemDto>> getCart() {
        return ResponseEntity.ok()
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
