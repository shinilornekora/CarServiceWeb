package org.shiniasse.controllers;

import jakarta.validation.Valid;
import org.shiniasse.dtos.UserDTO;
import org.shiniasse.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("")
    public String getUsers(Model model){
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "user-all";
    }

    @GetMapping("/get/{id}")
    public String getUser(@PathVariable String id, Model model){
        model.addAttribute("user", userServiceImpl.getUser(id));
        return "user-all";
    }

    @PostMapping("/add")
    public String addUser(@Valid @RequestBody UserDTO userDTO, Model model){
        userServiceImpl.saveUser(userDTO);
        return "pages/user/userConstruct";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @RequestBody UserDTO userDTO, Model model){
        userServiceImpl.updateUser(userDTO);
        return "pages/user/userConstruct";
    }

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable String id, Model model){
        userServiceImpl.deleteUser(id);
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "pages/user/userList";
    }
}
