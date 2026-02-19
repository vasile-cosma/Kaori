package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String redirectAdmin() {
        return "redirect:/admin";
    }

    @GetMapping
    public ModelAndView admin(){
        return new ModelAndView("/admin/admin-index");
    }
}
