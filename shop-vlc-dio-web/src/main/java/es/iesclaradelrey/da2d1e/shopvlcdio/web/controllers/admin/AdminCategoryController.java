package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers.admin;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewBrandDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCategoryDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryServiceImpl;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.BrandMapper;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.CategoryMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private final CategoryServiceImpl categoryService;

    public AdminCategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/")
    public String redirectAdminCategories(){
        return "redirect:/admin/categories";
    }

    @GetMapping
    public String adminCategories(){
        return "/admin/categories/categories";
    }

    @GetMapping({"/new", "/new/"})
    public String newCategoryGet(Model model){
        model.addAttribute("category", new NewCategoryDto());
        return "/admin/categories/new";
    }

    @PostMapping({"/new", "/new/"})
    public String newCategoryPost(@ModelAttribute("category") NewCategoryDto newCategoryDto, Model model){
        try {
            if (newCategoryDto.getName().isBlank() || newCategoryDto.getDescription().isBlank()){
                throw new Exception("Debe completar los campos obligatorios");
            }
            categoryService.createNew(newCategoryDto);
        } catch (Exception e) {
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));
            return "/admin/categories/new";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteCategoryGet(@PathVariable Integer id, Model model){
        Optional<Category> category = categoryService.findById(id);
        try {
            model.addAttribute("category", category.orElseThrow());
        } catch (NoSuchElementException e){
            model.addAttribute("error", "ERROR: No se encuentra la categoría");
        } catch (Exception e){
            model.addAttribute("error", String.format("ERROR: \n%s", e.getMessage()));
        }

        return "/admin/categories/delete";
    }

    @PostMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteCategoryPost(@PathVariable Integer id, Model model){
        Optional<Category> category = categoryService.findById(id);
        try {
            category.ifPresent(categoryService::delete);
            return "redirect:/admin/categories";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "ERROR: Violación de una restricción de integridad referencial.");
            model.addAttribute("category", category.orElseThrow());
            return "/admin/categories/delete";
        } catch (Exception e) {
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));
            model.addAttribute("category", category.orElseThrow());
            return "/admin/categories/delete";
        }
    }

    @GetMapping({"/edit/{id}", "/edit/{id}/"})
    public String editCategoryGet(@PathVariable Integer id, Model model) {
        try {
            Category category = categoryService.findById(id).orElseThrow();
            NewCategoryDto newCategoryDto = CategoryMapper.map(category);
            model.addAttribute("category", newCategoryDto);
            model.addAttribute("id", id);
        } catch (Exception e){
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));

        }
        return "/admin/categories/edit";
    }

    @PostMapping({"/edit/{id}", "/edit/{id}/"})
    public String editCategoryPost(@PathVariable Integer id, Model model, @ModelAttribute NewCategoryDto newCategoryDto) {
        try {
            categoryService.update(id, newCategoryDto);
            return "redirect:/admin/categories";
        } catch (Exception e){
            model.addAttribute("error", String.format("ERROR: %s", e.getMessage()));
            model.addAttribute("category", newCategoryDto);
            model.addAttribute("id", id);
            return "/admin/categories/edit";
        }

    }

}
