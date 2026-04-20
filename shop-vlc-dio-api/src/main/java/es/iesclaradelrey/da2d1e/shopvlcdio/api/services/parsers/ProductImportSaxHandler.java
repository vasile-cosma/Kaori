package es.iesclaradelrey.da2d1e.shopvlcdio.api.services.parsers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.BrandNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.exceptions.CategoryNotFoundException;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.BrandRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.CategoryRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProductImportSaxHandler extends DefaultHandler {

    @Getter
    private final List<Product> products = new ArrayList<>();

    private Product currentProduct;
    private Brand currentBrand;
    private Category currentCategory;

    private final StringBuilder currentTextBuilder = new StringBuilder();

    private boolean inProduct = false;
    private boolean inBrand = false;
    private boolean inCategory = false;

    private boolean inCode = false;
    private boolean inName = false;
    private boolean inDescription = false;
    private boolean inImg = false;
    private boolean inPrice = false;
    private boolean inDiscount = false;
    private boolean inStock = false;

    private boolean inBrandId = false;
    private boolean inCategoryId = false;

    private final BrandService brandService;
    private final CategoryService categoryService;

    public ProductImportSaxHandler(BrandService brandService,
                                   CategoryService categoryService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        switch (qName) {

            case "product":
                inProduct = true;
                currentProduct = new Product();
                currentProduct.setCategories(new HashSet<>());
                break;

            case "brand":
                inBrand = true;
                currentBrand = new Brand();
                break;

            case "category":
                inCategory = true;
                currentCategory = new Category();
                break;

            case "code":
                inCode = true;
                break;

            case "name":
                inName = true;
                break;

            case "description":
                inDescription = true;
                break;

            case "img":
                inImg = true;
                break;

            case "price":
                inPrice = true;
                break;

            case "discount":
                inDiscount = true;
                break;

            case "stock":
                inStock = true;
                break;

            case "id":
                if (inBrand) inBrandId = true;
                if (inCategory) inCategoryId = true;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (inCode || inName || inDescription || inImg ||
                inPrice || inDiscount || inStock ||
                inBrandId || inCategoryId) {

            currentTextBuilder.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (currentProduct == null && !qName.equalsIgnoreCase("product")) {
            currentTextBuilder.setLength(0);
            return;
        }

        String text = currentTextBuilder.toString().trim();

        switch (qName) {
            case "code":
                currentProduct.setCode(text);
                inCode = false;
                break;

            case "name":
                if (inBrand) {
                    currentBrand.setName(text);
                } else if (inCategory) {
                    currentCategory.setName(text);
                } else {
                    currentProduct.setName(text);
                }
                inName = false;
                break;

            case "description":
                currentProduct.setDescription(text);
                inDescription = false;
                break;

            case "img":
                currentProduct.setImg(text);
                inImg = false;
                break;

            case "price":
                if (!text.isEmpty()) currentProduct.setPrice(Double.valueOf(text));
                inPrice = false;
                break;

            case "discount":
                if (!text.isEmpty()) currentProduct.setDiscount(Integer.valueOf(text));
                inDiscount = false;
                break;

            case "stock":
                if (!text.isEmpty()) currentProduct.setStock(Integer.valueOf(text));
                inStock = false;
                break;

            case "id":
                if (inBrand) {
                    currentBrand.setId(Integer.valueOf(text));
                    inBrandId = false;
                } else if (inCategory) {
                    currentCategory.setId(Integer.valueOf(text));
                    inCategoryId = false;
                }
                break;

            case "brand":
                Brand brand = brandService.findById(currentBrand.getId())
                        .orElseThrow(BrandNotFoundException::new);
                currentProduct.setBrand(brand);
                inBrand = false;
                currentBrand = null;
                break;

            case "category":
                Category category = categoryService.findById(currentCategory.getId())
                        .orElseThrow(CategoryNotFoundException::new);
                currentProduct.getCategories().add(category);
                inCategory = false;
                currentCategory = null;
                break;

            case "product":
                products.add(currentProduct);
                currentProduct = null;
                inProduct = false;
                break;
        }

        currentTextBuilder.setLength(0);
    }
}