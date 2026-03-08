package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public ProductController(ProductService productService, CategoryService categoryService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @GetMapping({"/products", "/products/"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/products/product-grid-6-cols");
        List<Product> products = productService.findAll();
        products.sort((a, b) -> a.getName().compareTo(b.getName()));

        mv.addObject("products", products);
        return mv;
    }

    @GetMapping({"/products/{id}/{slug}"})
    public ModelAndView detail(@PathVariable Integer id,
                                @PathVariable String slug) {

        ModelAndView mv = new ModelAndView("/products/single-product");

        Product product = productService.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado el producto"));
        mv.addObject("product", product);

        String correctSlug = productService.generateSlug(slug);
        if (!slug.equals(correctSlug)){
            return new ModelAndView("redirect:/products/" + id + "/" + correctSlug);
        }




        return mv;
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }


    @ModelAttribute("brands")
    public List<Brand> getAllBrands(){
        return brandService.findAll();
    }
}
