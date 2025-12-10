package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/categories", "/categories/"})
    public ModelAndView index() {
        //todo: Cambiar la ubicación de las páginas en templates.
        ModelAndView mv = new ModelAndView("category-grid-3-cols");

       mv.addObject("categories", categoryService.findAll());

        return mv;
    }

    //TODO: PENDIENTE GET MAPPING
    public ModelAndView detail(@PathVariable(name = "id") Long categoryId){
        ModelAndView mv = new ModelAndView("detail");

        Optional<Category> category = categoryService.findById(categoryId);

        mv.addObject("isPresent", category.isPresent());
        if(category.isPresent()){
            mv.addObject("category", category.orElseThrow());
        }

        return mv;
    }
}
