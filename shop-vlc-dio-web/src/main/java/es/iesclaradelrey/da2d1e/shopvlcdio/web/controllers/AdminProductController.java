package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {


    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
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

    @PostMapping("/new")
    public String newProductPost(@ModelAttribute("product") NewProductDto newProductDto){
        Product newProduct = productService.createNew(newProductDto);
        productService.save(newProduct);
        System.out.printf("Producto agregado:\n%s\n", newProductDto);
        return "redirect:/admin/products";
    }

}
