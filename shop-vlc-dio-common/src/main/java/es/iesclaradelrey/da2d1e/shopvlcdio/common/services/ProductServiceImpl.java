package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.ProductMapper;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findAll() { return productRepository.findAll(); }

    @Override
    public Optional<Product> findById(Integer id) { return productRepository.findById(id); }

    @Override
    public Product save(Product item) { return productRepository.save(item);
    }

    @Override
    public void delete(Product item) {
        productRepository.delete(item);
    }

    @Override
    public void createNew(NewProductDto newProductDto) {

        Product product = productMapper.map(newProductDto);

        productRepository.save(product);
    }

}
