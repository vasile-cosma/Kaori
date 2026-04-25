package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewProductDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    List<Product> findAllByAlphabeticalOrder();

    Optional<Product> findById(@PathVariable Integer id);

    List<Product> findByCategoryId(Integer id);

    Product save(Product item);

    void delete(Product item);

    boolean existsByCode(String code);

    boolean existsByCodeAndId(String code, Integer id);


    void createNew(NewProductDto newProductDto);

    Product update(Integer productId, NewProductDto newProductDto);

    String generateSlug(String url);
}
