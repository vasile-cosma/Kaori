package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers.admin;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CategoryService categoryService;
    private final BrandService brandService;

    public AdminController(CategoryService categoryService, BrandService brandService) {
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){ return categoryService.findAll(); }

    @ModelAttribute("brands")
    public List<Brand> getAllBrands(){
        return brandService.findAll();
    }

    @GetMapping("/")
    public String redirectAdmin() {
        return "redirect:/admin";
    }

    @GetMapping
    public ModelAndView admin(){
        return new ModelAndView("/admin/admin-index");
    }
}
