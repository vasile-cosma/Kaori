package es.iesclaradelrey.da2d1e.shopvlcdio.api.services.parsers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.BrandNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.CategoryNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.ResourceNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.BrandRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CategoryRepository;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ProductSaxHandler extends DefaultHandler {
    private final List<Product> products = new ArrayList<>();
    private Product currentProduct;
    private final StringBuilder currentValue = new StringBuilder();
    private Brand currentBrand;
    private Category currentCategory;

    // Repositorios para validar existencia
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductSaxHandler(BrandRepository brandRepo, CategoryRepository categoryRepo) {
        this.brandRepository = brandRepo;
        this.categoryRepository = categoryRepo;
    }

    public List<Product> getProducts() { return products; }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentValue.setLength(0); // Limpiar buffer
        if (qName.equalsIgnoreCase("product")) {
            currentProduct = new Product();
        } else if (qName.equalsIgnoreCase("brand")) {
            int brandId = Integer.parseInt(attributes.getValue("id"));
            currentBrand = brandRepository.findById(brandId)
                    .orElseThrow(BrandNotFoundException::new);
            currentProduct.setBrand(currentBrand);
        } else if (qName.equalsIgnoreCase("category")) {
            int catId = Integer.parseInt(attributes.getValue("id"));
            currentCategory = categoryRepository.findById(catId)
                    .orElseThrow(CategoryNotFoundException::new);
            currentProduct.getCategories().add(currentCategory);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (currentProduct == null) return;

        switch (qName.toLowerCase()) {
            case "code" -> currentProduct.setCode(currentValue.toString());
            case "name" -> {
                // Si estamos dentro de category o brand, no setear al producto
                if (currentCategory == null && currentBrand == null)
                    currentProduct.setName(currentValue.toString());
            }
            case "description" -> currentProduct.setDescription(currentValue.toString());
            case "price" -> currentProduct.setPrice(Double.parseDouble(currentValue.toString()));
            case "discount" -> currentProduct.setDiscount(Integer.parseInt(currentValue.toString()));
            case "stock" -> currentProduct.setStock(Integer.parseInt(currentValue.toString()));
            case "img" -> currentProduct.setImg(currentValue.toString());
            case "brand" -> currentBrand = null; // Salimos del nodo brand
            case "category" -> currentCategory = null; // Salimos del nodo category
            case "product" -> {
                products.add(currentProduct);
                currentProduct = null;
            }
        }
    }
}
