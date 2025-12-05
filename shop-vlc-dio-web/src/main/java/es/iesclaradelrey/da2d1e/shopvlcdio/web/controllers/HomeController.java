package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public static ModelAndView index(){
        new ModelAndView("index");
        return new ModelAndView("index");
    }
}
