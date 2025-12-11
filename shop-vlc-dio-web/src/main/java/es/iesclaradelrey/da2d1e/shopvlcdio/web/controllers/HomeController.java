package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"/index", "/index/"})
    public ModelAndView index() {
        //todo: Cambiar la ubicación de las páginas en templates.
        ModelAndView mv = new ModelAndView("index");

        mv.addObject("categories", categoryService.findAll());

        return mv;
    }
}
