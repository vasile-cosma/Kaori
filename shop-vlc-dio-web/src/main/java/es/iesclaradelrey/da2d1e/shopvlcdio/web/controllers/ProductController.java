package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
        mv.addObject("categories", categoryService.findAll());
        return mv;
    }
}
