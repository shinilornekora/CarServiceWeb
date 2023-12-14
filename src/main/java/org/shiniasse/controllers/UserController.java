package org.shiniasse.controllers;

import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shiniasse.dtos.UserDTO;
import org.shiniasse.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    private UserServiceImpl userServiceImpl;

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

    @PostMapping("/add")
    public String addUser(@Valid @RequestBody UserDTO userDTO, Model model){
        LOG.log(Level.INFO, "Add user " + userDTO.getFirstName());

        userServiceImpl.saveUser(userDTO);
        return "pages/user/userConstruct";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @RequestBody UserDTO userDTO, Model model){
        LOG.log(Level.INFO, "Update user " + userDTO.getFirstName());

        userServiceImpl.updateUser(userDTO);
        return "pages/user/userConstruct";
    }

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable String id, Model model){
        LOG.log(Level.INFO, "Remove user with id " + id);

        userServiceImpl.deleteUser(id);
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "pages/user/userList";
    }
}
