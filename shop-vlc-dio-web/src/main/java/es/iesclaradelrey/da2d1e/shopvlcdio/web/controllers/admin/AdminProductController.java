package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers.admin;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCategoryDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.CategoryMapper;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.ProductMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {


    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductMapper productMapper;

    public AdminProductController(ProductService productService, CategoryService categoryService, BrandService brandService, ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.productMapper = productMapper;
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

    @GetMapping({"/new", "/new/"})
    public String newProductGet(Model model){
        model.addAttribute("product",new  NewProductDto());
        return "/admin/products/new";
    }

    @PostMapping({"/new", "/new/"})
    public String newProductPost(@ModelAttribute("product") NewProductDto newProductDto, Model model){
        try {
            if (newProductDto.getName().isBlank() ||
                newProductDto.getCode().isBlank() ||
                newProductDto.getDescription().isBlank()){
                throw new Exception("Debe completar los campos obligatorios");
            }
            productService.createNew(newProductDto);
        } catch (Exception e) {
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));
            System.out.println(e.getClass());
            return "/admin/products/new";
        }
        return "redirect:/admin/products";
    }

    @GetMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteProductGet(@PathVariable Integer id, Model model){
        Optional<Product> product = productService.findById(id);
        try {
            model.addAttribute("product", product.orElseThrow());
        } catch (NoSuchElementException e){
            model.addAttribute("error", "ERROR: No se encuentra el producto");
        } catch (Exception e){
            model.addAttribute("error", String.format("ERROR: \n%s", e.getMessage()));
        }

        return "/admin/products/delete";
    }

    @PostMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteProductPost(@PathVariable Integer id, Model model){
        Optional<Product> product = productService.findById(id);
        try {
            product.ifPresent(productService::delete);
            return "redirect:/admin/products";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "ERROR: Violación de una restricción de integridad referencial.");
            model.addAttribute("brand", product.orElseThrow());
            return "/admin/products/delete";
        } catch (Exception e) {
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));
            model.addAttribute("brand", product.orElseThrow());
            return "/admin/products/delete";
        }
    }

    @GetMapping({"/edit/{id}", "/edit/{id}/"})
    public String editProductGet(@PathVariable Integer id, Model model) {
        try {
            Product product = productService.findById(id).orElseThrow();
            NewProductDto newProductDto = productMapper.map(product);
            model.addAttribute("product", newProductDto);
        } catch (Exception e){
            model.addAttribute("Error", String.format("ERROR: %s", e.getMessage()));

        }
        return "/admin/products/edit";
    }

    @PostMapping({"/edit/{id}", "/edit/{id}/"})
    public String editProductPost(@PathVariable Integer id, Model model, @ModelAttribute NewProductDto newProductDto) {
        try {
            productService.update(id, newProductDto);
            return "redirect:/admin/products";
        } catch (Exception e){
            model.addAttribute("Error", String.format("ERROR: %s", e.getMessage()));
            return "/admin/products/new";
        }
    }

}
