package es.iesclaradelrey.da2d1e.shopvlcdio.web.controllers;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewUserDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.AppUserRepository;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.AppUserService;
import es.iesclaradelrey.da2d1e.shopvlcdio.security.AppUserDetails;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class AppUserController {
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    public AppUserController(AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

     @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
     @GetMapping("users/profile/{id}")
     public String showUserProfileById(Model model, @PathVariable("id") Integer id, Authentication authentication) {

         System.out.println(authentication.getPrincipal());

         AppUser appUser = appUserService.findById(id).orElseThrow(() -> new IllegalArgumentException("No se ha encontrado el usuario"));

         model.addAttribute("user", appUser);

        return "users/profile";
     }

     @PreAuthorize("isAuthenticated()")
     @GetMapping("users/profile")
     public String showUserProfile(Model model, @AuthenticationPrincipal UserDetails user, Authentication authentication) {
        AppUserDetails appUser = (AppUserDetails) user;
        return showUserProfileById(model, appUser.getId(), authentication);
    }

    @GetMapping({"/register", "/register/"})
    public String newUserGet(Model model){
        model.addAttribute("user", new NewUserDto());
        return "/users/register";
    }

    @PostMapping({"/register", "/register/"})
    public String newUserPost(@ModelAttribute("user") NewUserDto newUserDto, Model model){
        ArrayList<String> errors = new ArrayList<>();
        if (newUserDto.getUsername().isBlank() ||
                newUserDto.getName().isBlank() ||
                newUserDto.getLastName().isBlank() ||
                newUserDto.getPassword().isBlank() ||
                newUserDto.getEmail().isBlank()
        ) {
            errors.add("ERROR: Debe completar todos los campos obligatorios");
        }
        if (!newUserDto.getPassword().equals(newUserDto.getPasswordConfirmation())){
            errors.add("ERROR: Las contraseñas deben coincidir");
        }
        if (!newUserDto.isTermsAccepted()){
            errors.add("ERROR: Debe aceptar los términos y condiciones");
        }
        if (appUserService.findByUsername(newUserDto.getUsername()).isPresent()){
            errors.add("ERROR: Ya existe una cuenta con ese nombre de usuario");
        }
        if (appUserService.findByEmail(newUserDto.getEmail()).isPresent()){
            errors.add("ERROR: Ya existe una cuenta con ese correo electrónico");
        }
        if (!newUserDto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.add("ERROR: Introduzca un correo electrónico válido");
        }
        if (!newUserDto.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$")) {
            errors.add("ERROR: La contraseña debe tener mínimo 8 caracteres, una letra y un número");
        }
        if (!errors.isEmpty()){
            model.addAttribute("errors", errors);
            return "/users/register";
        }


        try{
            newUserDto.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
            appUserService.createNew(newUserDto);
        } catch (DataIntegrityViolationException e) {
            errors.add("ERROR: Ya existe una cuenta con estos datos");
            model.addAttribute("errors", errors);
            return "/users/register";
        } catch (Exception e) {
            System.out.println(e.getClass());
            errors.add(e.getMessage());
            model.addAttribute("errors", errors);
            return "/users/register";
        }

        return "redirect:/index";
    }



}
