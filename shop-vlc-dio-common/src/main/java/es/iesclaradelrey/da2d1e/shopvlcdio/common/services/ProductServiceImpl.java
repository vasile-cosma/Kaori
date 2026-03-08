package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.BrandRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CategoryRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.ProductMapper;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
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

    @Override
    public Product update(Integer productId, NewProductDto newProductDto){
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No se encuentra el producto con id %d", productId)));
        Set<Integer> categoriesIds = newProductDto.getCategoriesIds();
        Brand brand = brandRepository
                .findById(newProductDto.getBrandId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("No se encuentra la marca con id %d", newProductDto.getBrandId())));

        if (!newProductDto.getCode().isBlank()){
            product.setCode(newProductDto.getCode());
        }
        if (!newProductDto.getName().isBlank()){
            product.setName(newProductDto.getName());
        }
        if (!newProductDto.getDescription().isBlank()){
            product.setDescription(newProductDto.getDescription());
        }
        if (!newProductDto.getPrice().isNaN()){
            product.setPrice(newProductDto.getPrice());
        }
        if (newProductDto.getDiscount() != null){
            product.setDiscount(newProductDto.getDiscount());
        }
        product.setBrand(brand);
        if (!categoriesIds.isEmpty()){
            Set<Category> categories = new HashSet<>();
            categoriesIds.forEach(x -> categories.add(categoryRepository.findById(x).orElseThrow(() -> new EntityNotFoundException(String.format("No se encuentra la categoría con id %d", x)))));
            product.setCategories(categories);
        } else {
            product.setCategories(new HashSet<Category>());
        }

        return productRepository.save(product);
    }

    @Override
    public String generateSlug(String url){
        String normalized = Normalizer.normalize(url, Normalizer.Form.NFD);
       return normalized
               .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
               .toLowerCase()
               .replaceAll(" ", "-")
               .replaceAll("[^a-z0-9-\\s]", "")
               .trim();

    }

}
