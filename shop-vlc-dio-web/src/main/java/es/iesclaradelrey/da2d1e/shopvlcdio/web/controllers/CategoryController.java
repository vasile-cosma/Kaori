package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/categories", "/categories/"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("category-grid-3-cols");

       mv.addObject("categories", categoryService.findAll());

        return mv;
    }

    @GetMapping("/categories/{id}")
    public ModelAndView detail(@PathVariable(name = "id") Integer id){
        ModelAndView mv = new ModelAndView("category-list");

        Optional<Category> category = categoryService.findById(id);
        mv.addObject("isPresent", category.isPresent());
        if(category.isPresent()){
            mv.addObject("category", category.orElseThrow());
        }

        return mv;
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

}
