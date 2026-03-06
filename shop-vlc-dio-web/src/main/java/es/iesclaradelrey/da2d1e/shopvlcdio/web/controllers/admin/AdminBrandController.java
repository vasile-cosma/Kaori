package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers.admin;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewBrandDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.BrandMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;
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
    public String adminBrands(){
        return "/admin/brands/brands";
    }

    @GetMapping({"/new", "/new/"})
    public String newBrandGet(Model model){
        model.addAttribute("brand", new NewBrandDto());
        return "/admin/brands/new";
    }

    @PostMapping({"/new", "/new/"})
    public String newBrandPost(@ModelAttribute("brand") NewBrandDto newBrandDto, Model model) {
        try {
            if (newBrandDto.getName().isBlank()){
               throw new Exception("Debe completar los campos obligatorios");
            }
            brandService.createNew(newBrandDto);
        } catch (Exception e) {
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));
            return "/admin/brands/new";
        }
        return "redirect:/admin/brands";
    }

    @GetMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteBrandGet(@PathVariable Integer id, Model model) {
        Optional<Brand> brand = brandService.findById(id);
        try {
            model.addAttribute("brand", brand.orElseThrow());
        } catch (NoSuchElementException e){
            model.addAttribute("error", "ERROR: No se encuentra la marca");
        } catch (Exception e){
            model.addAttribute("error", String.format("ERROR: \n%s", e.getMessage()));
        }

        return "/admin/brands/delete";
    }

    @PostMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteBrandPost(@PathVariable Integer id, Model model){
        Optional<Brand> brand = brandService.findById(id);
        try {
            brand.ifPresent(brandService::delete);
            return "redirect:/admin/brands";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "ERROR: Violación de una restricción de integridad referencial.");
            model.addAttribute("brand", brand.orElseThrow());
            return "/admin/brands/delete";
        } catch (Exception e) {
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));
            model.addAttribute("brand", brand.orElseThrow());
            return "/admin/brands/delete";
        }
    }

    @GetMapping({"/edit/{id}", "/edit/{id}/"})
    public String editBrandGet(@PathVariable Integer id, Model model) {
        try {
            Brand brand = brandService.findById(id).orElseThrow();
            NewBrandDto newBrandDto = BrandMapper.map(brand);
            model.addAttribute("brand", newBrandDto);
        } catch (Exception e){
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));

        }
        return "/admin/brands/edit";
    }

    @PostMapping({"/edit/{id}", "/edit/{id}/"})
    public String editBrandPost(@PathVariable Integer id, Model model, @ModelAttribute NewBrandDto newBrandDto) {
        try {
            brandService.update(id, newBrandDto);
            return "redirect:/admin/brands";
        } catch (Exception e){
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));
            model.addAttribute("brand", newBrandDto);
            return "/admin/brands/edit";
        }
    }

}
