package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers.admin;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {


    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public AdminProductController(ProductService productService, CategoryService categoryService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }


    @ModelAttribute("brands")
    public List<Brand> getAllBrands(){
        return brandService.findAll();
    }

    @GetMapping("/")
    public String redirectAdminProducts(){
        return "redirect:/admin/products";
    }

    @GetMapping
    public ModelAndView adminProducts(){
        return new ModelAndView("admin/products/products");
    }

    @ModelAttribute("products")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/new")
    public String newProductGet(Model model){
        model.addAttribute("product",new  NewProductDto());
        return "/admin/products/new";
    }

    @PostMapping({"/new", "/edit"})
    public String newProductPost(@ModelAttribute("product") NewProductDto newProductDto, Model model){
        try {
            productService.createNew(newProductDto);
        } catch (Exception e) {
            model.addAttribute("error", String.format("Se ha producido un error: %s", e.getMessage()));
            return "admin/products/new";
        }
        return "redirect:/admin/products";
    }

    @GetMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteProductGet(@PathVariable Integer id, Model model){
        Optional<Product> product = productService.findById(id);
        model.addAttribute("product", product.orElseThrow());

        return "/admin/products/delete";
    }

    @PostMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteProductPost(@PathVariable Integer id, Model model){
        Optional<Product> product = productService.findById(id);
        try {
            product.ifPresent(productService::delete);
            return "redirect:/admin/products";
        } catch (Exception e){
            model.addAttribute("error", String.format("ERROR: \n%s", e.getMessage()));
            model.addAttribute("product", product.orElseThrow());
            return "/admin/products/delete";
        }
    }

}
