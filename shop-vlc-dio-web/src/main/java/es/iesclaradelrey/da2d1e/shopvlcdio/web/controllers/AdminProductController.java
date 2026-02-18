package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return new ModelAndView("admin-products");
    }

    @ModelAttribute("products")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/new")
    public String newProductGet(Model model){
        model.addAttribute("product", newProductDto)
    }

}
