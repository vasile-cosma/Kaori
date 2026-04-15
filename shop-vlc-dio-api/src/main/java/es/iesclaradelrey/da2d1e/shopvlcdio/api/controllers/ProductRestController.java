package es.iesclaradelrey.da2d1e.shopvlcdio.api.controllers;


import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.mappers.ApiProductMapper;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.ProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private final ProductService productService;
    private final ApiProductMapper apiProductMapper;

    public ProductRestController(ProductService productService, ApiProductMapper apiProductMapper) {
        this.productService = productService;
        this.apiProductMapper = apiProductMapper;
    }

    @GetMapping
    ResponseEntity<List<ProductDto>> findAll(){
       /*  List<Product> products = productService.findAllByAlphabeticalOrder();
          for (Product product : products){
             System.out.println(product.getName());
         }
        return ResponseEntity.ok(products);*/


     return ResponseEntity.ok(productService.findAllByAlphabeticalOrder().stream().map(apiProductMapper::map).toList());

    }
}
