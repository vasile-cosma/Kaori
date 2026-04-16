package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ProductNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCartItemDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRestController {
    private final ProductRepository productRepository;

    public CartRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<NewCartItemDto> addToCart(Authentication authentication, Integer id, Integer units) {
        UserDetails user = (UserDetails) authentication.getPrincipal();

        Optional<Product> product = productRepository.findById(id).orElseThrow(new ProductNotFoundException());
        return null;

    }
}
