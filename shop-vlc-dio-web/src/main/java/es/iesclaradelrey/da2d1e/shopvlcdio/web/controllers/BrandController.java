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
import java.util.Optional;

@Controller
public class BrandController {
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductService productService;

    public BrandController(CategoryService categoryService, BrandService brandService, ProductService productService) {
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.productService = productService;
    }

    @GetMapping({"/brands", "/brands/"})
    public ModelAndView index() {
        return new ModelAndView("brand-grid-3-cols");
    }

    @GetMapping("/brands/{id}")
    public ModelAndView detail(@PathVariable(name = "id") Integer id){
        ModelAndView mv = new ModelAndView("brand-list");
        List<Product> products = productService.findAll();
        products.sort((a, b) -> a.getName().compareTo(b.getName()));
        mv.addObject("products", products);

        Optional<Brand> brand = brandService.findById(id);
        mv.addObject("isPresent", brand.isPresent());
        if(brand.isPresent()){
            mv.addObject("brand", brand.orElseThrow());
        }

        return mv;
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @ModelAttribute("products")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @ModelAttribute("brands")
    public List<Brand> getAllBrands(){
        return brandService.findAll();
    }
}
