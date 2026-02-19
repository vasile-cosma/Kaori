package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewCategoryDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public String newCategoryPost(@ModelAttribute("category") NewCategoryDto newCategoryDto){
        Category newCategory = categoryService.createNew(newCategoryDto);
        categoryService.save(newCategory);
        System.out.printf("Producto agregado:\n%s\n", newCategoryDto);
        return "redirect:/admin/categories";
    }

}
