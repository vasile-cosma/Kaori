package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers.admin;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewBrandDto;
import org.springframework.ui.Model;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
    public String newBrandPost(@ModelAttribute("brand") NewBrandDto newBrandDto, Model model) {
        try {
            brandService.createNew(newBrandDto);
        } catch (Exception e) {
            model.addAttribute("error", String.format("Se ha producido un error: %s", e.getMessage()));
            return "admin/brands/new";
        }
        return "redirect:/admin/brands";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteBrandGet(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("admin/brands/delete");
        Optional<Brand> brand = brandService.findById(id);
        mv.addObject("isPresent", brand.isPresent());
        if (brand.isPresent()){
            mv.addObject("brand", brand.orElseThrow());
            System.out.println("He añadido la marca: " + brand);
        }
        return mv;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteBrandPost(@PathVariable Integer id){
        ModelAndView mv = new ModelAndView("admin/brands/delete");
        try {
            Optional<Brand> brand = brandService.findById(id);
            if (brand.isPresent()){
                brandService.delete(brand.get());
                System.out.println("He borrado la marca");
            } else {
                mv.addObject("error",true);
                mv.addObject("errorMessage", "ERROR: No se encuentra la marca");
                return mv;
            }
            return new ModelAndView("redirect:/admin/brands");
        } catch(Exception e) {
            mv.addObject("error",true);
            mv.addObject("errorMessage", e.getMessage());
            brandService.findById(id).ifPresent(brand -> mv.addObject("brand", brand));
            return mv;
        }
    }

}
