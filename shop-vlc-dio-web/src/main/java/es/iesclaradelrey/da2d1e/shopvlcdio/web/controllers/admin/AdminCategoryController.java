package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers.admin;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCategoryDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.List;
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
        return "redirect:/admin/categories/categories";
    }

    @GetMapping
    public ModelAndView adminCategories(){
        return new ModelAndView("/admin/categories/categories");
    }

    @GetMapping("/new")
    public String newCategoryGet(Model model){
        model.addAttribute("category", new NewCategoryDto());
        return "/admin/categories/new";
    }

    @PostMapping("/new")
    public String newCategoryPost(@ModelAttribute("category") NewCategoryDto newCategoryDto, Model model){
        try {
            categoryService.createNew(newCategoryDto);
        } catch (Exception e) {
            model.addAttribute("error", String.format("Se ha producido un error: %s", e.getMessage()));
            return "/admin/categories/new";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteCategoryGet(@PathVariable Integer id, Model model){
        Optional<Category> category = categoryService.findById(id);
        model.addAttribute("category", category.orElseThrow());

        return "/admin/categories/delete";
    }

    @PostMapping({"/delete/{id}", "/delete/{id}/"})
    public String deleteCategoryPost(@PathVariable Integer id, Model model){
        Optional<Category> category = categoryService.findById(id);
        try {
            category.ifPresent(categoryService::delete);
            return "redirect:/admin/categories";
        } catch (Exception e){
            model.addAttribute("error", String.format("ERROR: \n%s", e.getMessage()));
            model.addAttribute(("category"), category.orElseThrow());
            return "/admin/categories/delete";
        }
    }

}
