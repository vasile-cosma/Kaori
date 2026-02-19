package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers.admin;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewBrandDto;
import org.springframework.ui.Model;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/brands")
public class AdminBrandController {
    private final BrandService brandService;

    public AdminBrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @ModelAttribute("brands")
    public List<Brand> getAllBrands() { return brandService.findAll();}

    @GetMapping("/")
    public String redirectAdminBrands() {
        return "redirect:/admin/brands";
    }

    @GetMapping
    public ModelAndView adminBrands(){
        return new ModelAndView("/admin/brands/brands");
    }

    @GetMapping("/new")
    public String newBrandGet(Model model){
        model.addAttribute("brand", new NewBrandDto());
        return "/admin/brands/new";
    }

    @PostMapping("/new")
    public String newBrandPost(@ModelAttribute("brand") NewBrandDto newBrandDto) {
        Brand newBrand = brandService.createNew(newBrandDto);
        brandService.save(newBrand);
        System.out.printf("Marca agregada:\n%s\n", newBrandDto);
        return "redirect:/admin/brands";
    }



}
