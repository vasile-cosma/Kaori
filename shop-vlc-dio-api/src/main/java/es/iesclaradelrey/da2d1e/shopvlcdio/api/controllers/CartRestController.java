package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRestController {
    @PostMapping
    public ResponseEntity<CartItemDto> addToCart()
}
