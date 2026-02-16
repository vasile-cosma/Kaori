package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/products", "/products/"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("product-grid-6-cols");
        mv.addObject("products", productService.findAll());
        return mv;
    }

    @GetMapping({"/products/{id}/{*}"})
    public ModelAndView detail(@PathVariable Integer id){
        System.out.println("En el controlador");
        ModelAndView mv = new ModelAndView("single-product");
        Optional<Product> product = productService.findById(id);
        mv.addObject("isPresent", product.isPresent());
        if (product.isPresent()){
            mv.addObject("product", product.orElseThrow());
        }
        System.out.println("Antes de salir");
        return mv;
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }
}
