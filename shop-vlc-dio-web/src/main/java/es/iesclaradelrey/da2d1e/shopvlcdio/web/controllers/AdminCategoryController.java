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
        System.out.printf("Producto agregado:\n%s\n", newCategoryDto);
        categoryService.createNew(newCategoryDto);
        return "/admin/categories/categories";
    }
    /*
    @GetMapping("/new")
    public String newCategoryGet(Model model){
        model.addAttribute("teacher", new NewTeacherModel());
        return "teachers/new";
    }


    @PostMapping("/new")
    public String newStudentPost(@ModelAttribute("teacher") NewTeacherModel newTeacherModel){
        System.out.printf("Profesor registrado:\n%s\n", newTeacherModel);
        teacherService.createNew(newTeacherModel);
        return "redirect:/teachers";
    }

    @GetMapping("/new")
    public String newStudentGet(Model model){
        model.addAttribute("student", new NewStudentModel());
        model.addAttribute("modules", moduleService.findAll());
        return "students/new";
    }


    @PostMapping("/new")
    public String newStudentPost(@ModelAttribute("student") NewStudentModel newStudentModel,
                                 Model model){

        System.out.printf("Estudiante recibido:\n%s\n", newStudentModel);

        try {
            Student newStudent = studentService.createNew(newStudentModel);
            return String.format("redirect:/students/%d", newStudent.getStudentId());
        } catch (Exception e) {
            System.out.println("Se ha producido un error");
            model.addAttribute("modules", moduleService.findAll());
            return "students/new";
        }


        //model.addAttribute("modules",moduleService.findAll());
        //return "students/new";
    }
 */
}
