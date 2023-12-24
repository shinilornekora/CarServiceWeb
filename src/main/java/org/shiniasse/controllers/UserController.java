package org.shiniasse.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shiniasse.dtos.UserDTO;
import org.shiniasse.dtos.UserRegistrationDTO;
import org.shiniasse.entities.User;
import org.shiniasse.services.implementations.AuthService;
import org.shiniasse.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    private final UserServiceImpl userServiceImpl;
    private final AuthService authService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, AuthService authService) {
        this.userServiceImpl = userServiceImpl;
        this.authService = authService;
    }

    @GetMapping("")
    public String getUsers(Model model){
        LOG.log(Level.INFO, "Get all users");
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "user-all";
    }

    @GetMapping("/get/{id}")
    public String getUser(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "Get user with id " + id);

        model.addAttribute("user", userServiceImpl.getUser(id));
        return "user-all";
    }
    @GetMapping("/login")
    public String loginUser(){
        LOG.log(Level.INFO, "Open login page");
        return "login-page";
    }
    @GetMapping("/login-error")
    public String loginError(){
        LOG.log(Level.INFO, "Open login-error page");
        return "login-error";
    }

    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal) {
        String username = principal.getName();
        UserDTO user = new UserDTO();
        if (username != null) {
            user = userServiceImpl.getUserByUsername(username);
        }
        model.addAttribute("user", user);
        System.out.println("CHECK USER: " + user.toString());
        return "user-profile";
    }

    @GetMapping("/registration")
    public String registrationUser(){
        LOG.log(Level.INFO, "Open registration page");
        return "registration-page";
    }
    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initForm(){
        return new UserRegistrationDTO();
    }
    @PostMapping("/registration")
    public String registrationUser(@Valid UserRegistrationDTO userRegistrationDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            LOG.log(Level.INFO, "Fail registration and redirect on re-registration");
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO",
                    bindingResult);
            return "redirect:/user/registration";
        }
        LOG.log(Level.INFO, "Successful registration new user");
        authService.register(userRegistrationDTO);
        return "redirect:/user/login";
    }

//    @PostMapping("/update")
//    public String updateUser(@Valid @RequestBody UserDTO userDTO, Model model){
//        LOG.log(Level.INFO, "Update user " + userDTO.getFirstName());
//
//        userServiceImpl.updateUser(userDTO);
//        return "pages/user/userConstruct";
//    }
//
//    @GetMapping("/remove/{id}")
//    public String removeUser(@PathVariable String id, Model model){
//        LOG.log(Level.INFO, "Remove user with id " + id);
//
//        userServiceImpl.deleteUser(id);
//        model.addAttribute("users", userServiceImpl.getAllUsers());
//        return "pages/user/userList";
//    }
}
